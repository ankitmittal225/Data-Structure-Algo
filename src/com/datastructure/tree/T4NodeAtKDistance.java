package com.datastructure.tree;

public class T4NodeAtKDistance {
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
//		root.left.left=new Node(60);
//		root.right.left=new Node(40);
//		root.right.right=new Node(50);
		printKNodes(root,2);
	}
	
	public static void printKNodes(Node root,int k) {
		if(root==null) return;
		if(k>0) {
			printKNodes(root.left,k-1);
			printKNodes(root.right,k-1);
		}
		if(k==0) {
			System.out.print(root.key+" ");
		}
		
	}

}

/**
 * We need to print K+1 level of node
 		10									10						10
 	/		\							/		\					/
 	20		30							6		8				  20
 /		\		\									\			/
 40		50		70									7			30
 												/		\
												11		12
 
I/P :	2										3					1
O/P :40 50 70								11  12					20


 Time Complexity : O(n)=worst case O(1)=best case(if k is 0)  
 Space Complexity : O(h) = it will take h(height recursion for a node)
 */