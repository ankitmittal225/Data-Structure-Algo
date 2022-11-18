package com.datastructure.array;

//In a sorted Array
public class A19MajorityElement {

    //Majority element are those who appeared more than n/2 times in array.
    public static void main(String[] args) {
        int[] arr1=new int[]{8,3,4,8,8}; // index of 8 5/2=3 it apprears more than n/2 so any index of 8 like 0,3,4
        int[] arr2=new int[]{3,7,4,7,7,5};//since 7 is exactly n/2=3 not more than that so it will return -1
        int[] arr3=new int[]{20,30,40,50,50,50,50};
        int[] arr4=new int[]{6,8,4,8,8};

        System.out.println(A19MajorityElement.getMax2(arr1));
        System.out.println(A19MajorityElement.getMax2(arr2));
        System.out.println(A19MajorityElement.getMax2(arr3));
        System.out.println(A19MajorityElement.getMax2(arr4));
    }

    /*'
    for example [10,5,-5]
    normal subset : {10},{5},{-5}, {10,5}, {5,-5},{10,5,-5}
    circular : {5,-5,10},{-5,10},{-5,10,5}
     */
    private static int getMax(int[] arr) {
        int count=0;
        for(int i=0;i<arr.length;i++){
            count=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==arr[j]) count++;
            }
            if(count>arr.length/2 ) {
                return i;
            }
        }
        return -1;
    }

    /*'
    Using Moore's voting algorithm
    idea is to
    1.check if there is any number apprear multiple time
    2. count how many time it appears & check if its greater than n/2
    8,3,4,8,8
    */
    private static int getMax2(int[] arr) {
        //1.check if there is any number apprear multiple time
        int count=1,res=0;
        for(int i=1;i<arr.length;i++){
            if(arr[res]==arr[i]) count++;
            else count--;
            if(count==0){
                res=i;
                count=1;
            }
        }
        //2. count how many time it appears & check if its greater than n/2
        count=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[res]==arr[i]) count++;
        }
        return count>(arr.length/2)?res:-1;
    }



}
