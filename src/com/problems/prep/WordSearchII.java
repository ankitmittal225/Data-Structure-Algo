package com.problems.prep;

import java.util.ArrayList;
import java.util.List;

/**
 * Question
 * Given a 2-dimensional grid of characters, and a dictionary, find al words ni the grid that also appear ni the dictionary. A word can be formed by traversing the grid by going either left, right, top, or down, but NOT diagonal. Also, a single grid position can not be used more than once ni a word.
 * For instance, ni the folowing 3x3 grid, with a dictionary of ( CAT, COPY, ASK, Sos )
 * C A T
 * O S K
 * P Y U
 * The first 3 words can be found ni the grid, but not sOS, since one cannot use S twice.
 *
 * Ask ? : can i keep the assumption that all character will be smallcase
 */
public class WordSearchII {

    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
//        System.out.println(findWordsUsingDFS(board, words));
        System.out.println(findWordsUsingTrie(board, words));

        char[][] board2 = {
                {'c','a','t'},
                {'o','s','k'},
                {'p','y','y'}
        };
        String[] words2 = {"cat", "copy", "ask", "sos"};
//        System.out.println(findWordsUsingDFS(board2, words2));
        System.out.println(findWordsUsingTrie(board, words));
    }

    /**
     * Native Solution
     * The naive approach is to search each word in the board by using Depth-First Search (DFS).
     * For each cell in the board, we will start a DFS to search for each word.
     *
     * Time Complexity:
     *
     * For each word, we perform a DFS starting from each cell in the board.
     * In the worst case, the time complexity is O(N * M * 4^L),
     * where N and M are the dimensions of the board and L is the length of the word.
     * This is because from each cell, we can go in 4 possible directions and we have to perform this for each
     * character in the word.
     */
    public static List<String> findWordsUsingDFS(char[][] board, String[] words) {
        List<String> result= new ArrayList<>();
        for(String word : words){
            if(wordExists(word, board)){
                result.add(word);
            }
        }
        return result;
    }

    public static boolean wordExists(String word, char[][] board) {
        int row=board.length;
        int col=board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j]==word.charAt(0) && dfs(word, board, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(String word, char[][] board, int i, int j, int count) {
        if(word.length()==count) return true;

        if(i<0 ||i>=board.length || j<0 || j >= board[i].length || word.charAt(count)!=board[i][j]){
            return false;
        }

        char temp= board[i][j];
        board[i][j]=' ';
        boolean found= dfs(word, board, i-1, j, count+1) ||
                dfs(word, board, i+1, j, count+1) ||
                dfs(word, board, i, j-1, count+1) ||
                dfs(word, board, i, j+1, count+1) ;
        board[i][j]=temp;
        return found;
    }

    /**
     * Optimized Solution
     * The optimized approach involves using a Trie (prefix tree) to reduce the search space.
     * We first insert all words into the Trie. Then, for each cell in the board, we start a DFS search,
     * but we use the Trie to quickly check if the current path is a prefix of any word in the list.
     *
     * Here's the Java code for the optimized solution:
     *
     * Time Complexity:
     * Building the Trie takes O(N * L), where N is the number of words and L is the average length of the words.
     * The DFS search is performed for each cell in the board, so in the worst case,
     * the time complexity is O(N * M * 4^L), where N and M are the dimensions of the board and
     * L is the length of the word. However, in practice, the Trie helps prune the search space,
     * making the solution more efficient.
     */
    public static List<String> findWordsUsingTrie(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root= buildTrie(words);
        for (int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i,j,root, result);
            }
        }
        return result;
    }

    private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c=board[i][j];
        if (c == '#' || node.child[c - 'a'] == null) return;
        node=node.child[c-'a'];
        if(node.word!=null){
            result.add(node.word);
            node.word=null;// Avoid duplicate entries
        }
        board[i][j] = '#';
        if(i>0) dfs(board,i-1,j,node,result);
        if(j>0) dfs(board,i,j-1,node,result);
        if(i<board.length-1) dfs(board,i+1,j,node,result);
        if(j<board[i].length-1) dfs(board,i,j+1,node,result);
        board[i][j]=c;
    }

    public static TrieNode buildTrie(String[] words) {
        int n= words.length;
        TrieNode root= new TrieNode();
        for(String word : words){
            TrieNode node=root;
            for (char c :word.toCharArray()) {
                int index=c-'a';
                if(node.child[index]==null) {
                    node.child[index] = new TrieNode();
                }
                node=node.child[index];
            }
            node.isEnd=true;
            node.word=word;
        }
        return root;
    }

}
class TrieNode {
    TrieNode[] child = new TrieNode[26];
    String word;
    boolean isEnd;
}

