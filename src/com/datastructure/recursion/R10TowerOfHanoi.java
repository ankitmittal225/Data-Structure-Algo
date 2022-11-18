package com.datastructure.recursion;

public class R10TowerOfHanoi {

    public static void main(String[] args) {

        R10TowerOfHanoi.print(3,"A","B","C");

    }

    private static void print(int n, String from, String through, String to) {
        if(n==1) {
            System.out.println( "Move "+n +" from "+from+" to "+to);
            return;
        }
        print(n-1,from, to,through);
        System.out.println( "Move "+n +" from "+from+" to "+to);
        print(n-1,through,from, to);

    }



}
