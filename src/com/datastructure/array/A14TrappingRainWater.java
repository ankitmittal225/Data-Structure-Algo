package com.datastructure.array;

//In a sorted Array
public class A14TrappingRainWater {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{3,0,1,2,5};
        System.out.println(A14TrappingRainWater.getMax(arr1));
        System.out.println(A14TrappingRainWater.getMax1(arr1));
        System.out.println(A14TrappingRainWater.trap(arr1));
    }

    //we need to compute the leftmax & right max for a given index and choose min one from them
    private static int getMax(int[] arr) {
        int  lmax=0,rmax=0, totalwater=0;
        for(int i=1;i<arr.length;i++){
            lmax=arr[i];
            rmax=arr[i];
            for(int j=0;j<i;j++){
                lmax=Integer.max(lmax,arr[j]);
            }
            for(int j=i+1;j<arr.length;j++){
                rmax=Integer.max(rmax,arr[j]);
            }
            totalwater=totalwater+Integer.min(rmax,lmax)-arr[i];
        }
        return totalwater;
    }

    //we need to compute the leftmax & right max for a given index and choose min one from them but instead of computing it everytime will pre-compute it in
    //lmax & rmax array
    private static int getMax1(int[] arr) {
        int[]  lmax=new int[arr.length];
        int[]  rmax=new int[arr.length];
        rmax[0]=arr[arr.length-1];
        lmax[0]=arr[0];
        int totalwater=0;
        for(int i=1;i<arr.length;i++){
            lmax[i]=Integer.max(arr[i],lmax[i-1]);
        }
        for(int i=arr.length-2;i>=0;i--){
            rmax[i]=Integer.max(arr[i],rmax[i+1]);
        }
        for(int i=0;i<arr.length;i++) {
            totalwater =totalwater+Integer.min(lmax[i],rmax[i])-arr[i];
        }
        return totalwater;
    }

    public static int trap(int[] height) {
        int l = 0, r = height.length - 1, sum = 0, lMax = 0, rMax = 0;
        while (l <= r) {
            lMax = Math.max(height[l], lMax);
            rMax = Math.max(height[r], rMax);
            //why? because, for example if the lMax is smaller, we can sure that how much water could be trapped at the left pointer position is decided by the left side.
            if (lMax < rMax) {
                sum += lMax - height[l++];
            } else {
                sum += rMax - height[r--];
            }
        }
        return sum;
    }


}
