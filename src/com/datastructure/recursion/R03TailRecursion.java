package com.datastructure.recursion;

public class R03TailRecursion {

    public static void main(String[] args) {
        R03TailRecursion.print1toN(100);
    }

    public static void print1toN(int n) {
        if(n==0) return;
        System.out.print(n+" ");
        printNto1(n-1);
    }

    //this method is non tail recursive
    public static void printNto1(int n) {
        if(n==0) return;
        print1toN(n-1);
        System.out.print(n+" ");

    }

    public static void printNto1TailRecursive(int n,int k) {
        if(n==0) return;
        System.out.print(k+" ");
        printNto1TailRecursive(n-1,k+1);

    }
}
/*
Here method printNto1 will take less time than print1toN in modern compiler.Let take an example to see execution flow

    print1toN(3)
        print 3
        print1toN(2)
            print 2
            print1toN(1)
                print 1
                print1toN(0)

     here when print1toN(0) finishes it gives control back to print1toN(1) and print1toN(1) immediately finsihes because it has nothing to do
     so print1toN(1) finishes immidiatly after print1toN(0) finish.
     A recusive function is called tail recursive when the parent function has nothing more to do when the child function is finished.
     A function is tail recursive when last thing that happen in the function is recursive call & nothing after that

    printNto1(3)
        printNto1(2)
            printNto1(1)
                printNto1(0)
                1
            2
        3
    printNto1 is not tail recursive because when printNto1(0) finishes printNto1(1) not immdiately finishes becuase it has something more to do.
    when caller has something more to do after chilld call than its not tail recursive


    Why tail recursion is faster because your caller doesn't have to save the state because in recursion or function call what happen is caller
    state is saved than called function is called and once the called function is finished the caller resumes execution from the same point
    we don't need to resume the execution  here at all there is no point asuming the execution and thats what optimization the compilermodern
    compilers do. so when modern sees such a recursive or tail recursive function what they do is they realise the last thing is recursion code
    ,its a tail recursive code so they change this function.

    the changes that the make are they change this line print1toN to n=n-1 and than they add goto start and add start at begin
    public static void print1toN(int n) {
        start :
            if(n==0) return;
            System.out.print(n+" ");
            n=n-1;
            goto start;
    so they remove recursion completely

    You cant convert every non-tail recursive to tail recursive like quick sort & merge sort

    is this tail recursive?
    public static void fact(int n) {
        if(n==0 || n==1) return 1;
        return n*fact(n-1);
    }

    No because after function call its getting multilied with caller method value. here is the tail recursive solution
    //initially pass K as 1 
    public static void fact(int n, int k) {
        if(n==0 || n==1) return k;

        return fact(n-1,n*k);
    }
 */
