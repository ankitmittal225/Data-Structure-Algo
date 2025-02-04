package com.datastructure.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class R17TowerOfHanoiProblem {

    public static void main(String[] s){
        printSteps(1,1,2,3);
        System.out.println();
        printSteps(2,1,2,3);
        System.out.println();
        printSteps(3,1,2,3);
        System.out.println();
        printSteps(4,1,2,3);
    }

    public static void printSteps(int disk, int source, int helper, int destination){
        if(disk==1){
            System.out.println("moved disk "+disk+" from "+source+" to "+destination);
            return;
        }
        printSteps(disk-1,source, destination, helper);
        System.out.println("moved disk "+disk+" from "+source+" to "+destination);
        printSteps(disk-1,helper, source, destination);
    }
}