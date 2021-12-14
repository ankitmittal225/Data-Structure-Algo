package com.datastructure.java11;


public class Tree{
	private Node head;
	
	private static class Node{
		 int val;
		 Node left;
		 Node right;
		Node(int value){
			this.val=value;
		}
	}
	
	public static Node createNode(int val) {
		Node node=new Node(val);
		node.left=null;
		node.right=null;
		return node;
	}
	
	public static void inorder(Node root) {
		if(root==null) return;
		inorder(root.left);
		System.out.print(root.val+" ");
		inorder(root.right);
	}
	
	public static void main(String... args) {
		Node tree=new Node(10);
		tree.left=createNode(7);
		tree.right=createNode(15);
		tree.left.left=createNode(5);
		tree.left.right=createNode(9);
		inorder(tree);
		System.out.println();
		Node mirror=getMirror(tree);
		inorder(mirror);
	}
	
	public static Node getMirror(Node root) {
		if(root==null) return null;
		Node mirror=createNode(root.val);
		mirror.right=getMirror(root.left);
		mirror.left=getMirror(root.right);
		return mirror;
	}
}
