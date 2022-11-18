package com.datastructure.array;

//In a sorted Array
public class A18MaximumCircularSubArraySum {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{10,5,-5};
        int[] arr2=new int[]{5,-2,3,4};
        int[] arr3=new int[]{8,-4,3,-5,4};
        int[] arr4=new int[]{3,-4,5,6,-8,7};

        System.out.println(A18MaximumCircularSubArraySum.getMax(arr1));
        System.out.println(A18MaximumCircularSubArraySum.getMax2(arr2));
        System.out.println(A18MaximumCircularSubArraySum.getMax2(arr3));
        System.out.println(A18MaximumCircularSubArraySum.getMax2(arr4));
    }

    /*'
    for example [10,5,-5]
    normal subset : {10},{5},{-5}, {10,5}, {5,-5},{10,5,-5}
    circular : {5,-5,10},{-5,10},{-5,10,5}
     */
    private static int getMax(int[] arr) {
        int curr_max, curr_sum;
        int res=0, index=0;
        for(int i=0;i<arr.length;i++){
            curr_max=arr[i];
            curr_sum=arr[i];
            for(int j=1;j<arr.length;j++){
                index=(i+j)%arr.length;
                curr_sum += arr[index];
                curr_max=Integer.max(curr_max,curr_sum);
            }
            res=Integer.max(res,curr_max);
        }
        return res;
    }

    /*'
    Idea : take the sum of following two
            1 maximum sum of normal subarray( Easy by using Kandane Algorithm)
            2.maximum sum of circular subarray(how to find this)
        take example :
        arr[]=[5,-2,3,4] if we substract minimum sum -2 from total will get maximum circular sum
        arr[]=[8,-4,3,-5,4] if we substraction minimum sum total from 8+4 will get max circular sum
        arr[]=[3,-4,5,6,-8,7] if we substract minimum sum --8 from total will get max circular sum
        idea is to remove the minimum sum which in mid from total using modify kandane algo

        example : arr[]=[8,-4,3,-5,4] max_nor=8,arr_sum=6
        after invert arr[]=[-8,4,-3,5,-4], max sum of this=min sum of original=6
        max_Cir=6+6 {since we inverted the original array so instead of substracting it from normal sum we will add it in normal sum
        res=max(8,12)=12
    */
    private static int getMax2(int[] arr) {
        int max_nor=getNormalSubarraySum(arr);
        if(max_nor<0) return max_nor;//because if max_nor is negative it means all number is negative
        int arr_sum=0,cir_sum=0;
        int res=0, index=0;
        for(int i=0;i<arr.length;i++){
            arr_sum+=arr[i];
            arr[i]=-arr[i]; //on reverting arr number its max sum will be the minimum sum of original array.
        }
        cir_sum=arr_sum+getNormalSubarraySum(arr);
        return Integer.max(cir_sum,max_nor);
    }

    public static int getNormalSubarraySum(int[] arr){
        int curr_sum=arr[0], max=0;
        for(int i=1;i<arr.length;i++){
            curr_sum=Integer.max(curr_sum+arr[i],arr[i]);
            max=Integer.max(max,curr_sum);
        }
        return max;
    }

}
