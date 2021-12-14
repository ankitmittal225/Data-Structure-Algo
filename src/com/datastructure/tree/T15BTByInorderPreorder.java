package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T15BTByInorderPreorder{
	static int preIndex=0;
	public static void main(String[] args) {
		int[] pre= {10,20,30,40,50};
		int[] in= {20,10,40,30,50};
		Node head= getBinaryTree(pre,in,0,in.length-1);
//		System.out.println(head.key);
		T2TreeTraversal.preorder(head);
	}
	
	//Recursive solution
	public static Node getBinaryTree(int[] pre,int[] in,int is,int ie) {
		if(is>ie) return null;
		Node root=new Node(pre[preIndex]);
		preIndex=preIndex+1;
		int inIndex=0;
		for(int i=is;i<=ie;i++) {
			if(in[i]==root.key) {
				inIndex=i;
				break;
			}
		}
		root.left=getBinaryTree(pre,in,is,inIndex-1);
		root.right=getBinaryTree(pre,in,inIndex+1,ie);
		return root;
	}

}

/**
 * generate  binar tree from inorder and preorder traversal traversal
 * Preorder : Root Left Right
 * In order  : Left Root Right
 * 
 * Eg : 
 * In : 20,10,40,30,50
 * Pre : 10,20,30,40,50
 * 
					10				5		null				3								10
				/		\			/						/		\						/				\
				20		30			4						1		2						8				2
					/		\				/					/		\	/	\				/		\		/
					40		50		6					1		2	7	7				6		2		6
																			\				/		\
																			9				20		10
																



Time Complexity : O(n*n) : can be done in O(n) by using hashing in hasmap store value as key and index as value.
Space Complexity : O(h+1)=O(h)

 */
