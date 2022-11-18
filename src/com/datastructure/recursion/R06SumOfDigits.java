package com.datastructure.recursion;

public class R06SumOfDigits {

    public static void main(String[] args) {

        System.out.println(R06SumOfDigits.getSumByRecursion(11111));

    }

    //iterative
    private static int getSum(int n) {
        if(n==0) return n;
        int sum=0;
        while(n>0){
            sum=sum+(n%10);
            n=n/10;
        }
        return sum;
    }

    //recursive
    private static int getSumByRecursion(int n) {
        if(n<10) return n;
        return n/10+getSumByRecursion(n%10);
    }




}
