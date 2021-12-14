package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T5LevelOrderTraversal {
	
	public static void main(String[] args) {
		Node root=null;
//		Node root=new Node(10);
//		root.left=new Node(20);
//		root.right=new Node(30);
//		root.left.left=new Node(80);
//		
//		root.right.left=new Node(40);
//		root.right.right=new Node(50);
		levelOrderTraversal(root);
	}
	
	public static void levelOrderTraversal(Node root) {
		if(root==null)return;
		Queue<Node> qu=new LinkedList<>();
		qu.add(root);
		while(!qu.isEmpty()) {
			Node temp=qu.poll();
			System.out.print(temp.key+" ");
			if(temp.left!=null) {
				qu.add(temp.left);
			}
			if(temp.right!=null) {
				qu.add(temp.right);
			}
		}
	}

}

/**
Level Order Traversal of a Binary Tree (Breadth first traversal)
 We have seen the three basic traversals(Preorder, postorder, and Inorder) of a Binary Tree. We can also traverse a 
 Binary Tree using the Level Order Traversal.

In the Level Order Traversal, the binary tree is traversed level-wise starting from the first to last level sequentially.

Consider the below binary tree:


The Level Order Traversal of the above Binary Tree will be: 10 20 30 40 50 60 70 80.

Algorithm: The Level Order Traversal can be implemented efficiently using a Queue.
	Create an empty queue q.
	Push the root node of tree to q. That is, q.push(root).
	Loop while the queue is not empty:
		Pop the top node from queue and print the node.
		Enqueue node's children (first left then right children) to q
		Repeat the process until queue is not empty.
		
Time Complexity : O(n) or to be specific theta(n) : enque /deque for every node
Space Complexity : O(n) or to be specific theta(w) width of binary tree , for every node all of its child node get store 

			0											0
		/										/				\
		0										0				0
	/										/		\		/		\
	0										0		0		0		0

Auxilary space:
	theta(1)										theta(n)


 */
