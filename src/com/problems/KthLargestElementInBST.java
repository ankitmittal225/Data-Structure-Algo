package com.problems;

import java.util.Stack;

/**
 * Given a Binary Search Tree (BST) and a positive integer k,
 * find the kth largest element in the Binary Search Tree.
 * For example, in the following BST, if k=3, then output should be 15,
 * and if k=5, then output should be .4
 *
 *           10
 *         /    \
 *        4     20
 *       /     /   \
 *      2     15    40
 */
public class KthLargestElementInBST {
    static int ans=Integer.MAX_VALUE;
    static int count=1;

    public static void main(String[] args) {
        Node root=new Node(15);
        root.left=new Node(5);
        root.left.left=new Node(3);
        root.right=new Node(20);
        root.right.left=new Node(18);
        root.right.left.left=new Node(16);
        root.right.right=new Node(80);
        int k=3;

        System.out.print("Kth Smallest: ");
        Node root1 = new Node(5);
        root1.left = new Node(3);
        root1.right = null;
        root1.left.left = new Node(2);
        root1.left.right = new Node(4);
        root1.left.left.left = new Node(1);

        System.out.println(getKthLargestElementNativeRecursive(root,3));
        count=1;
        ans=0;
        System.out.println(getKthLargestElementNativeRecursive(root,5));
        count=1;
        ans=0;
        System.out.println(getKthLargestElementNativeRecursive(root1,3));

//        System.out.println(getKthLargestElementNativeIterative(root,3));
//        System.out.println(getKthLargestElementNativeIterative(root,5));
//        System.out.println(getKthLargestElementNativeIterative(root1,3));

//        System.out.println(kthLargestUsingMorrisTraversal(root,3));
//        System.out.println(kthLargestUsingMorrisTraversal(root,5));
//        System.out.println(kthLargestUsingMorrisTraversal(root1,3));
    }

    /**
     * to get the smallest we do Inorder traversal and return element at equals k
     * performing a reverse in-order traversal (right -> root -> left) and keeping track of the count
     * of nodes visited. This way, we can stop the traversal as soon as we reach the k'th largest element.
     * Time Complexity: O(n) in the worst case
     * Space Complexity: O(h)
     * where h is the height of the tree
     *
     * Time and Space Complexity
     * Time Complexity: O(h + k) where h is the height of the BST. This is because,
     * in the worst case, we might need to traverse k nodes and some part of the tree's height.
     * Space Complexity: O(h) due to the recursion stack used during the reverse in-order traversal.
     */
    public static int getKthLargestElementNativeRecursive(Node root, int k) {
        if(root==null) return -1;
        getKthLargestElementNativeRecursive(root.right,k);
        if(count==k){
            ans=root.key;
            return ans;
        }
        count++;
        getKthLargestElementNativeRecursive(root.left,k);
        return ans;
    }

    /**
     * Time Complexity: O(h + k)
     * Space Complexity: O(h)
     */
    public static int getKthLargestElementNativeIterative(Node root, int k) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // Go to the rightmost node
            while (current != null) {
                stack.push(current);
                current = current.right;
            }

            current = stack.pop();
            k--;

            // If we have reached the kth node
            if (k == 0) {
                return current.key;
            }

            // Move to the left subtree
            current = current.left;
        }

        return -1; // This line should never be reached if k is valid
    }

    /*
    To Find the Kth Largest Element with O(1) Space Complexity and O(n) we need to perform a reverse in-order
    traversal of the tree. Morris Traversal (Threaded binary tree) is an in-order traversal that doesn't use any additional space
    for recursion or a stack.



    Steps
        - Initialize the current node as the root.
        - While the current node is not null:
            - If the current node has no right child:
                - Visit this node.
                - Move to the left child.
            - If the current node has a right child, find the in-order predecessor of the current node.
                - If the left subtree of the predecessor is null, make the current node as its right child and move to the right child.
                - If the left subtree of the predecessor is already pointing to the current node, it means the right subtree has already been visited, so revert the changes made and visit the current node, then move to the left child.

    in-order predecessor : the node that comes before our key node when we write the inorder traversal of the tree.
    Time Complexity: O(n), where n is the number of nodes in the tree. Every node is visited at most twice.
    Space Complexity: O(1), as no extra space is used apart from variables for traversal and counting.
     */
    public static int kthLargestUsingMorrisTraversal(Node root, int k) {
        Node current = root;
        int count = 0;
        int result = -1;

        while (current != null) {
            if (current.right == null) {
                // Visit the current node
                count++;
                if (count == k) {
                    result = current.key;
                }
                current = current.left;
            } else {
                // Find the inorder predecessor of current
                Node prev = current.right;
                while (prev.left != null && prev.left != current) {
                    prev = prev.left;
                }

                if (prev.left == null) {
                    // Make current as the left child of its inorder predecessor
                    prev.left = current;
                    current = current.right;
                } else {
                    // Revert the changes made in the 'if' part to restore the original tree
                    prev.left = null;
                    count++;
                    if (count == k) {
                        result = current.key;
                    }
                    current = current.left;
                }
            }
        }
        return result;
    }
    
    

}

class Node
{
    int key;
    Node left;
    Node right;
    Node(int k){
        key=k;
        left=right=null;
    }
}
