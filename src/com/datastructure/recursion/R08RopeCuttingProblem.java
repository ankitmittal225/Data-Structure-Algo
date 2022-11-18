package com.datastructure.recursion;

public class R08RopeCuttingProblem {

    public static void main(String[] args) {

        System.out.println(R08RopeCuttingProblem.getMaxCut(5,1,2,3));
        System.out.println(R08RopeCuttingProblem.getMaxCut(23,11,9,12));

    }

    //time complezity 3 raise to the power N (3)N
    private static int getMaxCut(int num, int a, int b, int c) {
        if(num==0) return 0;
        if(num<0) return -1;
        int res= Integer.max(getMaxCut(num-a,a,b,c),Integer.max(getMaxCut(num-b,a,b,c),getMaxCut(num-c,a,b,c)));
        return res==-1?-1:res+1;
    }

}
