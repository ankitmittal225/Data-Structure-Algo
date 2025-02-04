package com.datastructure.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 */
public class R23LetterCasePermutation {
    public static void main(String[] s){
        System.out.println(getPossibleStrings("a1b2"));
        System.out.println(getPossibleStrings("3z4"));
        System.out.println(getPossibleStrings("12345"));
        System.out.println(getPossibleStrings(""));
        System.out.println(getPossibleStrings(null));
    }

    public static List<String> getPossibleStrings(String str){
        if(str==null || str.isEmpty()) return null;
        List<String> list= new ArrayList<>();
        getPossibleStrings(str, "",0, list);
//        Collections.sort(list);
        return list;
    }

    public static void getPossibleStrings(String in, String out,int index, List<String> list){
        if(index>=in.length()){
            list.add(out);
            return;
        }
        char c= in.charAt(index);
        if(Character.isLetter(c)) {
            getPossibleStrings(in, out + c, index + 1, list);
            getPossibleStrings(in, out + Character.toUpperCase(c), index + 1, list);
        }
        else{
            getPossibleStrings(in, out + c, index + 1, list);
        }

    }
}
