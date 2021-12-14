package com.datastructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.datastructure.bst.Node;

public class T03BSTDelete {
		public static void main(String... args) {
//			Node root=null;
			Node root=new Node(10);
			root.left=new Node(5);
			root.right=new Node(15);
//			root.left.left=new Node(2);
			root.left.right=new Node(7);
			root.right.left=new Node(12);
			root.right.right=new Node(18);
////			root.right.left.right=new Node(80);
			root=bstDelete(root,10);
//			root=bstDelete(root,100);
			levelOrderTraversal(root);
			levelOrderTraversal(root);
		}
		public static Node getSuccessor(Node root) {
			Node curr=root.right;
			while(curr!=null && curr.left!=null)
				curr=curr.left;
			return curr;
		}
		
		public static Node bstDelete(Node root, int key) {
			// Recursive approach
			if(root==null) return null;
			else if(root.key<key) root.right= bstDelete(root.right, key);
			else if(root.key>key) root.left= bstDelete(root.left, key);
			else {
				if(root.left==null) return root.right;
				else if(root.right==null) return root.left;
				else {
					Node succ=getSuccessor(root);
					root.key=succ.key;
					root.right=bstDelete(root.right, succ.key);
				}
				return root;
			}
			return root;
			//Iterative approach
//			Node temp=new Node(key);
//			Node curr=root;
//			Node parent=null;
//			while(curr!=null) {
//				parent=curr;
//				if(curr.key<key) curr=curr.right;
//				else if(curr.key>key)  curr=curr.left;
//				else return root;
//			}
//			if(parent==null) return temp;
//			if(parent.key>key)parent.left=temp;
//			else parent.right=temp;
//			return root;
		}
		
		public static void levelOrderTraversal(Node root) {
			if(root==null)return;
			Queue<Node> qu=new LinkedList<>();
			qu.add(root);
			while(!qu.isEmpty()) {
				int size=qu.size();
				for(int i=0;i<size;i++) {
					Node temp=qu.poll();
					System.out.print(temp.key+" ");
					if(temp.left!=null) {
						qu.add(temp.left);
					}
					if(temp.right!=null) {
						qu.add(temp.right);
					}
				}
				System.out.println();
			}
		}
}
/**
 * Insert : 
 * 
 * Input : x=12								x=30										x=50		
 * Tree :  if deleted node is leaf		if deleted node has one child			id deleted node has both child
 * 				10							50												50	
 * 			/		\					/		\										/			\	
 * 			5		15					30		70										20			70
 * 				/		\			/		/		\										\	/		\
 * 				12		18			20		60		80										40	60		80
 * 
 * 
 * Output:				
 				10							50												60	
 * 			/		\					/		\										/			\	
 * 			5		15					20		70										20			70
 * 						\					/		\										\			\
 * 						18					60		80										40			80
 * 	in case if deleted node has 2 child then we need to find close smaller or bigger value related to deleted value
 * lets chose to replace with closest successor which can be find by close successor from inorder 
 * 
 * 		
 * Time Complexity : O(h) :height of the binary tree upperbound
 * Auxilary Space : 
 * 	for recursive : O(h) : h functional stack will be require
 * 
 * if tree is balanced , height will be  logn then time complexity will be O(logn)
 * if tree is unblanced ,height will be  n then time complexity is O(n) 
 * 
 * 				5
 * 					\
 * 					6
 * 						\
 * 						7
 * 							\
 * 							8	
 * 					
 */