package com.datastructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.datastructure.bst.Node;

//Floor : nearst smalller or equal value to given key
public class T04BSTFloor {
		public static void main(String... args) {
//			Node root=null;
			Node root=new Node(50);
			root.left=new Node(30);
			root.right=new Node(70);
			root.left.left=new Node(20);
			root.left.right=new Node(40);
			root.right.left=new Node(60);
			root.right.right=new Node(80);
			root.right.left.left=new Node(55);
			root.right.left.right=new Node(65);
			System.out.println(bstFloor(root,50).key);
			System.out.println(bstFloor(root,25).key);
			System.out.println(bstFloor(root,58).key);
//			root=bstDelete(root,100);
//			levelOrderTraversal(root)/;
//			levelOrderTraversal(root);
		}
		public static Node getSuccessor(Node root) {
			Node curr=root.right;
			while(curr!=null && curr.left!=null)
				curr=curr.left;
			return curr;
		}
		
		public static Node bstFloor(Node root, int key) {
			
			// solution with time comlexity O(h) & aux space O(1) 
			//go for iterative solution cuz recursive solution take O(h) extra space 
			
			//In naive solution we go with indorder,post or preorder then compare 
			//In efficient solution: consider 3 case
			// if root is equal to key return root
			// if rootkey> key go to left
			//if root<key then either root or right can be perfect fit
			Node curr=null;
			while(root!=null) {
				if(root.key==key) {
					return root;
				}
				else if(root.key>key) {
					root=root.left;
				}
				else {
					curr=root;
					root=root.right;
				}
				
			}
			return curr;
			
		}
		
		
}
/**
 * Insert : 
 * 
 * Input : x=14					x=4					x=30								x=100		
 * Tree :  root of below tree	root of smae tree  
 * 				10				null				10									  10	
 * 			/		\							/		\							/			\	
 * 			5		15							5		15							5			15
 * 				/		\							/		\								/		\
 * 				12		18							12		30								12		30
 * 
 * 
 * Output:		12				null					30								30
 * 
 * in case if deleted node has 2 child then we need to find close smaller or bigger value related to deleted value
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