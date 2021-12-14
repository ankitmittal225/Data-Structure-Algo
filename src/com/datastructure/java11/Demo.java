package com.datastructure.java11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
	/**
	 * 
Given an array of integers, our task is to write a program that efficiently finds the second largest element present in the array. 
Example:

Input: arr[] = {12, 35, 1, 10, 34, 1}
Output: The second largest element is 34.
Explanation: The largest element of the 
array is 35 and the second 
largest element is 34

Input: arr[] = {10, 5, 10}
Output: The second largest element is 5.
Explanation: The largest element of 
the array is 10 and the second 
largest element is 5

Input: arr[] = {10, 10, 10}
Output: The second largest does not exist.
Explanation: Largest element of the array 
is 10 there is no second largest element
	 * @param args
	 * 
	 * Given an array of strings, find all anagram pairs in the given array. 
		Example: 
		 
		
		Input: arr[] =  {"cat", "dog", "tac", "god", "act"};
		Output: [cat, tac, act][dog, god]
		
		 Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |

select * from employee e, employee m where e.maneger_id=m.id and e.salary>m.salary;
	 */
	public static void main(String[] args) {
		int[] arr= {12,35,1,10,34,1};
		int[] arr2 = {10, 5, 10};
		int[] arr1 = {10, 10, 10};
		String[] arr3 =  {"cat", "dog", "tac", "god", "act"};
//		System.out.println(getSecondLargest(arr));
//		System.out.println(getSecondLargest(arr1));
//		System.out.println(getSecondLargest(arr2));
		findAnagram(arr3);
	}

	private static void findAnagram(String[] arr) {
		
		Map<String,List<String>> map=new HashMap<>();
		String key;
	
		for(int i=0;i<arr.length;i++) {
			char[] strarr=arr[i].toCharArray();
			Arrays.sort(strarr);
			key=new String(strarr);
			List<String> val=map.get(key);
			if(val==null) {
				val=new ArrayList<>();
			}
			val.add(arr[i]);
			map.put(key, val);
//			else {
//				val.add(arr[i]);
//			}
		}
		for(List<String> str:map.values()) {
			System.out.println(str);
		}
		// TODO Auto-generated method stub
		
	}

	private static int getSecondLargest(int[] arr) {
		
		int first=-Integer.MIN_VALUE,second=-Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>first) {
				second=first;
				first=arr[i];
			}
			else if(arr[i]>second && arr[i]<first) {
				second=arr[i];
			}
		}
//		for(int i=0;i<arr.length;i++) {
//			if(arr[i]>second && arr[i]!=first) {
//				
//			}
//		}
		// TODO Auto-generated method stub
		return second;
	}
	
	
			
}



