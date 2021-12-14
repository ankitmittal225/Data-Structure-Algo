package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T7LevelTraversalLineByLine2 {
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
		root.left.left=new Node(80);
		
		root.right.left=new Node(40);
		root.right.right=new Node(50);
		levelOrderTraversal(root);
	}
	
	public static void levelOrderTraversal(Node root) {
		if(root==null)return;
		Queue<Node> qu=new LinkedList<>();
		qu.add(root);
		while(!qu.isEmpty()) {
			int size=qu.size();
			for(int i=0;i<size;i++) {
				Node temp=qu.poll();
				System.out.print(temp.key+" ");
				if(temp.left!=null) {
					qu.add(temp.left);
				}
				if(temp.right!=null) {
					qu.add(temp.right);
				}
			}
			System.out.println();
		}
	}

}

/**
Level Order Traversal of a Binary Tree (Breadth first traversal)

In the Level Order Traversal, the binary tree is traversed level-wise starting from the first to last level sequentially.

				10
			/		\
			20		30
		/
		40
		
		
		10-null 		
		null-20-30		poll 10
		20-30-null		poll null add next line
		30-null-40		poll 20 add child 40
		null-40-null	poll 30 add null no child
		40-null			poll 
		null

Consider the below binary tree:


The Level Order Traversal of the above Binary Tree will be: 10 20 30 40 50 60 70 80.

Algorithm: The Level Order Traversal can be implemented efficiently using a Queue.
	Create an empty queue q.
	Push the root node of tree to q. That is, q.push(root).
	Loop while the queue is not empty:
		Pop the top node from queue and print the node.
		Enqueue node's children (first left then right children) to q
		Repeat the process until queue is not empty.
		
Time Complexity : O(n) constant operation for enque and deque of each node
Space Complexity : theta(w) width of binary tree 


 */
