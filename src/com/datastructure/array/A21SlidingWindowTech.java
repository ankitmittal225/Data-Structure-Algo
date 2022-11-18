package com.datastructure.array;

//In a sorted Array
public class A21SlidingWindowTech {

    //Find the maximum sum of k consecutive element
    public static void main(String[] args) {
        int[] arr1=new int[]{1,8,30,-5,20,7};
        int[] arr2=new int[]{5,-10,6,90,3};

        System.out.println(A21SlidingWindowTech.getMax(arr1,3));
        System.out.println(A21SlidingWindowTech.getMax(arr2,2));
        System.out.println(A21SlidingWindowTech.getMax2(arr1,3));
        System.out.println(A21SlidingWindowTech.getMax2(arr2,2));
    }

    /*'
    Naive Solution
     */
    private static int getMax(int[] arr, int k) {
        int max=0,sum=0;
        for (int i = 0; i < arr.length-k; i++) {
            sum=0;
            for (int j = i; j < i+k; j++) {
                sum += arr[j];
            }
            max=Integer.max(max,sum);
        }
        return max;
    }

    /*'
    Efficient Solution
    Idea is to Compute sum of current window using some of previous window in O(1) time
    Example  : [1,8,30,-5,20,7] k =3;
    first we have elements in window : [1,8,30] sum=39
    for second window what we will do we subtract the first element in window i.e. 1 from the sum and add next element from array to the sum & window.
            [8,30,-5]=prevsum-first element-next element=39-1+(-5)=33

     */
    private static int getMax2(int[] arr, int k) {
        int max=0,sum=0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        max=sum;
        System.out.println("sum :"+sum);
        for(int i=k;i<arr.length;i++){
            sum=sum-arr[i-k]+arr[i];
            max=Integer.max(sum,max);
        }
        return max;
    }


}
