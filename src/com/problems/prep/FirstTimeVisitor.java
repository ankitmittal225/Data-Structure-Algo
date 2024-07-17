package com.problems.prep;

import java.util.*;

/*

Millions of customers visit our website every day, for each customer we have a unique identifier that is the
same every time they visit. We have 2kinds of customers.
Recurrent Visitor that visit more than once and OneTime Visitors, who so far have visited the website only once.
We want to implement a service that has 2 functionalities:
• storeCustomerVisit
• getFirstOneTimeVisitor

postCustomerVisit(2)
postCustomerVisit(5)
postCustomerVisit(2)
postCustomerVisit(3)
getFirstOneTimeVisitor() - 5
postCustomerVisit(2)
postCustomerVisit(4)
postCustomerVisit(5)
getFirstOneTimeVisitor () - 3


Guidance
* For L3, provide the question along with the example.
* For L4, to increase ambiguity do not provide the example. Wait for the candidate to ask questions and then if the candidate still doesn't understand the problem provide an example
* For L5+, to increase ambiguity do not provide the example. Wait for the candidate to ask questions and then give him an example verbally. Expect from the candidate to come up with test scenarios

Potential Follow-Up Questions (optional)
* Time complexity for the postCustomerVisit / getFirstOneTimeVisitor
* In case the candidate solves the problem directly with a LinkedHashMap ask how the LinkedHashMap is implemented and request to solve the problem without it
* Heap / Priority queue / TreeMap complexities
* HashMap internal implementation
* How much data can you fit in memory in a hashmap?
* HashMap, when does it resize? -> (~75% full / ~ ⅔)
* Ask for concurrent data structures
* What if the incoming data does not fit into memory?
* Sync VS async data processing

Solution Guidance
* O(N): Hashing/List where N is all visitor or oneTimeVisitors O(LogN)
* Heap/PriorityQueue to do Log(N) where N is oneTimeVisitors
* 0(1) DoubleLinkedList & HashMap
 */
public class FirstTimeVisitor {

    private HashMap<Integer, Integer> visitCount;
    private LinkedHashSet<Integer> oneTimeVisitors;

    public FirstTimeVisitor() {
        visitCount = new HashMap<>();
        oneTimeVisitors = new LinkedHashSet<>();

        oneTimeVisitorHeap= new PriorityQueue<>((a, b) -> Integer.compare(visitCount.get(a), visitCount.get(b)));

        head = null;
        tail = null;
        customerMap = new HashMap<>();
    }

    public static void main(String[] args) {
        FirstTimeVisitor service = new FirstTimeVisitor();

        service.postCustomerVisit(2);
        service.postCustomerVisit(5);
        service.postCustomerVisit(2);
        service.postCustomerVisit(3);
        System.out.println(service.getFirstOneTimeVisitor()); // Output: 5
        System.out.println(service.getFirstOneTimeVisitorHeap()); // Output: 5
        service.postCustomerVisit(2);
        service.postCustomerVisit(4);
        service.postCustomerVisit(5);
        System.out.println(service.getFirstOneTimeVisitor()); // Output: 3
        System.out.println(service.getFirstOneTimeVisitorHeap()); // Output: 5

        service.storeCustomerVisit(2);
        service.storeCustomerVisit(5);
        service.storeCustomerVisit(2);
        service.storeCustomerVisit(3);
        System.out.println(service.getFirstOneTimeVisitorDLL()); // Output: 5
        service.storeCustomerVisit(2);
        service.storeCustomerVisit(4);
        service.storeCustomerVisit(5);
        System.out.println(service.getFirstOneTimeVisitorDLL()); // Output: 3


    }

    /**
     * we'll use a LinkedHashMap to keep track of the order of customer visits and
     * a HashSet to keep track of recurrent visitors. The LinkedHashMap will help maintain
     * the insertion order, allowing us to easily get the first one-time visitor.
     *
     * Time Complexity
     * storeCustomerVisit: O(1) for insertion and removal operations in HashMap and LinkedHashMap.
     * getFirstOneTimeVisitor: O(1) for retrieving the first entry from LinkedHashMap.
     * Space Complexity
     * Space Complexity: O(n) where n is the number of unique customers due to the storage requirements of HashMap and LinkedHashMap.
     */
    public void postCustomerVisit(int customerId) {
        visitCount.put(customerId, visitCount.getOrDefault(customerId, 0) + 1);

        if (visitCount.get(customerId) == 1) {
            oneTimeVisitors.add(customerId);

            // for 2nd implementation
            oneTimeVisitorHeap.add(customerId);
        } else {
            oneTimeVisitors.remove(customerId);

            //for 2nd heap implementation
            oneTimeVisitorHeap.remove(customerId);

        }
    }

    public Integer getFirstOneTimeVisitor() {
        if (oneTimeVisitors.isEmpty()) {
            return null; // or throw an exception if you prefer
        }
        return oneTimeVisitors.iterator().next();
    }



    /**
     * 2nd approach using heap/priority queue with O(logn ) time complexity
     * Time Complexity
     * postCustomerVisit(int customerId):
     *
     * Adding to recurrentVisitors (HashSet): Average case O(1) time complexity.
     * Adding to oneTimeVisitors (PriorityQueue): O(log n) time complexity due to heap operations.
     * getFirstOneTimeVisitor():
     *
     * Polling from oneTimeVisitors: O(log n) time complexity due to heap operations, where n is the number of elements in the heap.
     * Space Complexity
     * Space Complexity: O(n), where n is the total number of unique customers (recurrent + one-time visitors) stored across oneTimeVisitors and recurrentVisitors.
     * @return
     */
    private PriorityQueue<Integer> oneTimeVisitorHeap;
    public int getFirstOneTimeVisitorHeap() {
        while (!oneTimeVisitorHeap.isEmpty()) {
            int customerId = oneTimeVisitorHeap.poll();
            if (visitCount.get(customerId) == 1) {
                return customerId;
            }
        }
        return -1;  // If no one-time visitors found
    }


    /**
     * 3rd way of doing with O(1) time complexity using doubly linked list implementation and a separate class
     */
    private Node head;
    private Node tail;
    private Map<Integer, Node> customerMap;

    public void storeCustomerVisit(int customerId) {
        if (customerMap.containsKey(customerId)) {
            Node node = customerMap.get(customerId);
            if (node.isOneTimeVisitor) {
                node.isOneTimeVisitor = false; // Mark as recurrent
                removeNode(node); // Remove from current position in DLL
            }
        } else {
            Node newNode = new Node(customerId);
            customerMap.put(customerId, newNode);
            addToTail(newNode); // Add new node to the tail of DLL
        }
    }

    public int getFirstOneTimeVisitorDLL() {
        if (head != null) {
            return head.customerId;
        }
        return -1; // No one-time visitors found
    }

    private void addToTail(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    private void removeNode(Node node) {
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

}

class Node {
    int customerId;
    boolean isOneTimeVisitor;
    Node prev;
    Node next;

    public Node(int customerId) {
        this.customerId = customerId;
        this.isOneTimeVisitor = true;
        this.prev = null;
        this.next = null;
    }
}
