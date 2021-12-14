package com.datastructure.tree;

public class T2TreeTraversal {
	
	public static void inorder(Node root) {
		if(root!=null) {
			inorder(root.left);
			System.out.println(root.key);
			inorder(root.right);
		}
	}
	
	public static void preorder(Node root) {
		if(root!=null) {
			System.out.println(root.key);
			preorder(root.left);
			preorder(root.right);
		}
	}
	
	public static void postorder(Node root) {
		if(root!=null) {
			postorder(root.left);
			postorder(root.right);
			System.out.println(root.key);
		}
	}
	
	public static void main(String[] args) {
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
		root.right.left=new Node(40);
		root.right.right=new Node(50);
//		inorder(root);
//		preorder(root);
		postorder(root);
	}

}

/**
 * 
					10
			/				\
			20				30
		/		\	
		40		50
 2 types of tree traversal there:
 -	Breadth first traversal (or level order) - 10-20-30-40-50
 -	Depth first traversal
 		-Inorder
 		-Preorder
 		-Postorder
 		
 	In Depth First traversal we have recursive approach:
 		-Travese root
 		-Traverse left subtree		-Recursive
 		-Traverse right subtree		-Recurcive
 		
 	For these 3 steps we have 3 popular permutations (inorder,preorder,postorder)
 	
 	 -Inorder : 	(Left Root Right)		:40-20-50-10-30
 	 -Preorder : 	(Root Left Right)		:10-20-40-50-30	
 	 -Postorder : 	(Left Right Root)		:40-50-20-30-10
 	 
 	 	Left : left subtree
 	 	Right : right subtree
 	 	
 	 
 	
 	
	Time complexity:O(n)
	space Complexity :O(h)
	 number of item in the recusion call stack is number of notes from root to the leaf which is height of binary tree
	 Consider longest height which is 3 & 1 for null so O(h) 
 		
 */
