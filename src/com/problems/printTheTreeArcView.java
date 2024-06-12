package com.problems;


import java.util.*;

/**
 * To solve this problem of printing nodes seen from bottom left to bottom right in an arc via the root node for an N-ary tree, we can follow these steps:
 *
 * 1. Perform a Breadth-First Search (BFS) to traverse the tree level by level.
 * 2. Track the levels using a queue.
 * 3. Maintain two lists (arrays): one for the leftmost nodes and one for the rightmost nodes.
 * 4. For each level:
 *      Add the leftmost node to the left list.
 *      Add the rightmost node to the right list.
 * 5. Ensure the root is added only once.
 * 6. Print the left list in reverse order followed by the right list.
 */

class TreeNode {
    int val;
    List<TreeNode> children;

    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class printTheTreeArcView {

    public static List<Integer> printArcView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> leftView = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();
        boolean rootAdded = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Integer leftmost = null, rightmost = null;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (i == 0) leftmost = currentNode.val;
                if (i == levelSize - 1) rightmost = currentNode.val;

                for (TreeNode child : currentNode.children) {
                    queue.offer(child);
                }
            }

            if (leftmost != null) {
                if (!rootAdded || leftmost != root.val) {
                    leftView.add(leftmost);
                }
                rootAdded = true;
            }
            if (rightmost != null && rightmost != root.val) {
                rightView.add(rightmost);
            }
        }

        // Prepare the result: left array in reverse + right array
        Collections.reverse(leftView);
        leftView.addAll(rightView);
        return leftView;
    }

    public static void main(String[] args) {
        // Example tree creation
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);

        node2.children.add(node5);
        node2.children.add(node6);

        node3.children.add(node7);

        node4.children.add(node8);
        node4.children.add(node9);

        // Print the nodes in the required order
        System.out.println(printArcView(root));

        TreeNode root10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);
        root10.children.add(node11);
        root10.children.add(node12);
        root10.children.add(node13);

        node11.children.add(node14);

        // Print the nodes in the required order
        System.out.println(printArcView(root10));
    }

}

//follow up question :
/**
 * Can the problem can be solved with depth first search?
 * Depth-First Search (DFS) Solution
 * The problem can be solved using Depth-First Search (DFS) as well, but it would be more complex to handle levels and ensure that the leftmost and rightmost nodes at each level are captured correctly. DFS is not as natural for this problem as BFS because BFS inherently processes nodes level by level, which aligns with our requirement to keep track of leftmost and rightmost nodes at each level.
 */
