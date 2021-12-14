package com.datastructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.datastructure.bst.Node;

public class T02BSTInsert {
		public static void main(String... args) {
			Node root=null;
//			Node root=new Node(10);
//			root.left=new Node(5);
//			root.right=new Node(15);
////			root.left.left=new Node(40);
////			root.left.right=new Node(50);
//			root.right.left=new Node(12);
//			root.right.right=new Node(18);
////			root.right.left.right=new Node(80);
			root=bstInsert(root,15);
			root=bstInsert(root,100);
			levelOrderTraversal(root);
			levelOrderTraversal(root);
		}
		
		public static Node bstInsert(Node root, int key) {
			// Recursive approach
//			if(root==null)root= new Node(key);
//			else if(root.key<key) root.right= bstInsert(root.right, key);
//			else if(root.key>key) root.left= bstInsert(root.left, key);
//			return root;
			
			//Iterative approach
			Node temp=new Node(key);
			Node curr=root;
			Node parent=null;
			while(curr!=null) {
				parent=curr;
				if(curr.key<key) curr=curr.right;
				else if(curr.key>key)  curr=curr.left;
				else return root;
			}
			if(parent==null) return temp;
			if(parent.key>key)parent.left=temp;
			else parent.right=temp;
			return root;
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
 * Input : x=20								x=10										x=10		
 * Tree : 
 * 				10							null										8	
 * 			/		\																/		\	
 * 			5		15																2		10
 * 				/		\
 * 				12		18			
 * 
 * 
 * Output:	root of following			
 * 				10							10											8	
 * 			/		\																/		\	
 * 			5		15																2		10
 * 				/		\
 * 				12		18
 * 							\
 * 							20	
 * 
 * 		
 * Time Complexity : O(h) :height of the binary tree upperbound
 * Auxilary Space : 
 * 	for recursive : O(h) : h functional stack will be require
 * 	for iterative : O(1) : doesn't require any space
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