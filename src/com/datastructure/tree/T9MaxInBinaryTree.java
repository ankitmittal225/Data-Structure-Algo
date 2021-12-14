package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T9MaxInBinaryTree {
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
//		root.left=new Node(20);
//		root.right=new Node(30);
//		root.left.left=new Node(80);
//		root.right.left=new Node(40);
//		root.right.right=new Node(100);
		System.out.println(max(root));;
	}
	
	public static int max(Node root) {
		if(root==null) return -1;
		return Math.max(root.key,Math.max(max(root.left),max(root.right)));
	}

}

/**
Size of binary tree :  number of nodes in binary tree

Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
