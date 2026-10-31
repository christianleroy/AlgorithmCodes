package com.theclcode.string.easy;

// https://leetcode.com/problems/day-of-the-year/
public class DayOfYear {

    public static void main(String[] args) {
        System.out.println(dayOfYear("2019-02-10"));
    }


    public static int dayOfYear(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(year > 2 && (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            daysInMonth[1] = 29;
        }

        int numberOfDaysInYear = 0;
        for(int i = 1; i <= month; i++) {
            if(i == month) {
                numberOfDaysInYear += day;
                break;
            }
            numberOfDaysInYear += daysInMonth[i-1];
        }

        return numberOfDaysInYear;
    }

}
