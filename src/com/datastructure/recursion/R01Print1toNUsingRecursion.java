package com.datastructure.recursion;

public class R01Print1toNUsingRecursion {

    public static void main(String[] args) {
        R01Print1toNUsingRecursion.print(100);
    }

    public static void print(int n) {
        if(n==0) return;
        print(n-1);
        System.out.print(n+" ");
    }


}

/*
Time Complexity : theta(n)
Auxiliary Space : theta(n)
however taking about concept of tail call elimination & if your compiler is modern compiler than auxiliary space will be reduce to theta(1).
 */