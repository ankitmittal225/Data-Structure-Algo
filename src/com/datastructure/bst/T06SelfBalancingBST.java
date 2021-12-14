package com.datastructure.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.datastructure.bst.Node;

//Idea: Keep the height of tree as O(H)
public class T06SelfBalancingBST {
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
//			root=bstDelete(root,100);
//			levelOrderTraversal(root)/;
//			levelOrderTraversal(root);
		}
		
		
		
		
		
}
/**
 * Self Balancing Tree:Self-Balancing Binary Search Trees are height-balanced binary search trees
 * that automatically keeps height as small as possible when insertion and deletion operations 
 * are performed on tree. The height is typically maintained in order of Log n so that all operations
 * take O(Log n) time on average. 
 *  
 * Idea: Keep the height of tree as O(H)
 * Background : Same set of keys can make different height BST.
 * Order 1  : 7,10,11,15,30,35,40								Order 2: 15,10,7,11,35,30,40		
 * Tree :  
 * 				7															 15	
 * 					\													/			\	
 * 					10													10			35
 * 						\											/		\	/		\
 * 						11											7		11	30		40
 *						 	\
 * 							15
 * 								\
 * 								30
 * 									\
 * 									35
 * 										\
 * 										40
 * 	
 * Output:		Height : 7												Height : 3
 * 
 * How to Make a Self balancing tree :
 *     If we know the Keys in advance,we can make a perfectly balanced BST
 *       for Example : 7,10,11,15,30,35,40 (Sorted keys)
 *       Approach:
 *       Pick the mid element Make a root
 *       for left half again pick mid key and make a node and so on in recursive way
 *       for right half again pic mid key and make a node and so on in recursive way
 *       
 *     how to keep it balance when random insertion/Deletion happening?
 *     	 The idea is to do some restructuring (or rebalance) while inserting /Deleting to balance the tree
 * 		 For example : 
 * 			inserting 100,200,300
 * 			100		100			100					-------Restructure-----				200
 * 						\			\												/			\				
 * 						200			200												100			300
 * 										\
 * 										300	
 * 			inserting 300,200,100
 * 			300			300			300					-------Restructure-----		200
 * 						/			/											/			\				
 * 					200			200												100			300
 * 								/
 * 								100	
 * 
 * 	Rotation : Focus is to following th BST rule i.e left<root<right
 * 							{t1}<y<{t2}<x<{t3}
 * 				p														p
 * 				|														|
 * 				x				------Right Rotation---->				y
 * 			/		\			<-----Left Rotation------			/		\
 * 			y		t3												t1		x	
 * 		/		\														/		\	
 * 		t1		t2														t2		t3
 * 
 * Example : 
 * 				p														p
 * 				|														|
 * 				30				------Right Rotation---->				20
 * 			/		\			<-----Left Rotation------			/		\
 * 			20		40												10		30	
 * 		/		\												/		/		\	
 * 		10		25												5		25		40
 * 		/
 * 		5
 * 
 * Types of Self Balancing:
 * 		AVL Trees - Strict in terms of balance
 * 		Red Black - loose in terms of balance (TreeSet, TreeMap in java)
 * 
 * Since Red Black tee loose in balance so it causes less structuring in terms of insert & delete
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