package com.datastructure.dp;

/**
 * Problem Type : Longest common subsequence pattern
 *
 * Longest Common Substring(Dynamic Programming)
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * Examples:
 *
 * Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
 * Output : 5
 * The longest common substring is “Geeks” and is of length 5.
 *
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
public class L19LengthOfLongestCommonSubStringProblem {

    public static void main(String[] args) {
            System.out.println(getLengthOfLongestCommonSubstring("", "abedfha"));
            System.out.println(getLengthOfLongestCommonSubstring("abcdgh", null));
            System.out.println(getLengthOfLongestCommonSubstring("abcdgh", "abedfha"));
            System.out.println(getLengthOfLongestCommonSubstring("bbcdgh", "abcdfha"));
            System.out.println(getLengthOfLongestCommonSubstring("abcdef", "ghijk"));
            System.out.println(getLengthOfLongestCommonSubstring("abcd", "abcd"));
        }

        public static int getLengthOfLongestCommonSubstring(String str1, String str2){
            if(str1==null || str1.isEmpty() ||str2==null || str2.isEmpty()) return 0;
            int n= str1.length(), m= str2.length();
//        return getLengthOfLongestCommonSubstringRecursion(str1, str2, n, m, 0);
            int[][] t= new int[n+1][m+1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= m; j++) {
                    t[i][j]=-1;
            }
        }

        return getLengthOfLongestCommonSubstringMemoization(str1, str2, n, m,t ,0);
//            for (int i = 0; i <= n ; i++) {
//                for (int j = 0; j <= m; j++) {
//                    t[i][j]=0;
//                }
//            }
//            return getLengthOfLongestCommonSubstringTopDown(str1,str2,n,m,t);
        }

        public static int getLengthOfLongestCommonSubstringRecursion(String str1,String str2,int n,int m, int max){
            if(n==0 || m==0) return max;
            if(str1.charAt(n-1)==str2.charAt(m-1)){
                max=getLengthOfLongestCommonSubstringRecursion(str1, str2, n-1, m-1,max+1);
            }
            else{
                max=Integer.max(max,Integer.max(getLengthOfLongestCommonSubstringRecursion(str1, str2, n-1, m,0),getLengthOfLongestCommonSubstringRecursion(str1, str2, n, m-1,0)));
            }
            return max;
        }

        // for this problem no such solution exists.
        private static int getLengthOfLongestCommonSubstringMemoization(String str1, String str2, int n, int m, int[][] t, int max) {
            if(n==0 || m==0) return max;
            if(t[n][m]!=-1) return t[n][m];
            int len=max;
            if(str1.charAt(n-1)==str2.charAt(m-1)){
                len= getLengthOfLongestCommonSubstringMemoization(str1, str2, n-1, m-1,t,max+1);
            }
            int len1=getLengthOfLongestCommonSubstringMemoization(str1, str2, n-1, m,t,0);
            int len2=getLengthOfLongestCommonSubstringMemoization(str1, str2, n, m-1,t,0);
            len=Math.max(len,Math.max(len1, len2));
            return len;
        }

        private static int getLengthOfLongestCommonSubstringTopDown(String str1, String str2, int n, int m, int[][] t) {
            int max=0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if(str1.charAt(i-1)==str2.charAt(j-1)){
                        t[i][j]=1+t[i-1][j-1];
                        max= Integer.max(max,t[i][j]);
                    }
                    else{
                        t[i][j]=0;
                    }
                }
            }
            return max;
        }

}
