package com.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxHeight {

	public static void main(String[] args) {//		Node root=null;
//		Node root=new Node(20);
//		root.left=new Node(8);
//		root.right=new Node(12);
//		root.left.left=new Node(3);
//		root.left.right=new Node(5);
////		root.right.left=new Node(40);
//		root.right.right=new Node(100);
//		root.right.right.right=new Node(50);
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
//		leftView(root,1);// level value is current level
		System.out.println(maxHeight(root));
		// TODO Auto-generated method stub

	}

	//height will be one less than actual height
	private static int maxHeightOfTree(Node root) {
		if(root==null) return -1;
		return 1+Integer.max(maxHeightOfTree(root.left),maxHeightOfTree(root.right));
	}
	
	private static int maxHeight(Node root) {
		if(root==null) return -1;
		int count=0;
		Queue<Node> q=new LinkedList<>();
		q.add(root);
		q.add(null);
		System.out.println(count);
		while(q.size()>1) {
			Node temp=q.poll();
			if(temp==null) {
				q.add(null);
				count++;
				continue;
			}
			if(temp!=null && temp.left!=null) q.add(temp.left);
			if(temp!=null && temp.left!=null) q.add(temp.right);
		}
		System.out.println(count);
		return count;
	}

}
