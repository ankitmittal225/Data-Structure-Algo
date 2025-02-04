package com.datastructure.dp;

import java.util.Objects;

/**
 * Problem Type : Longest common subsequence pattern
 *
 * Longest Common Subsequence Problem solution using recursion
 * Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 *
 * For example, “abc”,  “abg”, “bdf”, “aeg”,  ‘”acefg”, .. etc are subsequences of “abcdefg”.
 * Example s1= "abcdgh" s2= "abedfh"
 * SubString : continious = ab
 * Subsequence : non-continious = abd
 *
 * for recursion :
 * - base case (smallest valid input)
 * - Choice diagram
 * - Input should get smaller
 *
 *
 */
public class L17LongestCommonSubsequenceProblem {

    public static void main(String[] args) {
        System.out.println(getLongestCommonSubsequence("abcdgh", "abedfh"));//abdh
        System.out.println(getLCSMemoization("abcdgh", "abedfh"));//abdh
        System.out.println(getLCSTopDownDP("abcdgh", "abedfh"));//abdh
        System.out.println(getLongestCommonSubsequence("abcde", "ace"));//ace
        System.out.println(getLCSMemoization("abcde", "ace"));//ace
        System.out.println(getLCSTopDownDP("abcde", "ace"));//ace
        System.out.println(getLongestCommonSubsequence("abc", "abc"));//abc
        System.out.println(getLCSMemoization("abc", "abc"));//abc
        System.out.println(getLCSTopDownDP("abc", "abc"));//abc
        System.out.println(getLongestCommonSubsequence("abc", "def"));//""
        System.out.println(getLCSMemoization("abc", "def"));//""
        System.out.println(getLCSTopDownDP("abc", "def"));//""
    }

    private static String getLongestCommonSubsequence(String s1, String s2) {
        return getLongestCommonSubsequence(s1,s2,s1.length(),s2.length());
    }

    private static String getLongestCommonSubsequence(String s1, String s2, int n, int m) {
        if(n==0 || m==0) return "";
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return getLongestCommonSubsequence(s1, s2, n-1, m-1)+s1.charAt(n-1);
        }
        else{
            String str1=getLongestCommonSubsequence(s1, s2, n-1, m);
            String str2=getLongestCommonSubsequence(s1, s2, n, m-1);
            return str1.length()>str2.length()?str1:str2;
        }
    }

    //memoization (bottom-up approach)
    private static String getLCSMemoization(String s1, String s2) {
        if(s1.length()==0 || s2.length()==0) return "";
        int n=s1.length(), m=s2.length();
        String[][] t= new String[n+1][m+1];

        for (int i = 0; i < n+1; i++) {
            for (int j  = 0; j < m+1; j++) {
                t[i][j]="";
            }
        }

        return getLCSMemoization(s1,s2,n,m,t);

    }

    private static String getLCSMemoization(String s1, String s2, int n, int m, String[][] t) {
        if(n==0 || m==0) return "";
        if(Objects.equals(t[n][m], "")) {
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                t[n][m]= getLongestCommonSubsequence(s1, s2, n - 1, m - 1)+s1.charAt(n-1);
            } else {
                String str1= getLCSMemoization(s1, s2, n - 1, m,t);
                String str2=getLCSMemoization(s1, s2, n, m - 1,t);
                t[n][m]=  str1.length()>str2.length()?str1:str2;
            }
        }
        return t[n][m];
    }

    /**
     *
     */
    private static String getLCSTopDownDP(String s1, String s2) {
        int n= s1.length(),m=s2.length();
        if(n==0 || m==0) return "";
        int[][] t= new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            t[i][0]=0;
        }
        for (int j  = 0; j < m+1; j++) {
            t[0][j]=0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    t[i][j]=t[i-1][j-1]+1;
                }
                else{
                    t[i][j]=Math.max(t[i][j-1],t[i-1][j]);
                }
            }
        }
        int i=n,j=m;
        StringBuilder s= new StringBuilder();
        while (i > 0 && j > 0) {
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                s.insert(0, s1.charAt(i-1));
                i--;
                j--;
            }
            else{
                if(t[i][j-1]>t[i-1][j]){
                    j--;
                }
                else{
                    i--;
                }
            }
        }

        return s.toString();
    }

}
