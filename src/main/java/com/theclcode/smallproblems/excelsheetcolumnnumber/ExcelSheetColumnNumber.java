package com.theclcode.smallproblems.excelsheetcolumnnumber;

// https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        String columnTitle = "AAA";
        System.out.println(titleToNumber(columnTitle));
    }

    public static int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result = result * 26 + ((columnTitle.charAt(i) - 'A') + 1);
        }
        return result;
    }

    public static int titleToNumberReversed(String columnTitle) {
        int result = 0;
        int multiplier = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            result += multiplier * ((columnTitle.charAt(i) - 'A') + 1);
            multiplier *= 26;
        }
        return result;
    }
}
