package com.datastructure.recursion;

class R18KthSymbolGrammer {
	public static void main(String[] s){
		R18KthSymbolGrammer r18= new R18KthSymbolGrammer();
		System.out.println(r18.getKthSymbol(1,1)); // 0
		System.out.println(r18.getKthSymbol(2,2)); // 0 1  output= 1
		System.out.println(r18.getKthSymbol(2,1)); // 0 1  output= 0
		System.out.println(r18.getKthSymbol(3,2)); // 0 1 1 0  output= 1
		System.out.println(r18.getKthSymbol(3,3)); // 0 1 1 0  output= 1
		System.out.println(r18.getKthSymbol(4,4)); // 0 1 1 0 1 0 0 1  output= 0
		System.out.println(r18.getKthSymbol(4,5)); // 0 1 1 0 1 0 0 1  output= 1

	}


	public int getKthSymbol(int N, int K){
		if(N==1 && K==1) return 0;
		int mid= (int) (Math.pow(2,N-1)/2);
		if(K<=mid){
			return getKthSymbol( N-1, K);
		}
		else{
			return getKthSymbol( N-1, K-mid)^1; // convert 0 to 1 and 1 to zero
		}
	}
}
