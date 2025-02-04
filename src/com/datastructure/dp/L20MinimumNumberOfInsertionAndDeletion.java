package com.datastructure.dp;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Minimum number of deletions and insertions to transform one string into another

Given two strings ‘str1’ and ‘str2’ of size m and n respectively. The task is to remove/delete and insert minimum number of characters from/in str1 so as to transform it into str2. It could be possible that the same character needs to be removed/deleted from one point of str1 and inserted to some another point.
Example:
Input : str1 = "geeksforgeeks", str2 = "geeks"
Output : Minimum Deletion = 8
         Minimum Insertion = 0

PROBLEM STATEMENT LINK:https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqazhTaU1LRlg0UmhfdXVPOGZ2My1CMGRweEpHQXxBQ3Jtc0trZXVRdnU2TkJNMGpkdnF3YUQxX1pKdXBuS0k0cFpyTnVtUnV3TldTbTNOTEZXUlZ5Y0x0X0l0eVAzczVHVlJYc2QtOHVnZ1hzNjZId09TOVYtZTZ4MkIyRFE5aXc3Y29ZWk1GSEt0Y1NYNnFkVk9hTQ&q=https%3A%2F%2Fwww.geeksforgeeks.org%2Fminimum..&v=-fx6aDxcWyg


How This problem match with pattern of LCS (Longest common subsequence)
example heap->pea
for which we need to remove h & P and add p in front ea will be common and won't require changes
ea -> longest common subsequence of heap & pea
so process will be

heap -> LCS -> Pea
LCS -> ea
wee need to convert heap to LCS i.e deletion and LCS to pea i.e. deletion
Heap->ea : H & p deleted 2 deletion
ea->pea : p inserted 1 insertion

number of insertion : length of b- len of LCS
number of deletion : length of 1- len of lcs

 */
public class L20MinimumNumberOfInsertionAndDeletion {

    public static void main(String[] s){
        getMinimumNumberOfInsertionAndDeletion("", "abedfha");
        getMinimumNumberOfInsertionAndDeletion("abcdgh", null);
        getMinimumNumberOfInsertionAndDeletion("abcdgh", "abedfha");
        getMinimumNumberOfInsertionAndDeletion("bbcdgh", "abcdfha");
        getMinimumNumberOfInsertionAndDeletion("abcdef", "ghijk");
        getMinimumNumberOfInsertionAndDeletion("abcd", "abcd");
        getMinimumNumberOfInsertionAndDeletion("heap", "pea");
    }

    public static void getMinimumNumberOfInsertionAndDeletion(String str1, String str2){
        if(str1==null || str1.isEmpty() ||str2==null || str2.isEmpty()) return;
        int n= str1.length(), m= str2.length();
//        int LCP= getLengthOfLongestCommonSubSequenceRecursion(str1, str2, n, m);

        int[][] t= new int[n+1][m+1];
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= m; j++) {
                    t[i][j]=-1;
            }
        }
//        int LCP=  getLengthOfLongestCommonSubSequenceMemoization(str1, str2, n, m,t );
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                t[i][j]=0;
            }
        }
        int LCP=   getLengthOfLongestCommonSubstringTopDown(str1,str2,n,m,t);
        System.out.println("Deletion : "+(str1.length()-LCP)+", insertion : "+(str2.length()-LCP));
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
