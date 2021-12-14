package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T10BinaryTreeLeftView{
	static int  maxLevel=0;
	
	public static void main(String[] args) {
//		Node root=null;
		Node root=new Node(10);
		root.left=new Node(20);
		root.right=new Node(30);
//		root.left.left=new Node(80);
		root.right.left=new Node(40);
		root.right.right=new Node(100);
		root.right.left.right=new Node(50);
//		leftView(root,1);// level value is current level
		leftView2(root);
	}
	
	//Recursive solution
	public static void leftView(Node root,int level) {
		if(root==null) return;
		if(maxLevel<level) {
			System.out.print(root.key+" ");
			maxLevel=level;
		}
		leftView(root.left,level+1);
		leftView(root.right,level+1);
	}
	
	//iterative solution
	public static void leftView2(Node root) {
		if(root==null) return;
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int s=q.size();
			for(int i=0;i<s;i++) {
				Node curr=q.poll();
				if(i==0)
				System.out.print(curr.key+" ");
				if(curr.left!=null)q.add(curr.left);
				if(curr.right!=null)q.add(curr.right);
			}
			
		}
	}

}

/**
 * Left most node at atecery level
 * 
					10										30								10
				/		\										\						/		\
				20		30										50						50		60
			/		\		\									/							/		\
			40		50		60									60							70		20
																\							\
																10							8
																
O/P:		10 20 40									30 50 60 10						10 50 70 8



Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
