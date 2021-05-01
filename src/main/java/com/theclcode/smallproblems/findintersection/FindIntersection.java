package com.theclcode.smallproblems.findintersection;


// https://coderbyte.com/results/christianleroydev:Find%20Intersection:Java
public class FindIntersection {

    public static void main(String[] args) {
        System.out.println(findIntersection(new String[]{"1, 2, 3, 4, 5, 8", "1, 3, 5, 6, 8"}));
    }

    public static String findIntersection(String[] strArr) {

        if (strArr[0].isEmpty() || strArr[1].isEmpty()) {
            return "false";
        }

        String[] a = strArr[0].split(", ");
        String[] b = strArr[1].split(", ");

        StringBuilder builder = new StringBuilder();

        for (int i = 0, j = 0; i < a.length && j < b.length; ) {
            if (Integer.parseInt(a[i]) == Integer.parseInt(b[j])) {
                builder.append(a[i]).append(",");
                i++;
                j++;
            } else if (Integer.parseInt(a[i]) > Integer.parseInt(b[j])) {
                j++;
            } else {
                i++;
            }
        }
        if (builder.length() == 0) {
            return "false";
        }
        return builder.substring(0, builder.length() - 1);
    }
}
