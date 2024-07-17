package com.problems.prep;


import java.util.Arrays;/*
Question
Given an array of sorted integers, sort the array ni ascending order of squares of its elements. +
For example:
[1,5,7,7,8,10] >- [1,5,7,7,8,10]|
[-5 ,-3,-3, 2, 4, 4, 8] ->[2, -3, -3, 4, 4，-5, 8] i.e.,
                squares: (4, 9, ,9 16, 16, 25, 64]

Potential Follow-Up Questions (preferred for this question)
Consider the same input (sorted array of integers),
how would you change your solution ot find only the element with the K-th smallest square?

Code Quality Covers Edge Cases
- Even and odd arrays
- All negative or all positive arrays
- Single element arrays
- K in range of len(arr)

Asks Clarifying Questions (where needed)
- Acknowledge het existence of negative values (why ordered by squares is different than the current order)
- Understanding the relation of the follow up to the original question
- What kind of space constraints?


 */
public class SortArrayBySquare {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 7, 7, 8, 10};
        int[] arr2 = {-5, -3, -3, 2, 4, 4, 8};
        int[] arr3 = {-5, -3, -3, -2, -1};
        int[] arr4 = {1, 2, 3, 4, 5};
        int[] arr5 = {5};

        System.out.println(Arrays.toString(sortBySquare1stApproach(arr1))); // [1, 5, 7, 7, 8, 10]
        System.out.println(Arrays.toString(sortBySquare1stApproach(arr2))); // [2, -3, -3, 4, 4, -5, 8]
        System.out.println(Arrays.toString(sortBySquare1stApproach(arr3))); // [-1, -2, -3, -3, -5]
        System.out.println(Arrays.toString(sortBySquare1stApproach(arr4))); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(sortBySquare1stApproach(arr5)));

        System.out.println("--------------for kth smallest quare----------");
        System.out.println(sortBySquare1stApproach(arr1)[4]); // [1, 5, 7, 7, 8, 10] k=5
        System.out.println(sortBySquare1stApproach(arr2)[3]); // [2, -3, -3, 4, 4, -5, 8] k=3
        System.out.println(sortBySquare1stApproach(arr3)[4]);// [-1, -2, -3, -3, -5] k=4
    }

    /**
     * Simple approach is to sort the array using a custom comparator (i.,e squares).
     * Time and space complexity becomes that of the sorting algorithm,
     * considering the comparison complexity is O(1).
     * oT be specific, O(nlogn) time and O(n) space or
     * O(1) space with O(nlogn) average-case or O(n^2) worse-case.
     * <p>
     * The key insight is that the squares of the largest negative numbers and the largest positive
     * numbers are the largest values in the squared array. Therefore, we can use two pointers
     * to merge these squared values in a manner similar to the merge step of the merge sort algorithm.
     * <p>
     * Time Complexity: O(n), where n is the number of elements in the array.
     * Each element is processed exactly once.
     * <p>
     * Space Complexity: O(n), for storing the result array. However, no additional space beyond
     * the input array and the result array is used, making it efficient in terms of space usage.
     */
    public static int[] sortBySquare1stApproach(int[] arr) {
        if (arr == null || arr.length < 2 || (arr.length > 1 && arr[0] >= 0)) return arr;
        int n = arr.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;
        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare > rightSquare) {
                result[index--] = arr[left];
                left++;
            } else {
                result[index--] = arr[right];
                right--;
            }
        }

        return result;
    }

    /*
    Folow up part si also important as the former steps can be quite simple (and potentially leaked online). oT find only the k-th element:
    a. First approach can be ot use the sort above and pick the k-1 st index >-O(n)
    b. Abetter approach si ot find the smallest nonnegative element using a binary search and then iterate from that
    point outward ni both directions for k-steps >- Olog n) +0(k)
     */
    public static int[] sortBySquare2ndApproach(int[] arr) {
        // Step 1: Find the split point for negative and non-negative squares
        int splitPoint = 0;
        while (splitPoint < arr.length && arr[splitPoint] < 0) {
            splitPoint++;
        }

        // Step 2: Merge sorted squares into the original array
        int[] result = new int[arr.length];
        int negativeIdx = splitPoint - 1;
        int positiveIdx = splitPoint;
        int index = 0;

        while (negativeIdx >= 0 && positiveIdx < arr.length) {
            if (arr[negativeIdx] * arr[negativeIdx] < arr[positiveIdx] * arr[positiveIdx]) {
                result[index++] = arr[negativeIdx] * arr[negativeIdx];
                negativeIdx--;
            } else {
                result[index++] = arr[positiveIdx] * arr[positiveIdx];
                positiveIdx++;
            }
        }

        // Add remaining elements
        while (negativeIdx >= 0) {
            result[index++] = arr[negativeIdx] * arr[negativeIdx];
            negativeIdx--;
        }

        while (positiveIdx < arr.length) {
            result[index++] = arr[positiveIdx] * arr[positiveIdx];
            positiveIdx++;
        }

        return result;
    }

    /**
     * find the smallest nonnegative element using a binary search and then iterate from that point in both directions for k-steps with O(log n) +O(k) time complexity
     * Time Complexity: O(log n + k) where n is the size of the array. O(log n) for binary search and O(k) for two pointers traversal.
     * Space Complexity: O(1) additional space.
     */
    public static int findKthSmallestSquare(int[] arr, int k) {
        // Binary search to find the smallest nonnegative element index
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] >= 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        int nonNegativeIndex = lo;

        // Two pointers to find k-th smallest element
        int left = nonNegativeIndex - 1;
        int right = nonNegativeIndex;
        int count = 0;

        while (count < k) {
            if (left >= 0 && (right >= arr.length || Math.abs(arr[left]) <= arr[right])) {
                left--;
            } else {
                right++;
            }
            count++;
        }

        return arr[right - 1];
    }

    /**
     * A better solution exists for the follow up that I would consider the candidate impressive if
     * they get to .ti This involves using a modified binary search/recursion to find the target
     * element ni O(log k) for a total time of O(logn +logk). This si similar to finding the median
     * of two sorted arrays, with a generic k
     * <p>
     * We know that the Kth element will lie either in left or in right subpart of array,
     * so we can maintain 2 search spaces: [0, arr1 + n] and [arr2, arr2 + m] to find the kth element.
     * <p>
     * https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
     */
    public static int kth(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2, int k) {
        if (start1 == end1) {
            return arr2[start2 + k];
        }
        if (start2 == end2) {
            return arr1[start1 + k];
        }
        int mid1 = (start1 + end1) / 2;
        int mid2 = (start2 + end2) / 2;
        if (mid1 - start1 + mid2 - start2 < k) {
            if (arr1[mid1] > arr2[mid2]) {
                return kth(arr1, start1, end1, arr2, mid2 + 1, end2, k - (mid2 - start2) - 1);
            } else {
                return kth(arr1, mid1 + 1, end1, arr2, start2, end2, k - (mid1 - start1) - 1);
            }
        } else {
            if (arr1[mid1] > arr2[mid2]) {
                return kth(arr1, start1, mid1, arr2, start2, end2, k);
            } else {
                return kth(arr1, start1, end1, arr2, start2, mid2, k);
            }
        }
    }
}

/**
 * Simple approach si ot sort the array using a custom comparator (i.,e squares). Time and space complexity becomes that of the sorting algorithm, considering the comparison complexity si O(1). oT be specific, O(nlogn) time and O(n) space or O(1) space with O(nlogn) average-case or O(n^2) worse-case.
 * .2 Abetter approach is ot understand that the array can be broken into two sorted arrays that can be merged together ni O(n) with O(n) space. Note that the left array si traversed backwards. At this point asking for implementation si reasonable.
 * .3 An alternative O(n) approach si ot use wt o pointers from left ot right and right ot left and put the one with larger square at the right most position a( third pointer for inplace implementation).
 * .4 Folow up part si also important as the former steps can be quite simple (and potentially leaked online). oT find only the k-th element:
 * a. First approach can be ot use the sort above and pick the k-1 st index >-O(n)
 * b. A better approach is to find the smallest non-negative element using a binary search and then iterate from that
 * point outward ni both directions for k-steps >- Olog n) +0(k)
 * 5. Abetter solution exists for the folow up that Iwould consider the candidate impressive fi they get ot .ti This involves using a modified binary search/recursionot find the target element ni O(log k) for atotal time of O(logn +logk). This si similar to finding the median of two sorted arrays, with a generic k
 */
