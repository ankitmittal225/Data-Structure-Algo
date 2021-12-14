package com.datastructure.bst;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.datastructure.bst.Node;

//Idea: Keep the height of tree as O(H)
public class T07AVLTree{
//		public static void main(String... args) {
////			Node root=null;
////			Node root=new Node(50);
////			root.left=new Node(30);
////			root.right=new Node(70);
////			root.left.left=new Node(20);
////			root.left.right=new Node(40);
////			root.right.left=new Node(60);
////			root.right.right=new Node(80);
////			root.right.left.left=new Node(55);
////			root.right.left.right=new Node(65);
////			root=bstDelete(root,100);
////			levelOrderTraversal(root)/;
////			levelOrderTraversal(root);
//		}
		
		private static void main(String... args) {
			// TODO Auto-generated method stub
			int days=getDays("2020-11-10 11:45","2020-11-12 00:45");

		}
		public static int getDays(String date1,String date2) {
			if(date1.isEmpty()||date2.isEmpty()) return 0;
			date1.replace(" ","+");
			date1.replace(" ","+");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
			LocalDate date1After = LocalDate.parse(date1);
			LocalDate date2After = LocalDate.parse(date2);
				
			//calculating number of days in between
			long noOfDaysBetween = ChronoUnit.DAYS.between(date1After, date2After);
			System.out.println("---->"+noOfDaysBetween);
			return 0;
		}
		
		
		
		
}
/**
 * AVL TREE : Strict in terms of balance 
 * 	1.It is a BST (for every node, left subtree is smaller & right subtree is greater)
 * 	2.It is balanced(for every node , difference between left and right heights doesn't exceed one)
 * 			Balance Factor=|lh-rh|
 * 			Balance Factor <=1 
 * 
 * Example : 
 * 
 * 						18-0									18-2
 * 				/				\							/
 * 				6-1				20-1						12-1
 * 			/				/							/
 * 			2-0				19-0						8-0
 * 
 * 			AVL Tree									Not An AVL Tree
 * 
 * 
 * Operation : 
 * 		Search will be same like bst
 * 		Insert & delete will use rotation for balance
 * 		Only Ancestor node will be affected by node inserted
 * 
 * Insert Operation : 
 * 		Perform normal BST insert
 * 		Traverse all the ancestor of the newly inserted node from the node to root.
 * 		if find an unbalanced node, check for any of the below cases
 * 				Left Left		-Single rotation
 * 				Right Right		-single rotation
 * 				Left Right		-double rotaion	
 * 				Right Left		-double roatation
 * 
 * 
 * Eg : 20,15,5,40,50,18
 * 
 * 20			20			20			15			15				15					15				15
 * 			/			/			/		\	/		\		/		\			/		\		/		\
 * 			15			15			5		20	5		20		5		20			5		40		5		40
 * 					/										\				\			/		\		/		\
 * 					5										40				40			20		50		20		50
 * 					left left Case												\						/
 * 																				50						18
 * 																right right case					right left case	
 * 																										|
 * 																20										15
 * 															/		\								/		\	
 * 															15		40								5		20
 * 														/		\		\			--------			/		\
 * 														5		18		50								18		40
 * 																													\
 * 																													50		
 * Height of AVL Tree:
 * h<Clog2(n+2)+b
 * where c~1.4405
 * 		 b~-1.3277 
 * 
 * 
 * 
 * 
 * 	
 */																											
