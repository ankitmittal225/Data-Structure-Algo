package com.datastructure.array;

//In a sorted Array
public class A20MinimumConsecutiveFlips {

    //Minimum consecutive flips to make all the element same either you can flip all zero or all one not both
    public static void main(String[] args) {
        int[] arr1=new int[]{1,1,0,0,0,1};// either we can flip 1(2times 0-1 & 5) or 0 (1 time 2-4)
        int[] arr2=new int[]{1,0,0,0,1,0,0,1,1,1,1}; //either we can flip 1(3 times 0,4,7-10) or 0(2 time 1-3 & 5-6)

        A20MinimumConsecutiveFlips.getMin(arr1);
        A20MinimumConsecutiveFlips.getMin(arr2);
    }

    /*'

     */
    private static void getMin(int[] arr) {
        int count1=0,count0=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[i-1]) {
                if (arr[i] == 1) count1++;
                else count0++;
            }
        }
        int val=count0>count1?1:0,ind=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]==val && arr[i-1]!=val){
                ind=i;
            }
            if(arr[i]!=val && arr[i-1]==val){
                System.out.println(" From "+ind+" to "+(i-1));
            }
        }


        // second approach
        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[i-1]) {
                if(arr[i]!=arr[0]) System.out.println("From "+i+" to ");
                else {
                    System.out.print(i-1);
                    System.out.println();
                }
            }
        }
        if(arr[arr.length-1]!=arr[0]) {
            System.out.print(arr.length-1);
            System.out.println();
        }

    }

    /*'
    Using Moore's voting algorithm
    idea is to
    1.check if there is any number apprear multiple time
    2. count how many time it appears & check if its greater than n/2
    8,3,4,8,8
    */


}
