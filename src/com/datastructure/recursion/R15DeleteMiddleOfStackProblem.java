package com.datastructure.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class R15DeleteMiddleOfStackProblem {

    public static void main(String[] args) {
        R15DeleteMiddleOfStackProblem r14= new R15DeleteMiddleOfStackProblem();
        Deque<Integer> stack = new ArrayDeque<>(Arrays.asList(10, 5, 2, 3, 6));
        Deque<Integer> stack1 = new ArrayDeque<>(Arrays.asList(10, 5, 3, 6));
        Deque<Integer> stack3 = new ArrayDeque<>(Arrays.asList(10));
        Deque<Integer> stack2 = new ArrayDeque<>();


        r14.delete(stack);
        System.out.println(stack);
        r14.delete(stack1);
        System.out.println(stack1);
        r14.delete(stack2);
        System.out.println(stack2);
        r14.delete(stack3);
        System.out.println(stack3);
    }


    public void delete(Deque<Integer> stack) {
        if (stack == null || stack.size() < 1) return;
        deleteMid(stack, (stack.size()/2)+1);
    }

    public void deleteMid(Deque<Integer> stack, int mid) {
        if (mid==1) {
            stack.pop();
            return;
        }
        int last = stack.pop();
        deleteMid(stack, mid-1);
        stack.push(last);
    }
}