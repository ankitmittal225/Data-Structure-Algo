package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class T17DiameterOfBinaryTreeMethod2{
	static int res=0;
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
		root.left.left=new Node(80);
		root.left.right=new Node(50);
		root.right.left=new Node(40);
		root.right.right=new Node(50);
//		root.right.right.right=new Node(50);
		System.out.println(height(root));
		System.out.println(res);
	}
	
	
	//this method return height & sets the 'res' to have diameter
	public static int height(Node root) {
		if(root==null) return 0;
		int lh=height(root.left);
		int rh=height(root.right);
		res= Math.max(res,1+lh+rh);
		return 1+Math.max(lh,rh);
	}

}

/**

 * Diameter of binary Tree: Longest path between 2 leaves O(1) approach
 * Method 2 : precompute height of everynode in advance and store it in map
 * but this will require extra space O(n)
 *  Method 3  : O(n) with no extra space
 *  while computing the height we can get the diameter by 1+ height of left node+height of right node
 *  why we have to compute it separelty
 *  this method return height & sets the 'res' to have diameter
 * 
 * 
Time Complexity : O(n)
Space Complexity : O(h)
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
