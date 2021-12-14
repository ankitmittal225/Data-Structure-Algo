package com.datastructure.java11;


public class LinkedList{
	private Node head;
	private static class Node{
		private int value;
		private Node next;
		Node(int value){
			this.value=value;
		}
	}
	public void add(Node node) {
		if(head==null)head=node;
		else {
			Node temp=head;
			while(temp.next!=null) {
				temp=temp.next;
			}
			temp.next=node;
		}
	}
	
	public static void main(String... args) {
		LinkedList list=new LinkedList();
		Node head=new Node(1);
		list.add(head);
		list.add(new Node(2));
		list.add(new Node(3));
		list.add(new Node(4));
		list.add(new Node(5));
		list.add(new Node(6));
		list.add(new Node(7));
		list.add(new Node(8));
		list.add(new Node(9));
		list.add(new Node(10));
		System.out.println(getNthNodeFromEnd(head, 4));
	}
	
	public static int getNthNodeFromEnd(Node head, int n) {
		Node fst=head;
		Node scd=head;
		
		for(int i=0;i<n;i++) {
			fst=fst.next;
		}
		while(fst!=null) {
			fst=fst.next;
			scd=scd.next;
		}
		return scd.value;
		
	}
	
}
