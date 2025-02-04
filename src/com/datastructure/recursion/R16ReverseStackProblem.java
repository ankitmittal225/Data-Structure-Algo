package com.datastructure.recursion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class R16ReverseStackProblem {

    public static void main(String[] args) {
        R16ReverseStackProblem r14= new R16ReverseStackProblem();
        Deque<Integer> stack= new ArrayDeque<>(Arrays.asList(10,5,2,3,6));
        Deque<Integer> stack1= new ArrayDeque<>(Arrays.asList(1,1,1,1,1));
        Deque<Integer> stack2= new ArrayDeque<>();
        Deque<Integer> stack3= new ArrayDeque<>(Arrays.asList(10));
        Deque<Integer> stack4= new ArrayDeque<>(Arrays.asList(10,11));


        System.out.println(stack);
        r14.reverse(stack);
        System.out.println(stack);
        System.out.println(stack1);
        r14.reverse(stack1);
        System.out.println(stack1);
        System.out.println(stack2);
        r14.reverse(stack2);
        System.out.println(stack2);
        System.out.println(stack3);
        r14.reverse(stack3);
        System.out.println(stack3);
        System.out.println(stack4);
        r14.reverse(stack4);
        System.out.println(stack4);

    }

    public void reverse(Deque<Integer> stack){
        if(stack==null || stack.size()<=1) return;
        int num= stack.pop();
        reverse(stack);
        insert(stack, num);
    }

    public void insert(Deque<Integer> stack, int num){
        if(stack.isEmpty()){
            stack.push(num);
            return;
        }
        int last= stack.pop();
        insert(stack, num);
        stack.push(last);
    }
}