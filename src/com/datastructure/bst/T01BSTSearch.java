package com.datastructure.bst;

import com.datastructure.bst.Node;

public class T01BSTSearch {
		public static void main(String[] args) {
			Node root=new Node(10);
			root.left=new Node(5);
			root.right=new Node(15);
//			root.left.left=new Node(40);
//			root.left.right=new Node(50);
			root.right.left=new Node(12);
			root.right.right=new Node(18);
//			root.right.left.right=new Node(80);
			System.out.println(bstSearch(root,15));
			System.out.println(bstSearch(root,100));
	//		preorder(root);
	//		postorder(root);
		}
		
		public static boolean bstSearch(Node root, int key) {
			// Recursive approach
			if(root==null)return false;
			if(root.key==key)return true;
			else if(root.key<key) return bstSearch(root.right, key);
			else return  bstSearch(root.left, key);
			
			//Iterative approach
//			while(root!=null) {
//				if(root.key==key)return true;
//				else if(root.key<key) root=root.right;
//				else if(root.key>key)  root=root.left;
//			}
//			return false;
		}
}
/**
 * 
 * Time Complexity : O(h) :height of the binary tree upperbound
 * Auxilary Space : 
 * 	for recursive : O(h) : h functional stack will be require
 * 	for iterative : O(1) : doesn't require any space
 * 
 * if tree is balanced , height will be  logn then time complexity will be O(logn)
 * if tree is unblanced ,height will be  n then time complexity is O(n) 
 * 
 * 				5
 * 					\
 * 					6
 * 						\
 * 						7
 * 							\
 * 							8	
 * 					
 */