package com.datastructure.recursion;

public class R06Palindrome {

    public static void main(String[] args) {

        System.out.println(R06Palindrome.checkPalindrome("abcdcba",0,7));

    }

    private static boolean checkPalindrome(String str, int s, int e) {
         if(e<=s) return true;
         return str.charAt(s)==str.charAt(e-1) && checkPalindrome(str,s+1,e-1);
    }


}
