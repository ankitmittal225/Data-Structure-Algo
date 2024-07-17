package com.problems.prep;

import java.util.*;

/**
 * You are given a set of symbols for the elements in the periodic table
 * eg. (... Li, Be, B, C, N, E , Ne, Na, Co, Ni, CuG,a, Al, Si...)
 * Write the function BreakingBad (name, symbols) that given a name and a set of symbols returns the phrase with the following format. For example:
 * Symbols = ["H", " H e " , "Li", "Ве", "в", "C","N", "f", "Ne", "Na", "Co", "Ni","Cu","Ga", "Al", "s1", "Fa"]
 * BreakingBad ("henry alba", symbols)
 * results in "[He]nry [Al]ba"
 *
 * Potential Follow-Up Questions (optional) Additional Examples:
 * BreakingBad("connor riddle", symbols) results in [Co]nnor riddle
 * BreakingBad("nicole carry", symbols) results in [Ni]cole [C]arry
 * BreakingBad( "jerry seinfeld", symbols) results in jerry seinfeld
 * BreakingBad ("ben f gabe", symbols) results in [Be]n [F] [Ga]be
 *
 * What will happen fi we change the symbols to contain 3-4 letter words
 * Symbols - [Hat, Hey, Liam, Ben, Bot, Can, Net, Fat, He, Na)
 * BreakingBad ("Henry the Hatter is the Father of Ben", symbols) results in [He]nry the [Hat]ter is the [Fat]her of [Ben]
 * What if we want to find and replace al occurrences of symbols in name?
 * BreakingBad("bencu alcun") ->""[Be][N][Cu] [A][Cu][N]"
 *
 *
 * To solve the problem of transforming a given name using symbols from the periodic table in a "Breaking Bad" style, we need to:
 *
 * Identify all possible symbols in the given name.
 * Replace the first occurrence of each symbol in the name with the symbol enclosed in square brackets.
 */
public class BreakingBad {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("H", "He", "Li", "Be", "B", "C", "N", "Ne", "Na", "Co", "Ni", "Cu", "Ga", "Al", "Si", "F");
        System.out.println(breakingBadNativeSolution("henry alba", symbols)); // [He]nry [Al]ba
        System.out.println(breakingBadNativeSolution("connor riddle", symbols)); // [Co]nnor riddle
        System.out.println(breakingBadNativeSolution("nicole carry", symbols)); // [Ni]cole [C]arry
        System.out.println(breakingBadNativeSolution("jerry seinfeld", symbols)); // jerry seinfeld
        System.out.println(breakingBadNativeSolution("ben f gabe", symbols)); // [Bee]n [F] [Ga]be

        System.out.println(breakingBadTrie("henry alba", symbols)); // [He]nry [Al]ba
        System.out.println(breakingBadTrie("connor riddle", symbols)); // [Co]nnor riddle
        System.out.println(breakingBadTrie("nicole carry", symbols)); // [Ni]cole [C]arry
        System.out.println(breakingBadTrie("jerry seinfeld", symbols)); // jerry seinfeld
        System.out.println(breakingBadTrie("ben f gabe", symbols)); // [Be]n [F] [Ga]be

        List<String> symbols2 = Arrays.asList("Hat", "Hey", "Liam", "Ben", "Bot", "Can", "Net", "Fat", "He", "Na");
//        System.out.println(breakingBadNativeSolution("Henry the Hatter is the Father of Ben", symbols2)); // [He]nry the [Hat]ter is the [Fat]her of [Ben]
        System.out.println(breakingBadTrie("Henry the Hatter is the Father of Ben", symbols2)); // [He]nry the [Hat]ter is the [Fat]her of [Ben]

    }

    /**
     * Native Solution :
     * The naive approach involves iterating over each symbol and performing a replacement in the name.
     * This is a straightforward approach, but it might not be efficient if there are a large
     * number of symbols or if the symbols are long.
     *
     * Time Complexity:
     * O(N * M), where N is the length of the name and M is the number of symbols.
     * This is because for each character in the name, we may check up to M symbols.
     *
     * Space Complexity:
     * O(M), where M is the number of symbols, due to the storage in the HashSet.
     *
     * Create a hashset with al the elements, then iterate over words ni input string while at the same building the answer string:
     * For current word test which of the prefixes is found in the hashset
     * fI longest found prefix is not empty then add ti ot answer with square parens Add characters after the longest prefix to answer
     * When adding elements ot the hash set you must convert first character to lowercase, when testing fi prefix si ni hashset ti also must be lowercase, and of course when adding longest prefix to answer it's first letter must be converted back to uppercase. O(E) space due ot hashset and OE( +N) time where Esi number of elements and Nsi length of the input sentence
     */
    private static String breakingBadNativeSolution(String name, List<String> symbols) {

        // Sort symbols by length in descending order to match the longest symbols first
        symbols.sort((a, b) -> b.length() - a.length());

        // Build a set of symbols for quick lookup
        Set<String> symbolSet = new HashSet<String>(symbols);

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < name.length()) {
            boolean matched = false;
            for (String symbol : symbols) {
//                if (name.regionMatches(true, i, symbol, 0, symbol.length())) {
                if (i + symbol.length() <= name.length() && name.substring(i, i + symbol.length()).equalsIgnoreCase(symbol)){
                    result.append("[").append(symbol).append("]");
                    i += symbol.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                result.append(name.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Time Complexity:
     * Trie Construction: O(M * L), where M is the number of symbols and L is the average length of the symbols.
     * Symbol Replacement: O(N * L), where N is the length of the name and L is the average length of the symbols.
     * The Trie allows us to quickly find the longest matching symbol at each position in the name.
     *
     * Space Complexity:
     * Trie Storage: O(M * L), where M is the number of symbols and L is the average length of the symbols.
     * StringBuilder Storage: O(N), where N is the length of the name.
     * By using a Trie, the optimized solution reduces the time complexity of finding and replacing symbols in the name, making it more efficient for larger datasets.
     * */
    private static String breakingBadTrie(String name, List<String> symbols) {
        TrieNodeBB root = build(symbols);
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < name.length()) {
            TrieNodeBB node = root;
            int maxLength = 0;
            String matchedSymbol = null;
            for (int j = i; j < name.length(); j++) {
                char c = Character.toLowerCase(name.charAt(j));
                if (node.children.containsKey(c)) {
                    node = node.children.get(c);
                    if (node.symbol != null) {
                        maxLength = j - i + 1;
                        matchedSymbol = node.symbol;
                    }
                } else {
                    break;
                }
            }
            if (maxLength > 0) {
                result.append("[").append(matchedSymbol).append("]");
                i += maxLength;
            } else {
                result.append(name.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    public static TrieNodeBB build(List<String> symbols) {
        TrieNodeBB root = new TrieNodeBB();
        for (String symbol : symbols) {
            TrieNodeBB current = root;
            for (char c : symbol.toCharArray()) {
                c = Character.toLowerCase(c);
                current.children.putIfAbsent(c, new TrieNodeBB());
                current = current.children.get(c);
            }
            current.symbol = symbol;
        }
        return root;
    }
}

class TrieNodeBB {
    Map<Character, TrieNodeBB> children;
    String symbol;

    TrieNodeBB() {
        this.children = new HashMap<>();
        this.symbol = null;
    }
}