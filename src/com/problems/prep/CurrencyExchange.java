package com.problems.prep;

import java.util.*;

/**
 * Question
 * Paramenters:
 * - array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP
 * - an array containing a 'from' currency and a 'to' currency
 * Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency.
 * Your return value should be a number.
 *
 * Example 1 -
 * (GBP,EUR, 10) - read as "1 GPB equals 10 EUR"
 * (EUR, USD, 1.1) - "1 EUR equals 1.1 USD"
 * (10, GBP, USD) →> ?- "10 GBP equals how many USD?"
 *
 * Answer : 110
 * Explanation: 10 GBP = 10*(10 EUR)=10*(10*(1.1 USD)) = 110 USD
 *
 * Example 2 -
 * (GBP,EUR, 10) - read as "1 GPB equals 10 EUR"
 *  (EUR, USD, 1.1) - "1 EUR equals 1.1 USD"
 * (USD, JPY, 108.3)
 * (10 GBP, JPY) =>? - "10 PBG equals how much JPY?"
 * Answer: 11913
 *
 * Explanation: 10 GBP = 10x (10 EUR) = 10x 10( x (1.1 USD)) = 10x 10( x(1.1 x (108.336 JPY))) = 11913 JPY
 *
 * ( GBP, CAD, 1.671)
 * ( INR, CAD, 0.0185)
 * ( CAD, EUR, 0.685)
 * (1, EUR, INR)->?
 * Answer: 78.91
 *
 * ( EUR , USD, 1.1 )
 * (USD, INR, 70.0)
 * (INR, JPY, 1.5)
 * (10 UB,R PJY) -> ?
 * Answer: 1155.0
 *
 * Explanation: Similar logic, but twice: 10 EUR= 10 x(1.1 USD)= 10*(1.1 * (70.0 INR))=10*(1.1 * (70.0 * (1.5 JPY))) =  1155.0 JPY
 *
 * (EUR, USD, 1.1 )
 * (INR, USD, 0.014)
 * (INR, JPY, 1.5)
 * (10 EUR, JPY) →?
 * Answer: 1178.57
 * Explanation: NIR - > USD is present, need to convert that to USD - > INR
 *
 *
 * (EUR, USD, 1.1)
 * (USD, INR, 70.0)
 *  (INR, JPY, 1.5)
 *  (EUR, GBP, 0.9)
 * (GBP, MP, 27.3)
 * (MP, JPY, 4.7)
 * 10( EUR, JPY) →?>
 * Answer : 1155.0
 * Explanation : multiple path possible, but they lead to same result.
 *
 *
 *
 * (EUR, USD, 1.1)
 * (CAD, INR, 53.9)
 * (1, BUR, INR) →?
 * Answer : null
 * Explanation : No conversion possible
 *
 * (EUR, GBP, 0.91)
 * (GBP, CHE, 1.18)
 * (USD, INR, 70.0)
 * (EUR, USD, 1.1)
 * (INR, JPY, 1.5)
 * (10 EUR, JPY) →> ?
 * Answer : 1155.0
 * Explanation : Multiple paths possible from EUR, one of them leads to solution. DFS
 */
public class CurrencyExchange {

    public static void main(String[] args) {
        Graph graph= new Graph();
        graph.addExchangeRate("EUR", "GBP", 0.91);
        graph.addExchangeRate("GBP", "CHE", 1.18);
        graph.addExchangeRate("USD", "INR", 70.0);
        graph.addExchangeRate("EUR", "USD", 1.1);
        graph.addExchangeRate("INR", "JPY", 1.5);
        System.out.println(graph.calculateCurrencyRateUsingBFS("EUR","JPY",10 ));
        System.out.println(graph.calculateCurrencyRateUsingDFS("EUR","JPY",10 ));

        graph.adjacencyList.clear();
        graph.addExchangeRate("EUR", "USD", 1.1);
        graph.addExchangeRate("CAD", "INR", 53.9);
        System.out.println(graph.calculateCurrencyRateUsingBFS("EUR","INR",1 ));
        System.out.println(graph.calculateCurrencyRateUsingDFS("EUR","INR",1 ));

        graph.adjacencyList.clear();
        graph.addExchangeRate("EUR", "USD", 1.1);
        graph.addExchangeRate("USD", "INR", 70.0);
        graph.addExchangeRate("INR", "JPY", 1.5);
        graph.addExchangeRate("EUR", "GBP", 0.9);
        graph.addExchangeRate("GBP", "MP", 27.3);
        graph.addExchangeRate("MP", "JPY", 4.7);
        System.out.println(graph.calculateCurrencyRateUsingBFS("EUR","JPY",10 ));
        System.out.println(graph.calculateCurrencyRateUsingDFS("EUR","JPY",10 ));
    }




    static class Graph {
//        Map<String, List<ExchangeRate>> adjacencyList = new HashMap<>();
        Map<String, Map<String, Double>> adjacencyList = new HashMap<>();

        /**
         * Building the Graph
         * Time Complexity: O(E), where E is the number of conversion rates.
         * Space Complexity: O(V + E), where V is the number of unique currencies (vertices), and E is the number of conversion rates (edges).
         */
        void addExchangeRate(String from, String to, double rate) {
            adjacencyList.computeIfAbsent(from, k -> new HashMap<>()).put(to,rate);
            adjacencyList.computeIfAbsent(to, k -> new HashMap<>()).put(from,1.0/rate);
        }

        /**
         * Time Complexity: O(V + E), where V is the number of vertices, and E is the number of edges.
         * In the worst case, we might visit all vertices and edges.
         * Space Complexity: O(V), for the queue, the visited set, and the amounts queue.
         *
         */
        public Double calculateCurrencyRateUsingBFS(String from, String to, double rate){
            // BFS to find the conversion rate
            Queue<String> queue = new LinkedList<>();
            Queue<Double> rates = new LinkedList<>();

            queue.offer(from);
            rates.offer(rate);

            Set<String> visited = new HashSet<>();
            visited.add(from);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                double currentAmount = rates.poll();

                if (current.equals(to)) {
                    return currentAmount;
                }

                for (Map.Entry<String, Double> entry : adjacencyList.getOrDefault(current, new HashMap<>()).entrySet()) {
                    if (!visited.contains(entry.getKey())) {
                        visited.add(entry.getKey());
                        queue.offer(entry.getKey());
                        rates.offer(currentAmount * entry.getValue());
                    }
                }
            }
            return null;
        }

        /**
         * Time Complexity: O(V + E), where V is the number of vertices, and E is the number of edges.
         * In the worst case, we might visit all vertices and edges.
         * Space Complexity: O(V), for the recursion stack and the visited set.
         */
        public Double calculateCurrencyRateUsingDFS(String from, String to, double rate){
            // DFS to find the conversion rate
            Set<String> visited = new HashSet<>();
            return dfs(from, to, rate, visited);
        }

        private Double dfs(String from, String to, double amount, Set<String> visited) {
            if (from.equals(to)) return amount;
            visited.add(from);
            for (Map.Entry<String, Double> entry : adjacencyList.getOrDefault(from, new HashMap<>()).entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    Double result = dfs(entry.getKey(), to, amount * entry.getValue(), visited);
                    if (result != null) {
                        return result;
                    }
                }
            }
            return null;
        }

    }

    static class ExchangeRate {
        String to;
        double rate;

        ExchangeRate(String to, double rate) {
            this.to = to;
            this.rate = rate;
        }
    }

}

/**
 *
 * How to drive to solution
 * To solve this problem, we need to find a way to navigate through a network of currency conversions.
 * We'll use a graph to represent the conversion rates and then use a search algorithm like
 * Depth-First Search (DFS) or Breadth-First Search (BFS) to find the conversion path.
 * Here's the step-by-step plan:
 *
 * Graph Representation: We'll represent the currency conversions as a directed graph
 * where each currency is a node, and each conversion rate is an edge with a weight
 * representing the conversion factor.
 *
 * DFS or BFS Search: To find the conversion rate from one currency to another,
 * we'll perform a DFS or BFS to navigate through the graph.
 *
 * Edge Cases: We'll handle cases where no conversion is possible by checking
 * if the target currency is reachable from the source currency.
 */

/**
 * Now, return the optimal exchange rate. Optimal ni this case means maximize the amount of the target currency received.
 * Follow up 1:
 * Answer :
 * we need consider creating a
 * fully connected graph (full matrix) with all exchange rates. But the pros of this approach is speed,
 * the cons is size. At this point, you can discuss how to design this graph in such a way that you
 * pass the level of connectivity where the maximum connectivity is n - 1, and the minimum is 1.
 *
 * Follow up 2:
 * Check if there's a path that can give you a profit from doing a chain of conversions
 * (assuming that the conversions are one-directional). For example,
 * if you have USD - EUR: 10, EUR -> CHF: 1.1 and CHF -> USD: 0.1 then you can make a
 * profit following the chain USD - EUR → CHF (1 × 10 x 1,1 x 0.1 = 1:1)
 *
 * Follow up 3:
 * What if we're getting many queries? Can we optimize queries? :
 * Answer : We need to choose the path which gives the biggest answer. This becomes a simple dp on graph question.
 *
 * Follow up 4:
 * What if we're getting currency exchange rates in real-time? How do we process a graph updating in real-time?
 * Answer : We can optimize queries by precomputing al conversions and then returning them ni O(1).
 * For this, we can use the USD solution above. We can start a BFS from USD and find all costs at USD.
 * For each query, we can convert using USD.
 */
