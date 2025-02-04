package com.datastructure.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*n.
 *
 * For example, given n = 3, a solution set is:
 *
 * "((()))", "(()())", "(())()", "()(())", "()()()" .
 */
public class R24GenerateAllBalancedParanthesis {
    public static void main(String[] s){
        System.out.println(getPossibleStrings(4));
        System.out.println(getPossibleStrings(3));
        System.out.println(getPossibleStrings(2));
        System.out.println(getPossibleStrings(1));
        System.out.println(getPossibleStrings(0));
    }

    public static List<String> getPossibleStrings(int count){
        if(count<1) return null;
        List<String> list= new ArrayList<>();
        getPossibleStrings(count, count, "", list);
        return list;
    }

    public static void getPossibleStrings(int open, int close,String out, List<String> list){
        if(open==0 && close==0){
            list.add(out);
            return;
        }
        if(open>0){
            getPossibleStrings(open-1,close,out+"(", list);
        }
        if(open<close ){

            getPossibleStrings(open,close-1,out+")", list);
        }
    }
}
