package com.datastructure.dp;

import java.util.Objects;

/**
 * Problem Type : smallest common supersequence Length pattern
 *
 * Given two strings str1 and str2, the task is to find the length of the shortest string that has
 * both str1 and str2 as subsequences.
 *
 * Examples :
 *
 * Input:   str1 = "geek",  str2 = "eke"
 * Output: 5
 * Explanation:
 * String "geeke" has both string "geek"
 * and "eke" as subsequences.
 *
 * Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"
 * Output:  9
 * Explanation:
 * String "AGXGTXAYB" has both string
 * "AGGTAB" and "GXTXAYB" as subsequences.
 *
 * for recursion :
 * - base case (smallest valid input)
 * - Choice diagram
 * - Input should get smaller
 *
 * how to compute supersequence :
 * str1 = "AGGTAB",  str2 = "GXTXAYB"
 * start comparing each character and if it matches write it once if its not write the
 * lowest index character once
 * i=0 j=0 : A & G  Output : A
 * i=1 j=0 : G & G  Output : AG
 * i=2 j=1 : G & X  Output : AGX
 * i=2 j=2 : G & T  Output : AGXG
 * i=3 j=2 : T & T  Output : AGXGT
 * i=4 j=3 : A & X  Output : AGXGX
 * i=4 j=4 : A & A  Output : AGXGXA
 * i=5 j=5 : B & Y  Output : AGXGXA
 *
 * Length of smallest common supersequence= length of string 1 +length of string 2 - LCS
 * for example : str1 = "AGGTAB" n= 6, str2 = "GXTXAYB" m= 7 , LCS = GTAB length=4
 * smalles common subsequence : AGGXTXAYB , length = 9
 * n+m-lcs= 6+7-4 = 9
 *
 *
 *
 */
public class L18ShortedCommonSupersequenceLengthProblem {

    public static void main(String[] args) {
        System.out.println(getLongestCommonSubsequence("abcdgh", "abedfh"));//abcedgfh = 8
        System.out.println(getLCSMemoization("abcdgh", "abedfh"));//abcedgfh = 8
        System.out.println(getLCSTopDownDP("abcdgh", "abedfh"));//abcedgfh = 8
        System.out.println(getLongestCommonSubsequence("abcde", "ace"));//abcde = 5
        System.out.println(getLCSMemoization("abcde", "ace"));//abcde = 5
        System.out.println(getLCSTopDownDP("abcde", "ace"));//abcde = 5
        System.out.println(getLongestCommonSubsequence("abc", "abc"));//abc = 3
        System.out.println(getLCSMemoization("abc", "abc"));//abc = 3
        System.out.println(getLCSTopDownDP("abc", "abc"));//abc = 3
        System.out.println(getLongestCommonSubsequence("abc", "def"));//abcdef=6
        System.out.println(getLCSMemoization("abc", "def"));//abcdef=6
        System.out.println(getLCSTopDownDP("abc", "def"));//abcdef=6
        System.out.println(getLongestCommonSubsequence("abc", ""));//abc=3
        System.out.println(getLCSMemoization("abc", ""));//abc=3
        System.out.println(getLCSTopDownDP("abc", ""));//abc=3
    }

    private static int  getLongestCommonSubsequence(String s1, String s2) {
        int lcs=getLongestCommonSubsequence(s1,s2,s1.length(),s2.length());
        return s1.length()+s2.length() - lcs;
    }

    private static int getLongestCommonSubsequence(String s1, String s2, int n, int m) {
        if(n==0 || m==0) return 0;
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return 1+getLongestCommonSubsequence(s1, s2, n-1, m-1);
        }
        else{
            return Integer.max(getLongestCommonSubsequence(s1, s2, n-1, m),getLongestCommonSubsequence(s1, s2, n, m-1));
        }
    }

    //memoization (bottom-up approach)
    private static int  getLCSMemoization(String s1, String s2) {
        int n=s1.length(), m=s2.length();
        int[][] t= new int[n+1][m+1];

        for (int i = 0; i < n+1; i++) {
            for (int j  = 0; j < m+1; j++) {
                t[i][j]=-1;
            }
        }
        return n+m-getLCSMemoization(s1,s2,n,m,t);

    }

    private static int getLCSMemoization(String s1, String s2, int n, int m, int[][] t) {
        if(n==0 || m==0) return 0;
        if(t[n][m]==-1) {
            if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                t[n][m]= 1+getLongestCommonSubsequence(s1, s2, n - 1, m - 1);
            } else {
                t[n][m]= Integer.max(getLongestCommonSubsequence(s1, s2, n-1, m),getLongestCommonSubsequence(s1, s2, n, m-1));
            }
        }
        return t[n][m];
    }

    /**
     *
     */
    private static int getLCSTopDownDP(String s1, String s2) {
        int n= s1.length(),m=s2.length();
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
        return n+m-t[n][m];
    }

}
