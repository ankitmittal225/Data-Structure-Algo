package com.datastructure.tree;

import java.util.Stack;

public class T20IterativeInorderTraversal {
	
	public static void inorder(Node root) {
		if(root!=null) {
			Stack<Node> s=new Stack<>();
			Node curr=root;
			while(curr!=null || !s.isEmpty()) {
				while(curr!=null) {
					s.push(curr);
					curr=curr.left;
				}
				curr=s.pop();
				System.out.print(curr.key+" ");
				curr=curr.right;
			}
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
		root.left.left=new Node(40);
		root.left.right=new Node(50);
		root.right.left=new Node(60);
		root.right.right=new Node(70);
		root.right.left.right=new Node(80);
		inorder(root);
//		preorder(root);
//		postorder(root);
	}

}

/**
 * 
					10
			/				\
			20				30
		/		\		/		\
		40		50		60		70
						\
						80
	
 	 -Inorder : 	(Left Root Right)		:40-20-50-10-30
 	 -Preorder : 	(Root Left Right)		:10-20-40-50-30	
 	 -Postorder : 	(Left Right Root)		:40-50-20-30-10
 	 
 	 	Left : left subtree
 	 	Right : right subtree
 	 	
 	 
 	
 	
	Time complexity:O(n) : (pushing and poping every node once having complecity O(1) for n node)
	space Complexity :O(h) : (Height of the tree)
	 number of item in the recusion call stack is number of notes from root to the leaf which is height of binary tree
	 Consider longest height which is 3 & 1 for null so O(h) 
 		
 */
