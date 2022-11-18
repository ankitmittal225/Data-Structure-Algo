package com.datastructure.array;

import java.util.HashMap;
import java.util.Map;

//In a sorted Array
public class A12FreqOfAllElelmentInSortedArray {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
//        int[] arr=new int[]{7,9,5,6,3,2};
//        int[] arr=new int[]{1,2,2,3,3,3,6,7,7};
//        int[] arr=new int[]{1};
        int[] arr=new int[]{40,50,50,50};
        A12FreqOfAllElelmentInSortedArray.getFreq(arr);
//        System.out.println(A12FreqOfAllElelmentInArray.getDiffernce2(arr));

    }

    //BIGO N Square  :native solution
    private static void getFreq(int[] arr) {
        int count=1,curr=arr[0],i=1;
        while(i<arr.length){
            if(arr[i]==curr) {
                count++;
            }
            else{
                System.out.println(curr+" : "+count);
                curr=arr[i];
                count=1;
            }
            i++;
        }
        System.out.println(curr+" : "+count);

    }





}
