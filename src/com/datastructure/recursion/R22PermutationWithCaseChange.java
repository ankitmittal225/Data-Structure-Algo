package com.datastructure.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Print all permutations of a string keeping the sequence but changing cases.
 *
 * Examples:
 *
 * Input : ab
 * Output : AB Ab ab aB
 *
 * Input : ABC
 * Output : abc Abc aBc ABc abC AbC aBC ABC
 */
public class R22PermutationWithCaseChange {
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
        getPossibleStrings(str, "",0, list);
        Collections.sort(list);
        return list;
    }

    public static void getPossibleStrings(String in, String out,int index, List<String> list){
        if(index>=in.length()){
            list.add(out);
            return;
        }
        char c= in.charAt(index);
        getPossibleStrings(in,out+c,index+1, list);
        getPossibleStrings(in,out+Character.toUpperCase(c),index+1, list);

    }
}
