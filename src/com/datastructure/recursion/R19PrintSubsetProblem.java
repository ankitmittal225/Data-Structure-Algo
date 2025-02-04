package com.datastructure.recursion;

/**
 * substring < Subsequence < powerset
 */
class R19PrintSubsetProblem{
	public static void main(String[] s){
		R19PrintSubsetProblem r19= new R19PrintSubsetProblem();
		r19.printSubset("");
		r19.printSubset("a");
		r19.printSubset("ab");
		r19.printSubset("abc");
		r19.printSubset("abcd");

	}


	public void printSubset(String str){
		System.out.println();
		if(str==null || str.isEmpty()){
			System.out.println(str);
			return;
		}
		printSubset(str,0,"");
	}

	public void printSubset(String str, int i ,String out){
		if(i==str.length()){
			System.out.println(out);
			return;
		}
		printSubset(str,i+1,out);
		printSubset(str,i+1,out+str.charAt(i));
	}
}