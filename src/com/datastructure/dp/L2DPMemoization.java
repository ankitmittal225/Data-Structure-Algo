package com.datastructure.dp;

public class L2DPMemoization {
	static int[] memo;
	
	//Memoization way for fibonacci series
	public static void main(String[] args) {
		int val=5;
		memo=new int[val+1];
		
		System.out.println(getFibonacci(val));
	}
	
	private static int getFibonacci(int val) {
		// TODO Auto-generated method stub
		if(memo[val]==0) {
			if(val==0 || val==1) {
				memo[val]=val;
			}
			else {
				memo[val]=getFibonacci(val-1)+getFibonacci(val-2);
			}
		}
		return memo[val];
	}

}

/**
 * To understand memoization lets understand fibonacci series example using recursion.
 * 
 * fibonacci number : 0,1,1,2,3,5,8,13
 * I/p: n=3				I/P: n=0
 * O/P: 2				O/P: 0
 * 
 * Recusive Solution : 
 * int fib(n){
 * 		if(n==0 || n==1) return n;
 * 		else
 * 			return fib(n-1)+fib(n-2);	
 * }
 * 
 * 
 * Recusion Tree for program : 
 * 											fib(5)
 * 							/										\
 * 						fib(4)									fib(3)
 * 				/					\				/							\
 * 				fib(3)			fib(2) 			fib(2)						fib(1)						
 * 		/			\		/		\		/			\				
 * 		fib(2)		fib(1) fib(1)	fib(0) fib(1)		fib(0)			
 *	/			\
 * fib(1)		fib(0)
 * 
 * 
 * Here if you can see there are many problems which we are resolving again and again
 * even fib(3) is already computed under fib(4)
 * computing fib(2) almost 3 times
 * 
 * 
 */
