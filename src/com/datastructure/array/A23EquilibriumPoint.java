package com.datastructure.array;

//In a sorted Array
public class A23EquilibriumPoint {

    //Equilibrium point is if sum of elements before it is equal to some of elements after it
    public static void main(String[] args) {
        int[] arr1=new int[]{3,4,8,-9,20,6};
        int[] arr2=new int[]{4,2,-2};
        int[] arr3=new int[]{4,2,2};

        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint(arr1));
        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint(arr2));
        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint(arr3));
        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint1(arr1));
        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint1(arr2));
        System.out.println(A23EquilibriumPoint.hasEquilibriumPoint1(arr3));
    }

    /*'
    Naive Solution
     */
    private static boolean hasEquilibriumPoint(int[] arr) {
        int ls=0,rs=0;
        for (int i = 0; i < arr.length; i++) {
            ls=0;
            rs=0;
            for (int j = 0; j < i; j++) {
                ls=ls+arr[j];
            }
            for (int j = i+1; j < arr.length; j++) {
                rs=rs+arr[j];
            }
            if(ls==rs) return true;
        }
        return false;
    }

    /*'
    Efficient Solution
        arr={3,4,8,-9,9,7}
        idea is to
         1  compute the prefix sum {3,7,15,6,15,22}
         2. compute the suffix sum {22,19,15,7,16,7}
         3 for every element (except corner ones) check if ps[i-1] equals ss[i+1]
         so for this we need 3 traversel but to optimise it calculating the overall sum and and rs we can get by
         substracting the current element & ls we can get by keep adding the current element & where both ls===rs is our equilibrium point

         arr[3,4,8,-9,0,7] rs=22, ls=0
         i=0 rs=19 ls=0
         i=1 rs=15 ls 3
         i=2 rs=7 ls=7 return true
     */
    private static boolean hasEquilibriumPoint1(int[] arr) {
        int rs=0,ls=0;
        for (int i = 0; i < arr.length; i++) {
            rs += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            rs=rs-arr[i];
            if(ls==rs) return true;
            ls=ls+arr[i];
        }
        return false;
    }


}
