package com.problems;

import java.util.Stack;

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

//        System.out.println(getKthLargestElementNativeRecursive(root,3));
//        count=1;
//        ans=0;
//        System.out.println(getKthLargestElementNativeRecursive(root,5));
//        count=1;
//        ans=0;
//        System.out.println(getKthLargestElementNativeRecursive(root1,3));

//        System.out.println(getKthLargestElementNativeIterative(root,3));
//        System.out.println(getKthLargestElementNativeIterative(root,5));
//        System.out.println(getKthLargestElementNativeIterative(root1,3));

        System.out.println(kthLargestUsingMorrisTraversal(root,3));
        System.out.println(kthLargestUsingMorrisTraversal(root,5));
        System.out.println(kthLargestUsingMorrisTraversal(root1,3));
    }

    /**
     * to get the smallest we do Inorder traversal and return element at equals k
     * Time Complexity: O(n) in the worst case
     * Space Complexity: O(h)
     * where h is the height of the tree
     */
    public static int getKthLargestElementNativeRecursive(Node root, int k) {
        if(root==null) return -1;
        getKthLargestElementNativeRecursive(root.right,k);
        if(count==k){
            ans=root.key;
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
                Node predecessor = current.right;
                while (predecessor.left != null && predecessor.left != current) {
                    predecessor = predecessor.left;
                }

                if (predecessor.left == null) {
                    // Make current as the left child of its inorder predecessor
                    predecessor.left = current;
                    current = current.right;
                } else {
                    // Revert the changes made in the 'if' part to restore the original tree
                    predecessor.left = null;
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
