package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T14BTToDoublyLinkedList{
	static Node prev=null;
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(20);
		root.left=new Node(8);
		root.right=new Node(12);
		root.left.left=new Node(3);
		root.left.right=new Node(5);
//		root.right.left=new Node(40);
//		root.right.right=new Node(100);
//		root.right.right.right=new Node(50);
//		leftView(root,1);// level value is current level
		System.out.println(doublyLinkedList(root));
	}
	
	//Recursive solution
	public static Node doublyLinkedList(Node root) {
		if(root==null) return root;
		Node head=doublyLinkedList(root.left);
		if(prev==null)head=root;
		else {
			root.left=prev;
			prev.right=root;
		}
		prev=root;
		doublyLinkedList(root.right);
		return head;
	}

}

/**
 * Convert binar tree into doubly linked list which will be in the form of inorder traversal
 * 
					20				5		null				3								10
				/		\			/						/		\						/				\
				8		12			4						1		2						8				2
			/		\				/					/		\	/	\				/		\		/
			3		5				6					1		2	7	7				6		2		6
																			\				/		\
																			9				20		10
																
O/P:		3-8-5-20-12				6=4=5		1		0					3						3



Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
