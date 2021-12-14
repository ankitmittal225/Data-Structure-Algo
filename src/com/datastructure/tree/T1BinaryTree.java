package com.datastructure.tree;


public class T1BinaryTree {
	public static void main(String[] args) {
		//Node tree=null; 		//empty tree
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
		root.left.left=new Node(40);
	}

}

