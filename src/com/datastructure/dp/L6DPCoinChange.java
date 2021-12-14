package com.datastructure.dp;

public class L6DPCoinChange {
	public static void main(String[] args) {
		int arr[]= {1,2,3};
		System.out.println(getCoinCount2(arr,4));
	}
	
//	//recusrive solution
//	private static int getCoinCount(int[] arr,int sum,int n) {
//		if(n==0) return 0; 
//		if(sum==0) return 1; // if sum=0 we have one way : to not collect any coin thats why its one
//		int res=getCoinCount(arr, sum, n-1);
//		if(arr[n-1]<=sum) {
//			res=res+getCoinCount(arr, sum-arr[n-1], n);
//		}
//		return res;
//	}
	
	//recusrive dynamic solution
//		private static int getCoinCount(int[] arr,int sum,int n) {
//			if(n==0) return 0;
//			if(sum==0) return 1;
//			int res=getCoinCount(arr, sum, n-1);
//			if(arr[n-1]<=sum) {
//				res=res+getCoinCount(arr, sum-arr[n-1], n);
//			}
//			return res;
//		}
		
//		// tabulation solution
//		private static int getCoinCount2(int[] arr,int sum,int n) {
//			int[][] dp=new int[sum+1][n+1];
//			if(n==0) return 0;
//			if(sum==0) return 1;
//			int res=getCoinCount(arr, sum, n-1);
//			if(arr[n-1]<=sum) {
//				res=res+getCoinCount(arr, sum-arr[n-1], n);
//			}
//			return res;
//		}
		// tabulation solution
			private static int getCoinCount2(int[] arr,int n) {
				if(n==0) return 1;
				int[] res=new int[n+1];
				int total;
				res[0]=1;
				for(int i=1;i<=n;i++) {
					total=0;
					for(int j=0;j<arr.length;j++) {
						if(i-arr[j]>=0) total=total+res[i-arr[j]];
					}
					res[i]=total;
				}
				return res[n];
			}

}

/**
 * Coin Change :  here we are given array of coin types find total possible ways to get the sum given
 * 
 * I/P : coins[]={1,2,3} sum=4
 * O/P : 4
 * How : 1+1+1+1,2+2,2+1+1,3+1
 * 
 * I/P : coins[]={2,3,5,6} sum=10
 * O/P : 5
 * How : 2+2+2+2+2, 2+3+2+3,2+2+6,5+5,2+3+5
 */
