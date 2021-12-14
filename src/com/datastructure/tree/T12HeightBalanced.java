package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T12HeightBalanced{
	static int  maxLevel=0;
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(20);
		root.left=new Node(8);
		root.right=new Node(12);
		root.left.left=new Node(3);
		root.left.right=new Node(5);
//		root.right.left=new Node(40);
		root.right.right=new Node(100);
		root.right.right.right=new Node(50);
//		leftView(root,1);// level value is current level
		System.out.println(isBalanced(root));
	}
	
	//Recursive solution
	public static int isBalanced(Node root) {
		if(root==null) return 0;
		int lh=isBalanced(root.left);
		if(lh==-1)return -1;
		int rh=isBalanced(root.right);
		if(rh==-1)return -1;
		if(Math.abs(lh-rh)>1)return -1;
		return Math.max(lh,rh)+1; //to return height if balanced
	}

}

/**
 * Balanced Height : Difference between left subtree & right subtree height is not more than 1.
 * 
					20				5		null				3								10
				/		\			/						/		\						/				\
				8		12			4						1		2						8				2
			/		\				/					/		\		\				/		\		/
			3		5				6					1		2		7				6		2		6
																			\				/		\
																			9				20		10
																
O/P:		Yes						No		Yes					No						yes



Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
