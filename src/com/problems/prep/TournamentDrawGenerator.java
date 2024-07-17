package com.problems.prep;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/*
Question
You are hosting a tennis tournament with many players participating.
All your players are pre-ranked based on their historical performance.
In an elimination tournament, the loser of each match-up is immediately eliminated from the tournament.
Each winner wil play another in the next round, until the final match-up,
whose winner becomes the tournament champion.

Part 1
Can you write a program ot predict the players in each round?
Your program should naively assume that a higher ranked player always wins against a lower ranked player.

Input
(1,2,3,4,5,6,7,8) /ranks of each player and hte match ups. The smaller het number, the higher the rank.

Output:
1,2, 3, 4, 5, 6, 7, 8 // round 1
1, 3, 5, 7 // round 2
1 5 //round :3
1 //round 4

Potential Follow-Up Questions (optional)
How do we make the generator works when participants counts is not a power of 2? :
Answer :To handle a number of participants that is not a power of 2, you can pad the remaining spots
with byes (automatic advancement to the next round). For example, in a 10-player tournament, 6 players could
 directly advance to the next round, and the remaining 4 could play matches among themselves.

How do we modify your solution to output all possible draws?
Answer : To output all possible draws, you can implement a backtracking algorithm that generates
permutations of the players' ranks. Each permutation would represent a valid draw. Ensure that each draw
meets the condition where higher-ranked players do not meet lower-ranked players in early rounds.

 */
public class TournamentDrawGenerator {

    public static void main(String[] args) {
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8};
        predictPlayerRoundWise(ranks);
        System.out.println(generateDrawPart2(8));
    }

    /**
     * Continues as long as there is more than one player in the currentRound.
     * Inside the loop, initializes an empty list nextRound to store players for the next round.
     * Iterates through currentRound in pairs (i.e., players in each match-up).
     * Determines the winner of each match-up based on their ranks (higher rank always wins).
     * Adds the winners to nextRound for the next iteration.
     *
     * Time Complexity
     * The time complexity of this approach is O(n log n), where n is the number of players.
     * This is because each round halves the number of players, and we perform
     * a linear scan to determine the winners of each match-up.
     *
     * Space Complexity
     * The space complexity is O(n), where n is the number of players.
     * This is primarily due to the storage of tournamentPlayers, currentRound, and nextRound lists,
     * each potentially containing all players at some point.
     * @param ranks
     */
    private static void predictPlayerRoundWise(int[] ranks) {
        List<Integer> tournamentPlayers = new ArrayList<>();
        for (int rank : ranks) {
            tournamentPlayers.add(rank);
        }

        List<Integer> currentRound = tournamentPlayers;
        int roundNumber = 1;

//        while (currentRound.size() > 1) {
//            List<Integer> nextRound = new ArrayList<>();
//            for (int i = 0; i < currentRound.size(); i += 2) {
//                int player1 = currentRound.get(i);
//                int player2 = currentRound.get(i + 1);
//                int winner = Math.min(player1, player2); // Higher rank always wins
//                nextRound.add(winner);
//            }
//            currentRound = nextRound;
//            System.out.println("Round " + roundNumber + ": " + currentRound);
//            roundNumber++;
//        }

        System.out.println("Predicting players in each round:");
        while (currentRound.size() > 1) {
            List<Integer> nextRound = new ArrayList<>();
            for (int i = 0; i < currentRound.size(); i += 2) {
                nextRound.add(currentRound.get(i));
            }
            currentRound = nextRound;
            System.out.println(currentRound);
        }
    }

    /**
     * Part 2
     * Can you create a draw generator that makes sure the better players do not meet in the earlier rounds?
     * to be more specific, a higher-ranked player should never be eliminated in an earlier round than someone
     * ranked lower according to your prediction in part .1
     * The input is a total number of players, output a possible draw
     *
     * Input :8
     * output: [1, 5, 3, 6, 2, 7, 4, 8]
     *
     * generateDraw Method:
     *
     * Takes an integer numPlayers as input.
     * Generates a draw array such that higher-ranked players are spread out across the draw.
     * Uses a two-pointer approach to distribute ranks from both ends towards the center.
     * Ensures that no higher-ranked player meets another higher-ranked player in earlier rounds.
     *
     * Time Complexity:
     * O(n), where n is the number of players. This is because each player's rank is assigned exactly once.
     *
     * Space Complexity:
     * O(n) for storing the draw array.
     */
    public static List<Integer> generateDrawPart2(int totalPlayers) {
        List<Integer> draw = new ArrayList<>(totalPlayers);
        int mid = totalPlayers / 2;
        int left = 0, right = mid;
        int index = 0;

        while (left < mid && right < totalPlayers) {
            draw.add(index++, left + 1);
            draw.add(index++,right + 1);
            left++;
            right++;
        }

        while (left < mid) {
            draw.add(index++,left + 1);
            left++;
        }

        while (right < totalPlayers) {
            draw.add(index++,right + 1);
            right++;
        }

        return draw;
    }

}
