package com.datastructure.array;

import java.util.HashMap;
import java.util.Map;

//In a sorted Array
public class A12FreqOfAllElelmentInArray {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
//        int[] arr=new int[]{7,9,5,6,3,2};
        int[] arr=new int[]{2,3,10,6,4,6,1,6,3};
        A12FreqOfAllElelmentInArray.getFreq2(arr);
//        System.out.println(A12FreqOfAllElelmentInArray.getDiffernce2(arr));

    }

    //BIGO N Square  :native solution
    private static void getFreq(int[] arr) {
        int count=0;
        for(int i=0;i<arr.length;i++){
            count=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==arr[j]) {
                    arr[j]=Integer.MAX_VALUE;
                    count++;
                }
            }
            if(arr[i]!=Integer.MAX_VALUE)
            System.out.println(arr[i]+" = "+count);
        }
    }

    //BIGO N  :efficient solution
    //idea is to Use a map
    private static void getFreq2(int[] arr) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        for (Map.Entry e:map.entrySet()) {
            System.out.println(e.getKey()+" : "+e.getValue());
        }
    }





}
