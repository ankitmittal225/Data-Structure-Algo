package com.problems;

import java.util.Arrays;

/**
 * Given a number, find the next smallest palindrome larger than this number. For example, if the input number is “2 3 5 4 5”, the output should be “2 3 6 3 2”. And if the input number is “9 9 9”, the output should be “1 0 0 1”.
 * The input is assumed to be an array. Every entry in array represents a digit in input number. Let the array be ‘num[]’ and size of array be ‘n’
 *
 * There can be three different types of inputs that need to be handled separately.
 * 1) The input number is palindrome and has all 9s. For example “9 9 9”. Output should be “1 0 0 1”
 * 2) The input number is not palindrome. For example “1 2 3 4”. Output should be “1 3 3 1”
 * 3) The input number is palindrome and doesn’t have all 9s. For example “1 2 2 1”. Output should be “1 3 3 1”.
 *
 * Solution for input type 1 is easy. The output contains n + 1 digits where the corner digits are 1, and all digits between corner digits are 0.
 * Now let us first talk about input type 2 and 3. How to convert a given number to a greater palindrome? To understand the solution, let us first define the following two terms:
 *
 * Left Side: The left half of given number. Left side of “1 2 3 4 5 6” is “1 2 3” and left side of “1 2 3 4 5” is “1 2
 * https://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
 *
 * All 9s Case:
 *
 * If the number is composed of all 9s, the next palindrome is simply 100...001. This is handled by creating a new array of size n + 1 with 1 at both ends.
 * Mirror and Increment:
 *
 * For other cases, the algorithm starts by mirroring the left half to the right half.
 * If the mirrored number is not greater than the original, incrementing the middle digit(s) is necessary. This may cause a carry which needs to be propagated.
 *
 */
public class NextSmallestPalindromeNumber {
    public static void main(String[] args) {
//        Integer.toString(num).chars().map(c -> c-'0').toArray();
        int[] num1 = {2, 3, 5, 4, 5};
        int[] num2 = {9, 9, 9};
        int[] num3 = {1, 2, 2, 1};

        System.out.println("Next palindrome for {2, 3, 5, 4, 5}: " + getNextSmallestPalindromeNative(23545));
        System.out.println("Next palindrome for {9, 9, 9}: " + getNextSmallestPalindromeNative(999));
        System.out.println("Next palindrome for {1, 2, 2, 1}: " + getNextSmallestPalindromeNative(1221));

        System.out.println("Next palindrome for {2, 3, 5, 4, 5}: " + next("23545".toCharArray()));
        System.out.println("Next palindrome for {9, 9, 9}: " + next("999".toCharArray()));
        System.out.println("Next palindrome for {1, 2, 2, 1}: " + next("1221".toCharArray()));

        System.out.println("Next palindrome for {2, 3, 5, 4, 5}: " + Arrays.toString(findNextPalindrome(num1)));
        System.out.println("Next palindrome for {9, 9, 9}: " + Arrays.toString(findNextPalindrome(num2)));
        System.out.println("Next palindrome for {1, 2, 2, 1}: " + Arrays.toString(findNextPalindrome(num3)));

        int[] num4 = {1, 2, 3, 4};
        int[] result4 = findNextPalindrome(num4);
        printArray(result4);

    }


    // not cover 9, 99,999 cases
    public static int getNextSmallestPalindromeNative(int num){
        while (!isPalindrome(num))
            num += 1;
        return num;
    }

    static boolean isPalindrome(int num)
    {

        // Declaring variables
        int n, k, rev = 0;

        // storing num in n so that we can compare it later
        n = num;

        // while num is not 0 we find its reverse and store
        // in rev
        while (num != 0) {
            k = num % 10;
            rev = (rev * 10) + k;
            num = num / 10;
        }

        // check if num and its reverse are same
        if (n == rev) {
            return true;
        }
        else {
            return false;
        }
    }

    public static long next(char[] s) {
        for(int i = 0, n = s.length; i < (n >> 1); i++) {
            while(s[i] != s[n - 1 - i]) {
                increment(s, n - 1 - i);
            }
        }
        return Long.parseLong(new String(s));
    }
    private static void increment(char[] s, int i) {
        while(s[i] == '9') s[i--] = '0';
        s[i]++;
    }

    // Function to find the next palindrome
    public static int[] findNextPalindrome(int[] num) {
        int n = num.length;

        // Case 1: All 9s
        if (areAll9s(num, n)) {
            int[] result = new int[n + 1];
            result[0] = 1;
            result[n] = 1;
            return result;
        }

        int[] result = num.clone();
        int mid = n / 2;
        int i = mid - 1;
        int j = (n % 2 == 0) ? mid : mid + 1;

        // Step 1: Ignore the middle same parts
        while (i >= 0 && result[i] == result[j]) {
            i--;
            j++;
        }

        boolean needIncrement = (i < 0 || result[i] < result[j]);

        // Step 2: Mirror the left side to the right side
        while (i >= 0) {
            result[j] = result[i];
            i--;
            j++;
        }

        // If the left half is smaller or equal, we need to handle increment
        if (needIncrement) {
            int carry = 1;
            i = mid - 1;

            // Add 1 to the middle digit(s)
            if (n % 2 == 1) {
                result[mid] += carry;
                carry = result[mid] / 10;
                result[mid] %= 10;
                j = mid + 1;
            } else {
                j = mid;
            }

            // Propagate the carry to the left side and mirror it to the right side
            while (i >= 0) {
                result[i] += carry;
                carry = result[i] / 10;
                result[i] %= 10;
                result[j++] = result[i--];
            }
        }

        return result;
    }

    // Function to check if all digits are 9
    private static boolean areAll9s(int[] num, int n) {
        for (int digit : num) {
            if (digit != 9) {
                return false;
            }
        }
        return true;
    }

    // Function to print the array
    public static void printArray(int[] arr) {
        for (int digit : arr) {
            System.out.print(digit + " ");
        }
        System.out.println();
    }
}
