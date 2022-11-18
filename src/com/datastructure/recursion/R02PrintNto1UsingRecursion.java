package com.datastructure.recursion;

public class R02PrintNto1UsingRecursion {
        public static void main(String[] args) {
            R02PrintNto1UsingRecursion.print(100);
        }

        public static void print(int n) {
            if(n==0) return;
            System.out.print(n+" ");
            print(n-1);
        }

}

/*
Time Complexity : theta(n)
Auxiliary Space : BigO(n)  (because it has to save the state of caller method to start from there)
however taking about concept of tail call elimination & if your compiler is modern compiler than auxiliary space will be reduce to theta(1).
 */
