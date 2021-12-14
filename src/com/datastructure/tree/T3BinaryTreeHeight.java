package com.datastructure.tree;

public class T3BinaryTreeHeight {
	
	public static void main(String[] args) {
		Node root=null;
//		Node root=new Node(10);
//		root.left=new Node(20);
//		root.right=new Node(30);
//		root.right.left=new Node(40);
//		root.right.right=new Node(50);
		System.out.println("height = "+height(root));
	}
	
	public static int height(Node root) {
		if(root==null) return 0;
		return Math.max(height(root.left),height(root.right))+1;
	}

}

/**
 		10					30				10			null		10
 	/		\			/		\										\
 	8		30			40		60										20
 	 	/		\		/													\
 		40		50		70													30
 			/			/
 			10			80
 
Height :	4				4					1			0				3  (By Node)
			3				3					0			-1				2	(By edge)
 Height of a Tree: Height of a tree is defined as the total number of levels in the tree or the length of the path from
 the root node to the node present at the last level. The above tree is of height 2.
 					OR
 maximum number of nodes from root to a leaf node.
 					OR
 maximum number of edges from root to leaf
 
 Time Complexity : O(n) comparision addition are 2 constant operation for every node
 Space Complexity : O(h) = it will take h(height recursion for a node)
 */