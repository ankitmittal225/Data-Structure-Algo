package com.datastructure.dp;

public class L5DPLCSVariation {
	static int arr[][];
	//tabulation way for fibonacci series(Bottom up)
	public static void main(String[] args) {
		
	}
	

}

/**
 * Variation of LCS : 
 * 	Diff Utility (difference between two file)
 * 	Minimum insertion and deletion required to convert one string into other
 * 		Ex "GEEK" "GFEK" - 1 deletion(remove f) 1 insertion(insert e)
 * 	Shortest common subsequence 
 * 		subsequence : postion of character can be different but must be in order like ale is subsequence of apple
 *		substring : position of character is matter with order like ale is not substring but app or ple is
 *		Ex "GEEK" "GFEK" -> "GEEFK" (find common sequence and add aditional character of other string in exact order);
 *
 *	Longest Palindrome sequence
 *		Ex s= "GEEKSFORGEEKS" longest palidrome seq ="EEFEE" "EEOEE"
 *		how can we solve this by lcs : 
 *			s= "GEEKSFORGEEKS" 
 *			s2="SKEEGROFSKEEG" (reverse of s)
 *		find longest common subsequence i.e. EEOEE
 *	Longesst Repeating subsequence
 *		Ex : s="AABB" o/p=2 (AB is repeating two time ) 
 *			 s="AAA"  o/p=2 (AA is repeating we need to consider same character in both the sequence at same indexes)
 *		How by LCS : 
 *			s= "AABB" s2="AABB" Using LCS concept with one differnce
 *			instead of  (str.charAt(i-1)==str2.charAt(j-1 )), Use(str.charAt(i-1)==str2.charAt(j-1 ) && i!=j)
 *
 *	Space optimized solution of LCS
 *		if you look at previous solution to compute the next row we only need previous row,so we can reduce the size to 
 *		arr[2][m] or arr[2][m]
 *	Printing LCS
 */
