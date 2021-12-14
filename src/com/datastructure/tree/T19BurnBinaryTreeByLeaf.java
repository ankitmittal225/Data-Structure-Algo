package com.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class T19BurnBinaryTreeByLeaf{
	static int res=0;
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(3);
		root.left=new Node(4);
		root.right=new Node(2);
		root.left.left=new Node(5);
		root.left.right=new Node(6);
		root.right.left=new Node(7);
		root.right.right=new Node(8);
		root.right.right.right=new Node(9);
		Node n=lca(root,7,9);
		System.out.println(n!=null?n.key:null);
	}
	
	
	
	//
	public static boolean findPath(Node root,List<Node> path,int n) {
		if(root==null) return false;
		path.add(root);
		if(root.key==n) return true;
		if(findPath(root.left, path, n) || findPath(root.right, path, n)) {
			return true;
		}
		path.remove(path.size()-1);
		return false;
	}
	
	//Method : 1
//	public static Node lca(Node root,int n1,int n2) {
//		if(root==null) return null;
//		List<Node> path1=new ArrayList<>();
//		List<Node> path2=new ArrayList<>();
//		if(findPath(root, path1, n1)==false || findPath(root, path2, n2)==false) return null;
//		for(int i=0;i<path1.size()-1 && i<path1.size()-1;i++) {
//			if(!path1.get(i+1).equals(path2.get(i+1)))return path1.get(i);
//		}
//		return null;
//	}
	
	//Method : 2 
	// TIme complexity : simple recusion O(n)
	//auxilary space :  O(h)
	public static Node lca(Node root,int n1,int n2) {
		if(root==null) return null;
		if(root.key==n1 ||root.key==n2) return root;
		Node lca1=lca(root.left, n1, n2);
		Node lca2=lca(root.right, n1, n2);
		if(lca1!=null && lca2!=null) return root;
		if(lca1!=null) return lca1;
		else return lca2;
	}

}

/**

 * Burn binary tree from leaf node : 
 * We have to create a program to provide time it will take to burn the tree starting from given leaf node.
 * To burn all adjacent node will take 1 second
 * 
 * Example in first case: 
 * if we start from 50 then
 * 	50 will burn at 0;
 * 	Adjacent node of 50= 20 will burn at 1 
 * 	Adjacent node of 20= 40,10 will burn at 2
 * 	Adjacent node of 40= null 
 * `Adjacent node of 10= 30 will burn at 3
 *
 *	Method 1 : the time will be equal to farthest node from the given leaf
 *	for example above farthest node from 50 is 30 and distance between them is 4
 *	the farthest node must be reachable throughone of the ancestor(50,20,10)
 *	distance through 50 =0
 *	distance through 20 =3
 *	distance through 10 =3
 *	
 *					10				5		null				3								10
 *				/		\			/						/		\						/				\
 *				20		30			4						4		2						8				2
 *			/		\				/					/		\	/	\				/		\		/
 *			40		50				6					5		6	7	8				6		2		6
 *																			\				/		\
 *																			9				20		10
 *
 *	I/p : 	50						4					7,9										6,10
 *	CA : 							5,4		null		3,2										10,8	
 *	LCA:  	3						2		null		2										8
 *	
 *	
 *
 *	We do normal recusive travesal.we have following cases for every node.
 *	-	if it is same as n1 or n2.
 *	-	if one of its subtree contains n1 and other contains n2
 *	-	if one of its subtree contains both n1 & n2.
 *	-	if none of its subtree contain any of n1 & n2.
 *
 *	Example for some case : 
 *	1-	n1=10, n=40, curr_node=10				o/p : 10
 *	2-	n1=80, n=40, curr_node=60				o/p : 60
 *	3-	n1=80, n=40, curr_node=30 or 10			o/p : 30
 *	4-	n1=80, n=40, curr_node=20				o/p : null
 * For example : 
 * 			10				
 * 		/		\
 * 		20		30
 * 			/		\
 * 			50		60
 * 		/		/		\
 * 		70		80		90
 * 							\
 * 							40




 */
