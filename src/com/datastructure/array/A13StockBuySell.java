package com.datastructure.array;

//In a sorted Array
public class A13StockBuySell {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{10,20,30};
        int[] arr2=new int[]{30,20,10};
        int[] arr3=new int[]{1,5,3,8,12};
//        System.out.println(A13StockBuySell.getMax(arr1,0,2));
//        System.out.println(A13StockBuySell.getMax(arr2,0,2));
//        System.out.println(A13StockBuySell.getMax(arr3,0,4));

        //optimized solution
        int profit=0;
        for(int i=1;i<arr1.length;i++){
            if(arr1[i]>arr1[i-1])
                profit=profit+arr1[i]-arr1[i-1];
        }
    }

    private static int getMax(int[] arr, int str, int end) {
        if(end<=str) return 0;
        int max=0,currProfit=0;
        for(int i=str;i<=end;i++){
            for(int j=i+1;j<=end;j++){
                if(arr[j]>arr[i]) {
                    currProfit = arr[j]-arr[i]+getMax(arr,str,i-1)+getMax(arr,j+1,end);
                }
                max=Integer.max(max,currProfit);
            }
        }
        return max;
    }


}
