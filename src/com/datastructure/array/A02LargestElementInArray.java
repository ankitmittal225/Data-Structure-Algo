package com.datastructure.array;

public class A02LargestElementInArray {

    public static void main(String[] args) {
        int[] arr=new int[]{10, 5, 2, 3, 6,0,0};
        System.out.println(A02LargestElementInArray.findMax(new int[]{10, 5, 20, 3, 6}));
        System.out.println(A02LargestElementInArray.find2ndMax(new int[]{10, 15, 20, 3, 6}));

    }

    private static int findMax(int[] ints) {
        if(ints.length==0) return -1;
        int max=0;
        for (int i=1;i<ints.length;i++){
            if(ints[i]>ints[max]) max=i;
        }
        return max;
    }

    /*
    Cases to remember to find second largest number
    1. current element is greater than max so far  : than max2=max;
                max = i;
    2. when new element is same as max : than ignore
    3. when new element is smaller than largest eg 5,8,12,7
        here 3 case comes in picture
            1. you have never seen second largest before eg: 12,12,12,7
                    max2=-1 now will be max2=i
            2. there was a second largest but current element is larger than second largest & smaller than largest Ex 12,8,12,9
                       a[i]>a[res] : max2=i
            3.there was a second largest but current element is smaller than second largest  Ex 12,8,12,7 (7 is smaller so ignore)
                    a[i]<=a[res] : ignore
     */
    private static int find2ndMax(int[] ints) {
        if(ints.length==0) return -1;
        int max=0,max2=-1;//if arr=[10,10,10] here is no second largest

        for (int i=1;i<ints.length;i++){
            if(ints[i]>ints[max]) {
                max2=max;
                max = i;
            }
            else if(ints[i]!=ints[max]){
                if(max==-1||ints[i]>ints[max2])
                    max2=i;
            }
        }
        return max2;
    }



}
