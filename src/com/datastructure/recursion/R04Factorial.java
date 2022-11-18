package com.datastructure.recursion;

public class R04Factorial {

    public static void main(String[] args) {

        System.out.println(R04Factorial.getFactorial(5));
    }

    private static int getFactorial(int i) {
//        if(i==1) return 1;
        // above one is wrong base case in case someone pass value as 0 for function call then it will stack overflow because it will keep calling
        if(i==0) return 1;
        return i*(i-1);
    }
}
