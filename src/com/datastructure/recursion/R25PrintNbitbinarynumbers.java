package com.datastructure.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer N, print all N bit binary numbers having more 1’s than 0’s for any prefix of the number.
 *
 * Input:
 * 2
 * 2
 * 3
 * Output:
 * 11 10
 * 111 110 101
 */
public class R25PrintNbitbinarynumbers {
    public static void main(String[] s){
        System.out.println(getPossibleStrings(4));
        System.out.println(getPossibleStrings(3));
        System.out.println(getPossibleStrings(2));
        System.out.println(getPossibleStrings(6));
        System.out.println(getPossibleStrings(0));
    }

    public static List<String> getPossibleStrings(int count){
        if(count<1) return null;
        List<String> list= new ArrayList<>();
        getPossibleStrings(count, 0,0, "", list);
        return list;
    }

    public static void getPossibleStrings(int count, int count1,int count0,String out, List<String> list){
        if(count1+count0==count){
            list.add(out);
            return;
        }
        getPossibleStrings(count,count1+1,count0,out+"1", list);
        if(count1>count0 ){

            getPossibleStrings(count,count1,count0+1,out+"0", list);
        }
    }
}
