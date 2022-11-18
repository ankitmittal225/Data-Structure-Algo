package com.datastructure.recursion;

public class R09GenerateSubset {

    public static void main(String[] args) {

        R09GenerateSubset.getSubset("abc",0,"");
        R09GenerateSubset.getSubset("abcd",0,"");

    }

    private static void getSubset(String str, int n, String s) {
        if(n==str.length()) {
            System.out.println(s);
            return;
        }
        getSubset(str,n+1,s);
        getSubset(str,n+1,s+str.charAt(n));
    }



}
