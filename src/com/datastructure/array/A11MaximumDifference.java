package com.datastructure.array;

public class A11MaximumDifference {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
//        int[] arr=new int[]{7,9,5,6,3,2};
        int[] arr=new int[]{2,3,10,6,4,8,1};
//        System.out.println(A11MaximumDifference.getDiffernce(arr));
        System.out.println(A11MaximumDifference.getDiffernce2(arr));

    }

    //BIGO Square  :native solution
    private static int getDiffernce(int[] arr) {
        if(arr.length==0) return 0 ;
        int max=arr[1]-arr[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                max=Integer.max(max,arr[j]-arr[i]);
            }
        }
        return max;
    }

    //BIGO Square  :efficient solution
    //idea is to keep track of min value while traversing from left to right and if difference is max with new value update it
    private static int getDiffernce2(int[] arr) {
        if(arr.length==0) return 0 ;
        int max=arr[1]-arr[0],minval=arr[0];
        for (int i = 1; i < arr.length; i++) {
            max=Integer.max(max,arr[i]-minval);
            minval=Integer.min(minval, arr[i]);
        }
        return max;
    }





}
