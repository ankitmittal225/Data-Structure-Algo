package com.datastructure.recursion;

public class R05SumOfNNaturalNumbers {

    public static void main(String[] args) {

        System.out.println(R05SumOfNNaturalNumbers.getSum(2));
        System.out.println(R05SumOfNNaturalNumbers.getSum(4));
        System.out.println(R05SumOfNNaturalNumbers.getSum(5));
    }

    private static int getSum(int i) {
        if(i==0) return 0;
        return i+getSum(i-1);
    }


}
