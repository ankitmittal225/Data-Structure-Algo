package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class T16SprialFormTraversalMethod2{
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
		root.left.left=new Node(80);
		
//		root.right.left=new Node(40);
		root.left.right=new Node(50);
		levelOrderTraversal(root);
	}
	
	public static void levelOrderTraversal(Node root) {
		if(root==null)return;
		Stack<Node> s1=new Stack<>();
		Stack<Node> s2=new Stack<>();
		s1.push(root);
		while(!s1.isEmpty() || !s2.isEmpty()) {
			while(!s1.isEmpty()) {
				Node temp=s1.pop();
				System.out.print(temp.key+" ");
				if(temp.left!=null)s2.push(temp.left);
				if(temp.right!=null)s2.push(temp.right);
			}
			while(!s2.isEmpty()) {
				Node temp=s2.pop();
				System.out.print(temp.key+" ");
				if(temp.right!=null)s2.push(temp.right);
				if(temp.left!=null)s2.push(temp.left);
			}
			System.out.println();
		}
	}

}

/**

 * generate  sprial form traversal using 2 stack
 * push root to stack s1;
 * while any of the 2 stack is not empty.
 * 		while s1 is not empty
 * 			a. take out a node print it.
 * 			b. push children of the taken out node in to s2.   Left,right
 * 		while s2 is not empty
 * 			a. take out a node print it.
 * 			b. push children of the taken out node into s1 in reverse order.   Right,left
 * 

 * 
					10				5		null				3								10
				/		\			/						/		\						/				\
				20		30			4						1		2						8				2
			/		\				/					/		\	/	\				/		\		/
			40		50				6					1		2	7	7				6		2		6
																			\				/		\
																			9				20		10
																
O/P:  10,30,20,40,50


Time Complexity : O(n) : can be done in O(n) by using hashing in hasmap store value as key and index as value.
Space Complexity : O(h+1)=O(h)

 */
