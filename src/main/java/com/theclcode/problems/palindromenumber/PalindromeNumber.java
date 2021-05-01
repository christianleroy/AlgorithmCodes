package com.theclcode.problems.palindromenumber;

import java.util.Scanner;

public class PalindromeNumber {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();
        int copy = number;
        int reversedNumber = 0;
        int remainder;

        while (number > 0) {
            remainder = number % 10;
            number = number / 10;
            reversedNumber = reversedNumber * 10 + remainder;
        }

        System.out.println("Palindrome: " + (reversedNumber == copy));

    }
}
