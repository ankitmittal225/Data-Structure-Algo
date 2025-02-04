package com.datastructure.dp;

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
public class L16LengthOfLongestCommonSubsequenceProblem {

    public static void main(String[] args) {
        System.out.println(getLengthOfLongestCommonSubsequence("abcdgh", "abedfh"));//abdh
        System.out.println(getLengthOfLCSMemoization("abcdgh", "abedfh"));//abdh
        System.out.println(getLengthOfLCSTopDownDP("abcdgh", "abedfh"));//abdh
        System.out.println(getLengthOfLongestCommonSubsequence("abcde", "ace"));//ace
        System.out.println(getLengthOfLCSMemoization("abcde", "ace"));//ace
        System.out.println(getLengthOfLCSTopDownDP("abcde", "ace"));//ace
        System.out.println(getLengthOfLongestCommonSubsequence("abc", "abc"));//abc
        System.out.println(getLengthOfLCSMemoization("abc", "abc"));//abc
        System.out.println(getLengthOfLCSTopDownDP("abc", "abc"));//abc
        System.out.println(getLengthOfLongestCommonSubsequence("abc", "def"));//""
        System.out.println(getLengthOfLCSMemoization("abc", "def"));//""
        System.out.println(getLengthOfLCSTopDownDP("abc", "def"));//""
    }

    private static int getLengthOfLongestCommonSubsequence(String s1, String s2) {
        return getLengthOfLongestCommonSubsequence(s1,s2,s1.length(),s2.length());
    }

    private static int getLengthOfLongestCommonSubsequence(String s1, String s2, int n, int m) {
        if(n==0 || m==0) return 0;
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return 1+getLengthOfLongestCommonSubsequence(s1, s2, n-1, m-1);
        }
        else{
            return Math.max(getLengthOfLongestCommonSubsequence(s1, s2, n-1, m),getLengthOfLongestCommonSubsequence(s1, s2, n, m-1));
        }
    }

    //memoization (bottom-up approach)
    private static int getLengthOfLCSMemoization(String s1, String s2) {
        if(s1.length()==0 || s2.length()==0) return 0;
        int n=s1.length(), m=s2.length();
        int[][] t= new int[n+1][m+1];

        for (int i = 0; i < n+1; i++) {
            for (int j  = 0; j < m+1; j++) {
                t[i][j]=-1;
            }
        }

        return getLengthOfLCSMemoization(s1,s2,n,m,t);

    }

    private static int getLengthOfLCSMemoization(String s1, String s2, int n, int m, int[][] t) {
        if(n==0 || m==0) return 0;
        if(t[n][m]==-1) {
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                t[n][m]= 1 + getLengthOfLongestCommonSubsequence(s1, s2, n - 1, m - 1);
            } else {
                t[n][m]= Math.max(getLengthOfLCSMemoization(s1, s2, n - 1, m,t), getLengthOfLCSMemoization(s1, s2, n, m - 1,t));
            }
        }
        return t[n][m];
    }

    /**
     *
     */
    private static int getLengthOfLCSTopDownDP(String s1, String s2) {
        int n= s1.length(),m=s2.length();
        if(n==0 || m==0) return 0;
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
        return t[n][m];
    }

}
