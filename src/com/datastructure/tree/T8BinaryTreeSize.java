package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T8BinaryTreeSize {
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
//		root.left=new Node(20);
//		root.right=new Node(30);
//		root.left.left=new Node(80);
//		root.right.left=new Node(40);
//		root.right.right=new Node(50);
		System.out.println(size(root));;
	}
	
	public static int size(Node root) {
		if(root==null) return 0;
		return size(root.left)+size(root.right)+1;
		
	}

}

/**
Size of binary tree :  number of nodes in binary tree

Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
