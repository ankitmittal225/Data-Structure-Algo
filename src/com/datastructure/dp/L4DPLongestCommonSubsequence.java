package com.datastructure.dp;

public class L4DPLongestCommonSubsequence {
	static int arr[][];
	//tabulation way for fibonacci series(Bottom up)
	public static void main(String[] args) {
//		int l=6,k=6;
//		arr= new int[l+1][k+1];
//		for(int i=0;i<l+1;i++) {
//			for(int j=0;j<l+1;j++) {
//				arr[i][j]=-1;
//			}
//		}
//		System.out.println(LCS("ABCDGH","AEDFHR",l,k));
//		System.out.println(LCSCount("ABCDGH","AEDFHR",l,k));
		System.out.println(LCSTab("ABCDGH","AEDFHR",6,6));
//		System.out.println(LCS("AXYZ","BAZ",4,3));
//		System.out.println(LCS("AGGTAB","GXTXAYB",6,7));
//		System.out.println(LCS("XYZ","XYZ",3,3));
//		System.out.println(LCS("ABC","XY",3,2));
		
	}

//	private static String LCS(String string, String string2, int n, int m) {
//		if(string.equals(string2)) return string;
//		if(string.isEmpty() || string2.isEmpty()) return "";
//		if(string2.contains(string.charAt(0)+"")) {
//			return string.charAt(0)+LCS(string.substring(1),string2.substring(string2.indexOf(string.charAt(0))+1),n,m);
//		}
//		return ""+LCS(string.substring(1),string2,n,m);
//	}
	
	// Using Basic Recursion theta(2 to the power n) theta(exponential)
	private static int LCS(String string, String string2, int n, int m) {
		if(n==0 || m==0) return 0;
		if(string2.charAt(m-1)==string.charAt(n-1)) 
			return 1+LCS(string,string2,n-1,m-1);
		else
			return Integer.max(LCS(string,string2,n,m-1),LCS(string,string2,n-1,m));
	}
	
	// Using Basic Memoization theta(mn)
		private static int LCSCount(String string, String string2, int n, int m) {
			if(arr[n][m]!=-1) return arr[n][m];
			if(n==0 || m==0) arr[n][m]=0;
			
			else {if(string2.charAt(m-1)==string.charAt(n-1)) 
				arr[n][m]= 1+LCSCount(string,string2,n-1,m-1);
			else
				arr[n][m]= Integer.max(LCSCount(string,string2,n-1,m),LCSCount(string,string2,n,m-1));
			}
			return arr[n][m];
		}
		
		// Using dp by Tabulation(i.e bottom up approach no recusion) theta(mn)
		private static int LCSTab(String string, String string2, int n, int m) {
			if(string.equals(string2)) return n;
			int[][] arr=new int[n+1][m+1];
			for(int i=0;i<n+1;i++) {
				arr[i][0]=0;
			}
			for(int i=0;i<m+1;i++) {
				arr[0][i]=0;
			}
			for(int i=1;i<n+1;i++) {
				for(int j=1;j<m+1;j++) {
					if(string.charAt(i-1)==string2.charAt(j-1))
						arr[i][j]=1+arr[i-1][j-1];
					else
						arr[i][j]=Integer.max(arr[i][j-1], arr[i-1][j]);
				}
			}
			return arr[n][m];
		}
	
	

}

/**
 * To understand memoization lets understand fibonacci series example using recursion.
 * 
 * I/P: s1="ABCDGH"
 * 		s2="AEDFHR"
 * 
 * O/P: 3(ADH)
 * 
 * I/P: s1="AGGTAB"
 * 		s2="GXTXAYB"
 * 
 * O/P: 4(GTAB)
 * 
 * I/P: s1="XYZ"
 * 		s2="XYZ"
 * 
 * O/P: 3(XYZ)
 * 
 * I/P: s1="ABC"
 * 		s2="XY"
 * 
 * O/P: 0
 * 
 */
