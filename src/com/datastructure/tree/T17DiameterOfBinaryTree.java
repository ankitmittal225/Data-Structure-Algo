package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class T17DiameterOfBinaryTree{
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
//		root.right=new Node(30);
		root.left.left=new Node(80);
//		root.left.right=new Node(50);
//		root.right.left=new Node(40);
//		root.right.right=new Node(50);
//		root.right.right.right=new Node(50);
		System.out.println(diameter(root));
	}
	
	public static int diameter(Node root) {
		if(root==null)return 0;
		int d1=1+height(root.left)+height(root.right);
		int d2=diameter(root.left);
		int d3=diameter(root.right);
		return Math.max(d1,Math.max(d2, d3));
	}
	
	public static int height(Node root) {
		if(root==null) return 0;
		else return 1+Math.max(height(root.left), height(root.right));
	}

}

/**

 * Diameter of binary Tree: Longest path between 2 leaves
 * max(1+lh+rh)
 * lh: left height
 * rh: right height
 * 1 to include root
 * 
 * 
Time Complexity : T(n)=T(n-1)+T(n) : T(n-1) for diameter & T(n) for height So worst case T(n*n)
Space Complexity : O(h+1)=O(h)
 * 

 * 
					10				5		null				3								10
				/		\			/						/		\						/				\
				20		30			4						1		2						8				2
			/		\				/					/		\	/	\				/		\		/
			40		50				6					1		2	7	7				6		2		6
																			\				/		\
																			9				20		10
																
O/P:  	4									0				7									6



 */
