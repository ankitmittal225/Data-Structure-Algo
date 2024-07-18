package com.problems.prep;

import java.util.*;

/*
We own a company that acquires customers. Each customer brings a certain revenue to the company and each customer
 can also refer more customers which in return add additional revenue.
 We want to implement an algorithm that calculates the revenue of the company.
 There are 3 methods to implement:

- int insertNewCustomerdouble revenue) - returns a customer ID (assume auto-incremented & 0-based)
- int inseitNewCustomer(double revenue, int referrerID) -> returns a customer ID (assume auto-incremented & 0-based)
-  Set<Integer> getLowestKCustomersByMinTotalRevenue(int k, int min TotalRevenue) -> returns customer IDs :
        The total revenue consists of the revenue that this customer brings initially as well as the revenue
        of the directly referred customers

Example
* insertNewCustomer(10) -> 0
* insertNewCustomer(30, 0) → 1
* insertNewCustomer(50, 1) -> 2
* getLowestKCustomersByMinTotalRevenue(1, 45) - (2)
* getLowestKCustomersByMinTotalRevenue(2, 45) - (1, 2)
Explanation:
* Revenue of customer with ID 0 => 10 + 30 = 40
* Revenue of customer with ID 1 => 30 + 50 = 80
* Revenue of customer with ID 2 => 50


Potential Follow-Up Questions (optional)
* Q: BigO complexities for insert/delete/get for HashMap & TreeMap
* Q: Dive deeper in the HashMap / TreeMap implementations
* Q: What if you want to calculate the revenue up to X levels ?
* Then this would be a graph problem
* Write BFS traversal and stop at X levels
* Q: How would you define the graph
Answer: Map<String, Set<String>> graph -> Customer & customer referrals
* The revenue can still be stored in the customerRevenueMap
 */
//public class RevenueCalculator {
//}
public class RevenueCalculator {

    static class Customer {
        int id;
        double revenue;

        public Customer(int id, double revenue) {
            this.id = id;
            this.revenue = revenue;
        }
    }

    private int currentCustomerId;
    private final HashMap<Integer, Customer> customerMap;
    private final TreeMap<Double, Set<Integer>> customerRevenuesSorted;

    //for BFS x level
    private final Map<Integer, List<Integer>> referralsMap = new HashMap<>();

    public RevenueCalculator() {
        this.currentCustomerId = -1;
        this.customerMap = new HashMap<>();
        this.customerRevenuesSorted = new TreeMap<>();
    }

    public int insertNewCustomer(double revenue) {
        currentCustomerId++;
        Customer customer = new Customer(currentCustomerId, revenue);
        customerMap.put(currentCustomerId, customer);
        addNewRevenueInCustomerRevenuesSorted(customer);

        ////for BFS x level
        referralsMap.put(currentCustomerId, new ArrayList<>());
        return currentCustomerId;
    }

    public int insertNewCustomer(double revenue, int referrerID) {
        if (!customerMap.containsKey(referrerID)) {
            throw new IllegalStateException("Referrer ID " + referrerID + " not found");
        }
        int newCustomerId = insertNewCustomer(revenue);
        Customer referrer = customerMap.get(referrerID);
        increaseRevenueForCustomer(referrer, revenue);
        ////for BFS x level
        referralsMap.get(referrerID).add(newCustomerId);
        return newCustomerId;
    }

    public Set<Integer> getLowestKCustomersByMinTotalRevenue(int k, double minTotalRevenue) {
        Set<Integer> customerIdsWithLowestRevenue = new HashSet<>();
        if (k <= 0) {
            return customerIdsWithLowestRevenue;
        }

        for (Map.Entry<Double, Set<Integer>> entry : customerRevenuesSorted.entrySet()) {
            if (entry.getKey() < minTotalRevenue) {
                continue;
            }
            for (int customerId : entry.getValue()) {
                customerIdsWithLowestRevenue.add(customerId);
                if (customerIdsWithLowestRevenue.size() == k) {
                    return customerIdsWithLowestRevenue;
                }
            }
        }
        return customerIdsWithLowestRevenue;
    }

    private void increaseRevenueForCustomer(Customer customer, double revenue) {
        customer.revenue += revenue;
        removeRevenueFromCustomerRevenuesSorted(customer.id, customer.revenue - revenue);
        addNewRevenueInCustomerRevenuesSorted(customer);
    }

    private void removeRevenueFromCustomerRevenuesSorted(int customerId, double revenue) {
        if (customerRevenuesSorted.containsKey(revenue)) {
            customerRevenuesSorted.get(revenue).remove(customerId);
            if (customerRevenuesSorted.get(revenue).isEmpty()) {
                customerRevenuesSorted.remove(revenue);
            }
        }
    }

    private void addNewRevenueInCustomerRevenuesSorted(Customer customer) {
        customerRevenuesSorted.putIfAbsent(customer.revenue, new HashSet<>());
        customerRevenuesSorted.get(customer.revenue).add(customer.id);
    }

    public void print() {
        System.out.println("Customer Map: " + customerMap);
        System.out.println("Customer Revenues Sorted: " + customerRevenuesSorted);
    }

    public static void main(String[] args) {
        RevenueCalculator calculator = new RevenueCalculator();

        int customerID1 = calculator.insertNewCustomer(10);
        calculator.print();

        int customerID2 = calculator.insertNewCustomer(30, customerID1);
        calculator.print();

        int customerID3 = calculator.insertNewCustomer(50, customerID2);
        calculator.print();

        System.out.println(calculator.getLowestKCustomersByMinTotalRevenue(1, 45));
        System.out.println(calculator.getLowestKCustomersByMinTotalRevenue(2, 45));
        System.out.println(calculator.getLowestKCustomersByMinTotalRevenue(1, 30));
        System.out.println(calculator.getLowestKCustomersByMinTotalRevenue(4, 30));
        System.out.println(calculator.getLowestKCustomersByMinTotalRevenue(2, 100));

        System.out.println("Revenue up to X levels:");
        System.out.println(calculator.calculateRevenueUpToXLevels(customerID1, 2)); // 90.0 (10 + 30 + 50)
    }

    /**
     * Time and Space Complexity:
     * insertNewCustomer: O(log n) for updating the TreeMap where n is the number of customers.
     *
     * getLowestKCustomersByMinTotalRevenue: O(n) for traversing the TreeMap entries where n is the number of customers.
     *
     * calculateRevenueUpToXLevels: O(n) for BFS traversal where n is the number of customers.
     *
     * Space Complexity: O(n) for storing customer data and relationships.
     */
    public double calculateRevenueUpToXLevels(int customerId, int x) {
        if (!customerMap.containsKey(customerId)) {
            throw new IllegalStateException(String.format("Customer ID %d not found", customerId));
        }
        return bfsRevenueCalculation(customerId, x);
    }

    private double bfsRevenueCalculation(int customerId, int x) {
        double totalRevenue = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{customerId, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentCustomerId = current[0];
            int level = current[1];

            if (level > x) {
                continue;
            }

            totalRevenue += customerMap.get(currentCustomerId).revenue;

            if (level < x) {
                for (int referral : referralsMap.get(currentCustomerId)) {
                    queue.offer(new int[]{referral, level + 1});
                }
            }
        }

        return totalRevenue;
    }
}

/*

Explanation:
Data Structures Used:

customerMap: HashMap to store customers where keys are customer IDs and values are Customer objects containing id and revenue.
customerRevenuesSorted: TreeMap to maintain a sorted order of customer revenues. Key is revenue and value is a Set of customer IDs having that revenue.

Methods:
- insertNewCustomer(double revenue): Inserts a new customer with the given revenue and returns the assigned customer ID.
- insertNewCustomer(double revenue, int referrerID): Inserts a new customer with a referrer, increases the referrer's revenue, and returns the assigned customer ID.
- getLowestKCustomersByMinTotalRevenue(int k, double minTotalRevenue): Returns a set of customer IDs with the lowest total revenue that meets the criteria (k customers and total revenue >= minTotalRevenue).
Print Method:

print(): Utility method to print the current state of customerMap and customerRevenuesSorted.

Time Complexity:
Insert Operations: O(log n) for TreeMap operations (insert, update, remove).
Query Operation (getLowestKCustomersByMinTotalRevenue): O(n + k log n), where n is the number of customers and k is the number of customers returned. This complexity arises due to iterating through customerRevenuesSorted and collecting results.

Space Complexity:
Space Complexity: O(n) for storing customerMap and customerRevenuesSorted, where n is the number of customers.
 */
