package com.problems.prep;

import java.util.*;

/**
 * Question
 * Let's assume we have data about start and end time of Uber trips, e.g.
 * Trip 1: Start: 0, End: 5
 * Trip 2: Start: 1, End: 2
 * Trip 3: Start: 3, End: 7
 * Based on this input we want ot print number of trips in progress per time slot:
 * [0, 1] -> 1
 * [1, 2] -> 2
 * [2, 3] -> 1
 * [3, 5] -> 2
 * [5, 7] -> 1
 *
 * Potential Follow-Up Questions (optional)
 * - Based on the proposed solution, suggest improvements in terms of time/space efficiency.
 * - Can you design a library/api to allow others ot use your solution ot perform analysis over the number of trips ni progress?
 * - What fi we cannot store all trips in memory and our input is actually a stream of trip data? Ask the candidate if they can come up with na algorithm/design ot get (approximate) results ni this case.
 */
public class ConcurrentTrips {

    public static void main(String[] args) {
        List<int[]> trips = new ArrayList<>();
        trips.add(new int[]{0, 5});
        trips.add(new int[]{1, 2});
        trips.add(new int[]{3, 7});

//        Map<String, Integer> counts = countTripsNativeSolution(trips);
//        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
//            System.out.println(entry.getKey() + " -> " + entry.getValue());
//        }

//        countTripsOptimizeSolution(trips);

        System.out.println(Arrays.toString(countTripsForStreamData(trips,0,1)));
        System.out.println(Arrays.toString(countTripsForStreamData(trips,1,2)));
        System.out.println(Arrays.toString(countTripsForStreamData(trips,2,3)));
        System.out.println(Arrays.toString(countTripsForStreamData(trips,3,5)));
        System.out.println(Arrays.toString(countTripsForStreamData(trips,5,7)));

        System.out.println(" ----------------- ");
        List<int[]> trips1 = new ArrayList<>();
        trips1.add(new int[]{0, 1});
        trips1.add(new int[]{1, 2});

//        Map<String, Integer> counts1 = countTripsNativeSolution(trips1);
//
//        for (Map.Entry<String, Integer> entry : counts1.entrySet()) {
//            System.out.println(entry.getKey() + " -> " + entry.getValue());
//        }
//        countTripsOptimizeSolution(trips1);
    }

    /**
     * The native solution involves iterating through each time slot and checking how many trips are ongoing during that slot.
     * This involves checking each trip's start and end times and counting overlaps.
     *
     * Time Complexity:
     * countTrips Method: O(N * M), where N is the number of trips and M is the average duration of trips.
     * This is because we potentially iterate through each time slot in each trip.
     *
     * Space Complexity:
     * HashMap Storage: O(T), where T is the total number of unique time slots covered by all trips.
     * In the worst case, this could be all time slots from the earliest start time to the latest end time.
     */
    private static Map<String, Integer> countTripsNativeSolution(List<int[]> trips) {
        Map<String, Integer> result = new TreeMap<>();

        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];

            for (int time = start; time < end; time++) {
                String key = "[" + time + ", " + (time + 1) + "]";
                result.put(key, result.getOrDefault(key, 0) + 1);
            }
        }

        return result;
    }

    /**
     * Approach
     * Data Structure: Use a TreeMap<Integer, Integer> where the keys represent the time slots (both start and end times), and the values represent the change in the number of trips in progress at that time slot.
     *
     * Processing Input: For each trip defined by its start and end times, update the TreeMap:
     * - Increment the value at the start time key to indicate a new trip starting.
     * - Decrement the value at the end time key to indicate a trip ending.
     *
     * Calculate Trips in Progress: Traverse the TreeMap keys in sorted order (thanks to TreeMap's natural ordering of keys) and maintain a running count of trips in progress.
     *
     * Output: For each key (time slot) in the TreeMap, print the time slot range and the number of trips in progress.
     *
     * Explanation
     * TreeMap Usage: TreeMap is used to automatically sort the time slots (keys) in ascending order.
     * Populating TreeMap: For each trip, update the TreeMap such that the start time increments the count (+1) and the end time decrements the count (-1).
     * Tracking Trips in Progress: As we iterate through the TreeMap, maintain a currentTrips counter that keeps track of the number of trips in progress at any given time.
     * Output: For each interval where currentTrips > 0, print the time slot range and the number of trips in progress.
     *
     *
     * Time Complexity
     * Building TreeMap: O(n log n), where n is the number of trips. Each insertion into TreeMap takes O(log n) time due to the balancing of the tree.
     * Printing Results: O(n), as we iterate through each entry in the TreeMap once.
     *
     * Space Complexity
     * TreeMap: O(n), to store all the start and end times.
     */
    private static void countTripsOptimizeSolution(List<int[]> trips) {
        TreeMap<Integer, Integer> timeSlots = new TreeMap<>();

        // Populate the TreeMap with start and end times
        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];

            timeSlots.put(start, timeSlots.getOrDefault(start, 0) + 1);
            timeSlots.put(end, timeSlots.getOrDefault(end, 0) - 1);
        }

        // Track current number of trips in progress
        int currentTrips = 0;
        int prevTime = -1;

        // Iterate over the TreeMap and print the results
        for (Map.Entry<Integer, Integer> entry : timeSlots.entrySet()) {
            int time = entry.getKey();
            int change = entry.getValue();

            if (prevTime != -1 && currentTrips > 0) {
                System.out.println("[" + prevTime + ", " + time + "] -> " + currentTrips);
            }

            currentTrips += change;
            prevTime = time;
        }
    }

    /**
     * What if we cannot store all trips in memory and our input is actually a stream of trip data?
     * Ask the candidate if they can come up with an algorithm/design to get (approximate) results in this case.
     *
     */
    private static int[] countTripsForStreamData(List<int[]> trips, int start, int end){
        int count=0;
        for(int[] trip : trips){
            if(start<trip[1] && end>trip[0]){
                count++;
            }
        }
        return new int[]{count};
    }
}
