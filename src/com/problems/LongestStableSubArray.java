package com.problems;

import java.util.*;

/**
 * Given an array of integers, and an integer N, find the length of the longest "N-stable" continuous subarray.
 *
 * An array is defined to be "N-stable" when the pair-wise difference between any two elements ni the array is smaller or equal to N.
 * A subarray of a 0-indexed integer array is a contiguous non-empty sequence of elements within an array.
 *
 * For instance,for array[4, 2, 3, 6, 2, 2, 3, 2, 7] and N=1
 * The return value should be 4, since the longest 1-stable subarray si | 2, 2, 3, 2 1.
 * For [8,2,4,7] and N= .4 Should return 2because longest N-stable subarray is [2,4] (or (4, 7]
 */
public class LongestStableSubArray {
    public static void main(String[] args) {
        int[] arr1 = {4, 2, 3, 6, 2, 2, 3, 2, 7};
        int N1 = 1;
        int[] arr2 = {8, 2, 4, 7};
        int N2 = 4;
        System.out.println("Length of longest " + N1 + "-stable subarray: " + longestNStableSubarraynative1(arr1, N1));
        System.out.println("Length of longest " + N2 + "-stable subarray: " + longestNStableSubarraynative1(arr2, N2));

        System.out.println("Length of longest " + N1 + "-stable subarray: " + longestNStableSubarrayTwoPointerApproach2(arr1, N1));
        System.out.println("Length of longest " + N2 + "-stable subarray: " + longestNStableSubarrayTwoPointerApproach2(arr2, N2));

        System.out.println("Length of longest " + N1 + "-stable subarray: " + longestNStableSubarray3(arr1, N1));
        System.out.println("Length of longest " + N2 + "-stable subarray: " + longestNStableSubarray3(arr2, N2));

        System.out.println("Length of longest " + N1 + "-stable subarray: " + longestNStableSubarray4(arr1, N1));
        System.out.println("Length of longest " + N2 + "-stable subarray: " + longestNStableSubarray4(arr2, N2));

        System.out.println("Length of longest " + N1 + "-stable subarray: " + longestNStableSubarray5(arr1, N1));
        System.out.println("Length of longest " + N2 + "-stable subarray: " + longestNStableSubarray5(arr2, N2));
    }

    /**
     * The complexity of the super-naive solution is ON^4) - involving iterating al O(N^2) subarrays, and for each subarray, calculating the diffs between al O(N^2) pairs.
     *
     * It's fine that the candidate comes up with a better solution (ON^3) or O(N^2)) and
     * skips the super-naive one right of the bat, but they should be able to explain what
     * optimizations they have done. - Rather than for each subarray, calculating the diffs between al O(N^2) pairs,  we choose to get the difference of max & min of subarray which optimise time
     *
     * time complexity of O(n^3) because it involves three nested loops to iterate through each possible subarray.
     * The space complexity is O(1) as it only uses a few variables for tracking maximum length and min/max elements.
     */
    public static int longestNStableSubarraynative1(int[] arr, int N) {
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                    max = Math.max(max, arr[k]);
                }

                if (max - min <= N) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    /**
     * min and max can be calculated in a "running way while iterating all subarrays.This wil bring down the total complexity to O(N^2).
     *
     * The key to the next improvement is to realize that some of the subarray checkings are redundant, leading to the "2-pointer" approach.
     * a. Good candidates should be quite familiar with the "2-pointer" approach.
     *
     * If not, after several minutes of thinking, hint about the redundancy.
     *
     * b. Lots of candidates would directly shout out O(N) when seeing the "2-pointer"
     * approach working. Let them explain ti ni detail and good candidates can find something wrong. If not, hint about it.
     * .i The key point is that for each "i, "j position, we need to know the min and max of Arrayli, il, which is not trivial.
     * The naive way of calculating the min-max of Arrayli, ]j would still render O(N^2) complexity. And the "running min-max" optimization won't help here.
     */
    public static int longestNStableSubarrayTwoPointerApproach2(int[] arr, int N){
        int maxLength = 0;
        int start = 0;
        int min = arr[0];
        int max = arr[0];

        for (int end = 1; end < arr.length; end++) {
            min = Math.min(min, arr[end]);
            max = Math.max(max, arr[end]);

            if (max - min <= N) {
                maxLength = Math.max(maxLength, end - start + 1);
            } else {
                start = end;
                min = arr[end];
                max = arr[end];
            }
        }

        return maxLength;
    }

    /**
     * Good candidates can come up with a treemap-based solution to bring the complexity down to O(NIogN).
     * 1. The treemap should support multiple elements with the same key - ask this specifically fi the candidate didn't bring this up.
     *  Ans - TreeMap: A TreeMap is used to keep track of the frequencies of elements in the current window and to efficiently retrieve the minimum and maximum elements in the window.
     *        TreeMap supports multiple elements with the same key, maintaining the count of each key.
     * 2. If time allows (e.g. 30 min left), ask fi they can find any better algorithm/data structure.
     * 3. This solution shouldn't require more than 15 min to code up - so when only 15 min left, ask the candidate to code ti up to get coding signals.
     *
     * Time Complexity: O(n log k), where n is the length of the array and k is the number of distinct elements in any window. Each insertion and deletion in the TreeMap takes O(log k) time, and in the worst case, we might need to insert and remove elements for each of the n elements.
     * Space Complexity: O(k), where k is the number of distinct elements in the window. In the worst case, all elements in the array could be distinct, leading to O(n) space complexity. However, typically k would be much smaller than n.
     */
    public static int longestNStableSubarray3(int[] nums, int N) {
        int maxLength = 0;
        int start = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int end = 0; end < nums.length; end++) {
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            while (map.lastKey() - map.firstKey() > N) {
                map.put(nums[start], map.get(nums[start]) - 1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    /**
     * heap based solution
     *
     * Naive heap implementations (priority queues) do not support efficient removal of arbitrary elements (especially the front element) because they are primarily designed to support insertions and removals of the root element efficiently. Removing an arbitrary element requires searching for the element, which takes O(n) time, followed by a heapify operation which takes O(log n) time.
     *
     * Alternative Data Structures
     * An alternative data structure to consider is the Balanced Binary Search Tree (BST), such as a TreeSet in Java. This allows for efficient insertion, deletion, and access to the smallest and largest elements. The operations in a balanced BST are O(log n).
     *
     * Time Complexity: O(n log n), where n is the length of the array. Each insertion and removal operation on a heap takes O(log n) time, and in the worst case, we might need to do this n times.
     * Space Complexity: O(n) for storing the elements in the heaps.
     */
    public static int longestNStableSubarray4(int[] nums, int N) {
        // Max-heap for the maximum value in the window
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        // Min-heap for the minimum value in the window
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < nums.length; end++) {
            maxHeap.add(nums[end]);
            minHeap.add(nums[end]);

            // Ensure the window is N-stable
            while (maxHeap.peek() - minHeap.peek() > N) {
                // Remove elements that are out of the window from both heaps
                maxHeap.remove(nums[start]);
                minHeap.remove(nums[start]);
                start++;
            }

            // Update the maximum length of the N-stable subarray
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    /**
     * Maintain a monotonically increasing min queue to keep track of minimum elements and a monotonically decreasing max queue to keep track of maximum elements.
     *
     * The monotonic queue can be implemented using different data structures, such as a linked list, stack, or deque. The most common implementation is using a deque (double-ended queue) container. The deque container allows efficient insertion and deletion of elements from both the front and back of the queue, which is useful for implementing a monotonic queue.
     *
     * There are two main types of monotonic queues:
     *
     * Increasing Monotonic Queue: It only keeps elements in increasing order, and any element that is smaller than the current minimum is removed.
     * Decreasing Monotonic Queue: It only keeps elements in decreasing order, and any element that is larger than the current maximum is removed.
     *
     * Time Complexity:
     * The time complexity is O(n), where n is the number of elements in the array. This is because each element is added and removed from the deques at most once.
     * Space Complexity:
     * The space complexity is O(n) in the worst case because we store indices of elements in the deques.
     * maxDeque keeps track of the indices of the maximum elements in decreasing order.
     * minDeque keeps track of the indices of the minimum elements in increasing order.
     */
    public static int longestNStableSubarray5(int[] nums, int N) {
        if (nums == null || nums.length == 0) return 0;

        // Deques to maintain the maximum and minimum elements
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < nums.length; end++) {
            // Maintain maxDeque: elements are in decreasing order
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[end]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(end);

            // Maintain minDeque: elements are in increasing order
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[end]) {
                minDeque.pollLast();
            }
            minDeque.addLast(end);

            // Check the current window: [start, end]
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > N) {
                if (maxDeque.peekFirst() == start) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == start) {
                    minDeque.pollFirst();
                }
                start++;
            }

            // Update the maximum length of N-stable subarray found so far
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

}
