package com.datastructure.recursion;

public class R11Josephusproblem {

    /*
    A number of people are standing in a circle waiting to be executed.
    Counting begins at a specified point in the circle and proceeds around the circle in a specified direction.
    After a specified number of people are skipped, the next person is executed. The procedure is repeated with the remaining people,
    starting with the next person, going in the same direction and skipping the same number of people, until only one person remains,
    and is freed.
    The problem—given the number of people, starting point, direction, and number to be skipped—is to choose the position in the initial
    circle to avoid execution.
     */
    public static void main(String[] args) {

        System.out.println(R11Josephusproblem.jose(5,3));

    }

    private static int jose(int n, int k) {
        if(n==1) return 0;
        return (jose(n-1,k)+k)%n;
    }



}
