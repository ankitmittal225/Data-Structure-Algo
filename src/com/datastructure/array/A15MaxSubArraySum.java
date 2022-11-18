package com.datastructure.array;

//In a sorted Array
public class A15MaxSubArraySum {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{1,-2,3,-1,2};
        int[] arr2=new int[]{-5,1,-2,3,-1,2,-2};
        System.out.println(A15MaxSubArraySum.getMax(arr1));
        System.out.println(A15MaxSubArraySum.getMax(arr2));
    }

    //we need to compute the leftmax & right max for a given index and choose min one from them
    private static int getMax(int[] arr) {
//        int max=arr[0], curr;
//        for (int i = 0; i < arr.length; i++) {
//            curr=0;
//            for (int j = i;j < arr.length; j++) {
//                curr=curr+arr[j];
//                max=Integer.max(curr,max);
//            }
//        }
//        return max;

        /*
        idea is we can check the sum till previous element & choose which one is maximum prevsum or the curr element to start with
        maxEnding[i]=max(maxEnding[i-1]+Arr[i],arr[i]);
         */
        int max=arr[0], maxprev=arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxprev=Integer.max(maxprev+arr[i],arr[i]);
            max=Integer.max(max,maxprev);
        }
        return max;
    }

}
