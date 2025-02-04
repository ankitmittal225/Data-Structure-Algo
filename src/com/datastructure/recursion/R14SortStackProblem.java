package com.datastructure.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class R14SortStackProblem {

    public static void main(String[] args) {
        R14SortStackProblem r14= new R14SortStackProblem();
        Deque<Integer> stack = new ArrayDeque(Arrays.asList(10, 5, 2, 3, 6));
        Deque<Integer> stack1 = new ArrayDeque();
        Deque<Integer> stack2 = new ArrayDeque(Arrays.asList(1, 1, 1, 1, 1));


        r14.sort(stack);
        System.out.println(stack);
        System.out.println(stack.peek());
        r14.sort(stack1);
        System.out.println(stack1);
        System.out.println(stack1.peek());
        r14.sort(stack2);
        System.out.println(stack2);
        System.out.println(stack2.peek());
    }


    public void sort(Deque<Integer> stack) {
        if (stack == null || stack.size() <= 1) return;
        int val = stack.pop();
        sort(stack);
        insert(stack, val);
    }

    public void insert(Deque<Integer> stack, int val) {
        if (stack.isEmpty() || stack.peek() <= val) {
            stack.push(val);
            return;
        }
        int last = stack.pop();
        insert(stack, val);
        stack.push(last);
    }
}