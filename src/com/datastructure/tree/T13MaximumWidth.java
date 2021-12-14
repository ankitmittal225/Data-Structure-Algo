package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class T13MaximumWidth{
	static int  maxLevel=0;
	
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
		System.out.println(maxWidth(root));
	}
	
	//Recursive solution
	public static int maxWidth(Node root) {
		if(root==null) return 0;
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		int res=0;
		
		while(!q.isEmpty()) {
			int size=q.size();
			res=Math.max(res, size);
			for(int i=0;i<size;i++) {
			Node cur=q.poll();
			if(cur.left!=null)q.add(cur.left);
			if(cur.right!=null)q.add(cur.right);
			 }
		}
		return res;
	}

}

/**
 * Balanced Height : Difference between left subtree & right subtree height is not more than 1.
 * 
					20				5		null				3								10
				/		\			/						/		\						/				\
				8		12			4						1		2						8				2
			/		\				/					/		\	/	\				/		\		/
			3		5				6					1		2	7	7				6		2		6
																			\				/		\
																			9				20		10
																
O/P:		2						1		0					3						3



Time Complexity : O(n) 
Space Complexity : O(h+1)=O(h)

 */
