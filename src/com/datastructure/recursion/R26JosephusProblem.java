package com.datastructure.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Josephus Problem | Game of Death in a circle | Execution in Circle
 *
 * There are n people standing in a circle (numbered clockwise 1 to n) waiting to be executed.
 * The counting begins at point 1 in the circle and proceeds around the circle in a fixed direction
 * (clockwise). In each step, a certain number of people are skipped and the next person is executed.
 * The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are
 * removed), until only the last person remains, who is given freedom.
 * Given the total number of persons n and a number k which indicates that k-1 persons are skipped and kth person
 * is killed in circle. The task is to choose the place in the initial circle so that you are the last one
 * remaining and so survive.
 * Consider if n = 5 and k = 2, then the safe position is 3.
 * Firstly, the person at position 2 is killed, then person at position 4 is killed, then person at position
 * 1 is killed. Finally, the person at position 5 is killed. So the person at position 3 survives. .
 *
 *
 * Using IBH  : Induction- Hypothesis-base condition
 *
 *
 * Recursive Solution
 * The safe position can be determined mathematically using the following recurrence relation:
 *
 * Base Case: If there is only one person, they are the survivor:
 *      Josephus(1,k)=0 (0-based index)
 * Recursive Case: For more than one person:
 *  Josephus(n,k)=(Josephus(n−1,k)+k)mod n
 * Here, n is the number of people in the circle,
 * k is the step size, and the result is a 0-based index. To convert it to a 1-based index (common in problem statements), add 1 to the result.
 */
public class R26JosephusProblem {
    public static void main(String[] s){
        System.out.println(getPositionOfSurviver(5, 2));
        System.out.println(getPositionOfSurviver(40, 7));
        System.out.println(getPositionOfSurviver(2,5));
        System.out.println(getPositionOfSurviverRecursion(5, 2));
        System.out.println(getPositionOfSurviverRecursion(40, 7));
        System.out.println(getPositionOfSurviverRecursion(2,5));
//        System.out.println(getPositionOfSurviver(6));
//        System.out.println(getPositionOfSurviver(0));
    }

    private static int getPositionOfSurviverRecursion(int N, int K) {
        if(N==1) return 1;
        else{
            return (getPositionOfSurviver(N-1,K)+K)% N;
        }
    }

    public static int getPositionOfSurviver(int N, int K){
        if(N<1) return 0;
        List<Integer> list= new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        return getPositionOfSurviver(N,K-1,0,list );

    }

    public static int getPositionOfSurviver(int N, int K,int index, List<Integer> list){
        if(list.size()==1){
            return list.get(0);
        }
        index=(index+K)%list.size();
        list.remove(index);
        getPositionOfSurviver(N,K,index, list);
        return list.get(0);
    }
}
