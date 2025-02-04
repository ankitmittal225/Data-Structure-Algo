package com.datastructure.dp;

/**
Given a sequence, find the length of the longest palindromic subsequence in it.
Example :
Input:"bbbab"
Output:4

https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqazlTUWlFbnFNTWc5MEZaNlRudmJjT2xPTUVHZ3xBQ3Jtc0trdFF3SVlVS2ljVUQyRDVSazhIemd2LUw2NmZBUGFISl9jZ2p6V3ZMSlZZblIwX2NialViVTMtaWtZVldWbkp6ckpjV2VzdG81WjNNcGVYek9BS1pCdlZfUURReERjU29WTGhuMVdrTEtSamUzOVQtbw&q=https%3A%2F%2Fwww.geeksforgeeks.org%2Flongest-palindromic-subsequence-dp-12%2F&v=wuOOOATz_IA

Approach :  a palindromic sequence will be which gives same output whether you start traversing from left or right
if we reverse the current string
and try to find LCS (longest common subsequence) it will give us Longest palindromic subsequence.

 **/
public class L21LengthOfLongestPalindromicSubSequenceProblem {
    public static void main(String[] s){
        System.out.println(getLongestPalindromicSubsequence(""));
        System.out.println(getLongestPalindromicSubsequence( null));
        System.out.println(getLongestPalindromicSubsequence("abcdgh"));
        System.out.println(getLongestPalindromicSubsequence("bbcdgh"));
        System.out.println(getLongestPalindromicSubsequence("abcdef"));
        System.out.println(getLongestPalindromicSubsequence("abcdcba"));
        System.out.println(getLongestPalindromicSubsequence("bbbab"));
        System.out.println(getLongestPalindromicSubsequence("agbcba"));
    }

    public static int getLongestPalindromicSubsequence(String str1){
        if(str1==null || str1.isEmpty() ) return 0;
        int n= str1.length();
        StringBuilder sb=new StringBuilder(str1);
        String str2=sb.reverse().toString();
        int m= n;
//        return getLengthOfLongestCommonSubSequenceRecursion(str1, str2, n, m);

        int[][] t= new int[n+1][m+1];
//        for (int i = 0; i <= n ; i++) {
//            for (int j = 0; j <= m; j++) {
//                    t[i][j]=-1;
//            }
//        }
//        return getLengthOfLongestCommonSubSequenceMemoization(str1, str2, n, m,t );
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                t[i][j]=0;
            }
        }
        return  getLengthOfLongestCommonSubstringTopDown(str1,str2,n,m,t);
    }

    public static int getLengthOfLongestCommonSubSequenceRecursion(String str1,String str2,int n,int m){
        if(n==0 || m==0) return 0;
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return 1+ getLengthOfLongestCommonSubSequenceRecursion(str1, str2, n-1, m-1);
        }
        else{
            return Integer.max(getLengthOfLongestCommonSubSequenceRecursion(str1, str2, n-1, m),getLengthOfLongestCommonSubSequenceRecursion(str1, str2, n, m-1));
        }
    }

    private static int getLengthOfLongestCommonSubSequenceMemoization(String str1, String str2, int n, int m, int[][] t) {
        if(n==0 || m==0) return 0;
        if(t[n][m]!=-1) return t[n][m];
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            t[n][m]= 1+getLengthOfLongestCommonSubSequenceMemoization(str1, str2, n-1, m-1,t);
        }
        else{
            t[n][m]=Integer.max(getLengthOfLongestCommonSubSequenceMemoization(str1, str2, n-1, m,t),getLengthOfLongestCommonSubSequenceMemoization(str1, str2, n, m-1,t));
        }
        return t[n][m];

    }

    private static int getLengthOfLongestCommonSubstringTopDown(String str1, String str2, int n, int m, int[][] t) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    t[i][j]=1+t[i-1][j-1];
                }
                else{
                    t[i][j]=Integer.max(t[i-1][j], t[i][j-1]);
                }
            }
        }
        return t[n][m];
    }
}
