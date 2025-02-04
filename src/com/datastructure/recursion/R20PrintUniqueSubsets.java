package com.datastructure.recursion;

import java.util.*;

/**
 * Given an array of integers that might contain duplicates, return all possible subsets.
 * substring < Subsequence < powerset
 * suppose we have a set = {a,b,c}
 * power set : {"", a,b,c,ab,bc,ac,abc}
 *
 * Substring: Continuous : {"", a,b,c,ab,bc,abc}: ac is not substring
 * subsequence: non-Continuous but order should be maintained : {"", a,b,c,ab,bc,ac,abc} : ca won't be subsequence
 * subset : every possible combination : ac as well ca both work no sequence order matter
 */
public class R20PrintUniqueSubsets {
    public static void main(String[] s){
        System.out.println(getUniqueSubset("abc"));
        System.out.println(getUniqueSubset("ac"));
        System.out.println(getUniqueSubset("abcd"));
        System.out.println(getUniqueSubset(""));
        System.out.println(getUniqueSubset(null));
    }

    public static Set<String> getUniqueSubset(String str){
        if(str==null || str.isEmpty()) return null;
        Set<String> set= new HashSet<>();
        getSubSet(str, "", 0, set);
        return set;
    }

    public static void getSubSet(String in, String out, int index, Set<String> set){
        if(index==in.length()){
            set.add(out);
            return;
        }
        getSubSet(in,out, index+1, set);
        getSubSet(in,out+in.charAt(index), index+1, set);
    }
}
