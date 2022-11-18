package com.datastructure.array;

//In a sorted Array
public class A22SubArrayGivenSum {

    //Find the maximum sum of k consecutive element
    public static void main(String[] args) {
        int[] arr1=new int[]{3,2,0,4,7};
        int[] arr2=new int[]{5,2,4,1,6};

        System.out.println(A22SubArrayGivenSum.isSubArrayExists(arr1,6));
        System.out.println(A22SubArrayGivenSum.isSubArrayExists(arr2,2));
        System.out.println(A22SubArrayGivenSum.isSubArrayExists1(arr1,6));
        System.out.println(A22SubArrayGivenSum.isSubArrayExists(arr2,2));
    }

    /*'
    Naive Solution
     */
    private static boolean isSubArrayExists(int[] arr, int sum) {
        int curr=0;
        for (int i = 0; i < arr.length; i++) {
            curr=0;
            for (int j = i; j < arr.length; j++) {
                curr=curr+arr[j];
                if(curr==sum) return true;
            }
        }
        return false;
    }

    /*'
    Efficient Solution
    we are using sliding window technique with variable length where s =start & e= end of the window
    Idea is to keep adding the element of arr until its sum is greater than given one and if its grater than given one start suntracting element from begining
    like [4,8,12,5] sum=17
        let s=0, e=0 //start & end
        e=0 curr=4
        e=1 curr=4+8=12
        e=2 curr=12+12=24
        since 24>17
            s=0 curr=24-4=20
            s=1 curr=20-8=12
        scince 12>17 false
        e=3 curr=12+5=17
        since 17==17 return true;

     */
    private static boolean isSubArrayExists1(int[] arr, int sum) {
        int s=0,curr=0;
        for (int e = 0; e < arr.length; e++) {
            curr += arr[e];
            while(curr>sum){
                curr-=arr[s];
                s++;
            }
            if(curr==sum) return true;
        }
        return false;
    }


}
