package com.datastructure.array;

//In a sorted Array
public class A15MaxConsecutive1 {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{0,1,1,0,1,0,1,1,1,1,0,1,1,1};
        System.out.println(A15MaxConsecutive1.getMax(arr1));
    }

    //we need to compute the leftmax & right max for a given index and choose min one from them
    private static int getMax(int[] arr) {
//        int max=0, count=0;
//        return Integer.max(max,count);
//        for (int i = 0; i < arr.length; i++) {
//            if(arr[i]==1){
//                count=count+1;
//            }
//            if(arr[i]==0){
//                max=Integer.max(max,count);
//                count=0;
//            }
//            System.out.println(arr[i]+" : "+count);
//        }
//        return Integer.max(max,count);

        int max=arr[0], count=0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]==1)
            arr[i]=arr[i]+arr[i-1];
            else
                max=Integer.max(max,arr[i-1]);
        }
        return max;

    }

}
