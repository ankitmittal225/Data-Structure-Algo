package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class T16SprialFormTraversalMethod1{
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
		Queue<Node> qu=new LinkedList<>();
		Stack<Integer> s=new Stack<>();
		boolean rev=false;
		qu.add(root);
		while(!qu.isEmpty()) {
			int size=qu.size();
			for(int i=0;i<size;i++) {
				Node temp=qu.poll();
				if(rev) {
					s.push(temp.key);
				}
				else
					System.out.print(temp.key+" ");
				if(temp.left!=null) {
					qu.add(temp.left);
				}
				if(temp.right!=null) {
					qu.add(temp.right);
				}
			}
			if(rev) {
				while(!s.isEmpty()) {
					System.out.print(s.pop()+" ");
				}
			}
			rev=!rev;
			System.out.println();
		}
	}

}

/**

 * generate  sprial form traversal using level traversal by adding additional condition
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
