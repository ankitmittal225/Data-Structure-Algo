package com.datastructure.recursion;

public class R04getNthFibonacci {

    public static void main(String[] args) {

        System.out.println(R04getNthFibonacci.getNthFibonacci(5));
    }

    private static int getNthFibonacci(int i) {
        if(i==0 || i==1) return 1;
        return getNthFibonacci(i-1)+getNthFibonacci(i-2);

    }
    //1,1,2,3,5,8
}
