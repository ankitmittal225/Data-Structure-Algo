package com.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Part 1, Basic Coding:
 * Given an input array of integers, return the indices of al local minima in the array. An element is a local minima fi ti is <= its immediate neighbors.
 * • Ex. 1
 * • In: 4,8,2,10
 * Out: 0,2
 * • Ex. 2
 * In: 7,3,5,7,9,0,2
 * • Out: 1,5
 *
 * Part 2, Efficiency:
 * Given an input array of integers, return the index of any one local minima in the array.
 * Potential Follow-Up Questions (optional)
 * Possible Expansions:
 * Code Review
 *
 *
 * Code Quality
 * • Covers testing of various inputs:
 * • Length 0 input
 * • Single element input
 * • Two element input, with both or one element as local minima
 * • 3+ element input, with one or multiple elements as local minima
 * • Clean looping and branching
 */
public class FindLocalMinima {
    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {4, 8, 2, 10};
        System.out.println(findLocalMinima1(nums1));  // Output: [0, 2]

        int[] nums2 = {7, 3, 5, 7, 9, 0, 2};
        System.out.println(findLocalMinima1(nums2));  // Output: [1, 5]

        // Additional test cases
        int[] nums3 = {};
        System.out.println(findLocalMinima1(nums3));  // Output: []

        int[] nums4 = {10};
        System.out.println(findLocalMinima1(nums4));  // Output: [0]

        int[] nums5 = {5, 3};
        System.out.println(findLocalMinima1(nums5));  // Output: [1]

        int[] nums6 = {3, 5};
        System.out.println(findLocalMinima1(nums6));  // Output: [0]

        int[] nums7 = {5, 5};
        System.out.println(findLocalMinima1(nums7));  // Output: [0, 1]
        System.out.println("-------");
        System.out.println(findLocalMinima2(nums1));
        System.out.println(findLocalMinima2(nums2));
        System.out.println(findLocalMinima2(nums3));
        System.out.println(findLocalMinima2(nums4));
        System.out.println(findLocalMinima2(nums5));
        System.out.println(findLocalMinima2(nums6));
        System.out.println(findLocalMinima2(nums7));

    }

    /**
     * Time Complexity:
     * The time complexity is O(n), where n is the number of elements in the array. We iterate through the array once.
     * Space Complexity:
     * The space complexity is O(k), where k is the number of local minima indices
     */
    public static List<Integer> findLocalMinima1(int[] nums) {
        List<Integer> localMinimaIndices = new ArrayList<>();

        // Handle edge cases
        if (nums == null || nums.length == 0) {
            return localMinimaIndices;
        }

        if (nums.length == 1) {
            localMinimaIndices.add(0);
            return localMinimaIndices;
        }

        // Check the first element
        if (nums[0] <= nums[1]) {
            localMinimaIndices.add(0);
        }

        // Check the elements in between
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i - 1] && nums[i] <= nums[i + 1]) {
                localMinimaIndices.add(i);
            }
        }

        // Check the last element
        if (nums[nums.length - 1] <= nums[nums.length - 2]) {
            localMinimaIndices.add(nums.length - 1);
        }

        return localMinimaIndices;
    }

    public static List<Integer> findLocalMinima2(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        if (nums.length == 1) {
            result.add(0);
            return result;
        }

        int left = 0;
        int right = nums.length - 1;

        // Check if the first element is a local minima
        if (nums[left] <= nums[left + 1]) {
            result.add(left);
        }

        // Check if the last element is a local minima
        if (nums[right] <= nums[right - 1]) {
            result.add(right);
        }

        // Perform binary search to find all local minima
        binarySearch(nums, left, right, nums.length, result);

        return result;
    }

    public static void binarySearch(int[] nums, int left, int right, int n, List<Integer> list){
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid>0 && nums[mid] <= nums[mid - 1] && nums[mid] <= nums[mid + 1]) {
                // Found a local minima
                list.add(mid);
            }
            binarySearch(nums, left, mid-1, nums.length, list);
            binarySearch(nums, mid+1, right, nums.length, list);
            break;
        }
    }

}
