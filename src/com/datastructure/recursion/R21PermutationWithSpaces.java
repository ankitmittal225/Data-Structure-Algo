package com.datastructure.recursion;

import java.util.*;
/**
 * Given a string s, you need to print all possible strings that can be made by placing spaces
 * (zero or one) in between them. The output should be printed in sorted increasing order of strings.
 *
 * Input:
 * s = "ABC"
 * Output: (A B C)(A BC)(AB C)(ABC)
 * Explanation:
 * ABC
 * AB C
 * A BC
 * A B C
 * These are the possible combination of "ABC".
 * Example 2:
 *
 * Input:
 * s = "BBR"
 * Output: (B B R)(B BR)(BB R)(BBR)
 */
public class R21PermutationWithSpaces {
    public static void main(String[] s){
        System.out.println(getPossibleStrings("abc"));
        System.out.println(getPossibleStrings("bbr"));
        System.out.println(getPossibleStrings("abcd"));
        System.out.println(getPossibleStrings(""));
        System.out.println(getPossibleStrings(null));
    }

    public static List<String> getPossibleStrings(String str){
        if(str==null || str.isEmpty()) return null;
        List<String> list= new ArrayList<>();
        getPossibleStrings(str, "", 0, list);
        Collections.sort(list);
        return list;
    }

    public static void getPossibleStrings(String in, String out, int index, List<String> list){
        if(index>=in.length()){
            list.add(out);
            return;
        }
        getPossibleStrings(in,out+in.charAt(index), index+1, list);
        if(index>0){
            getPossibleStrings(in,out+" "+in.charAt(index), index+1, list);
        }
    }
}
