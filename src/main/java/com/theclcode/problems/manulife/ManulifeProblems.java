package com.theclcode.problems.manulife;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ManulifeProblems {

    /*
        Get Max Value
        Modify input array such that:
        1. It is sorted.
        2. First element is 1.
        3. The difference between element and the one next to it is 1.
        Return the highest value in the array.

        Get Usernames
        1. Call API and return the list of usernames that have higher submission count than the given threshold.
     */

    public static void main(String[] args) throws IOException {
        getUsernames(10);
    }

    public static int getMaxValue(List<Integer> arr) {
        Collections.sort(arr);
        if (arr.get(0) > 1) {
            arr.set(0, 1);
        }

        for (int i = 1; i < arr.size(); i++) {
            int j = i + 1;
            if (j < arr.size()) {
                if (arr.get(j) - arr.get(i) > 1) {
                    arr.set(j, arr.get(i) + 1);
                }
            }
        }
        return arr.get(arr.size() - 1);
    }

    private static final String TOTAL_PAGES = "\"total_pages\"";
    private static final String DATA = "\"data\"";
    private static final String URL = "https://jsonmock.hackerrank.com/api/article_users?page=%s";
    private static final String SUBMISSION_COUNT = "submission_count";
    private static final String USERNAME = "username";

    public static List<String> getUsernames(int threshold) throws IOException {
        List<String> result = new ArrayList<>();
        call(result, threshold);
        System.out.println(result);
        return result;
    }

    public static void call(List<String> result, int threshold) throws IOException {
        int currPage = 1;
        int totalPages = Integer.MAX_VALUE;
        while (currPage <= totalPages) {
            java.net.URL url = new URL(String.format(URL, currPage));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");

            StringBuilder responseBuilder = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                responseBuilder.append(scanner.nextLine());
            }

            String respString = responseBuilder.toString();
            String data = parseData(respString);


            totalPages = parseValue(TOTAL_PAGES, respString);
            currPage++;

            readData(data, result, threshold);

        }
    }

    static int parseValue(String key, String data) {
        String keyIndex = data.substring(data.indexOf(key));
        String value = keyIndex.substring(keyIndex.indexOf(":") + 1, keyIndex.indexOf(","));
        return Integer.parseInt(value);
    }

    static String parseValueString(String key, String data) {
        String keyIndex = data.substring(data.indexOf(key));
        return keyIndex.substring(keyIndex.indexOf(":") + 1, keyIndex.indexOf(","));
    }

    static String parseData(String response) {
        String keyIndex = response.substring(response.indexOf(DATA));
        return keyIndex.substring(keyIndex.indexOf("[") + 1, keyIndex.lastIndexOf("]"));
    }

    static void readData(String data, List<String> result, int threshold) {
        while (data.contains("{")) {
            data = data.substring(data.indexOf("{"));
            int submissionCount = parseValue(SUBMISSION_COUNT, data);
            if (submissionCount > threshold) {
                String author = parseValueString(USERNAME, data).replaceAll("\"", "");
                result.add(author);
            }
            data = data.substring(data.indexOf("}"));
        }
    }
}
