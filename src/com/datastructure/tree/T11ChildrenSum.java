package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T11ChildrenSum{
	static int  maxLevel=0;
	
	public static void main(String[] args) {
		Node root=null;
//		Node root=new Node(20);
//		root.left=new Node(8);
//		root.right=new Node(12);
//		root.left.left=new Node(3);
//		root.left.right=new Node(5);
//		root.right.left=new Node(40);
//		root.right.right=new Node(100);
//		root.right.left.right=new Node(50);
//		leftView(root,1);// level value is current level
		System.out.println(check(root));
	}
	
	//Recursive solution
	public static boolean check(Node root) {
		if(root==null) return true;
		if(root.left==null && root.right==null) return true;
		int sum=0;
		if(root.left!=null) {sum=sum+root.left.key;}
		if(root.right!=null) {sum=sum+root.right.key;}
		return (root.key==sum && check(root.left) && check(root.right));
	}

}

/**
 * Left most node at atecery level
 * 
					20				5		null				3								10
				/		\									/		\						/		\
				8		12									1		2						8		2
			/		\											/		\							\
			3		5											1		2							2
																						
																					
																
O/P:		Yes						Yes		Yes					No						yes



Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
