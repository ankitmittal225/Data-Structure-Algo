package com.basicjava.predictoutput;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Array of integer
 * ith value represent price of stock on ith day
 * find the maximize profit
 *
 */

/**
 * given an array and a window size K print the maximum element in each window from start end
 * k=3
 */
public class predict {
        public static void main(String[] args) {
            int arr1[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
            int arr2[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            System.out.println(getMinJump(arr1,11));
            System.out.println(getMinJump(arr2,11));
        }

        public static int getMinJump(int[] arr, int n){
            if(arr.length<2) return 0;
            int temp[]=new int[n];
            temp[0]=0;
            for(int i=1;i<n;i++){
                temp[i]=Integer.MAX_VALUE;
                for(int j=0;j<i;j++){
                    if( i<=j+arr[j] && temp[j]!=Integer.MAX_VALUE){
                        temp[i]=Math.min(temp[i],temp[j]+1);
                        break;
                    }
                }
            }
            return temp[n-1];
        }

}
