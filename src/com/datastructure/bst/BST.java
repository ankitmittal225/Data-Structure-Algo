package com.datastructure.bst;

public class BST {

}

/**
 * 
 * Binary Search Tree is a node-based binary tree data structure which has the following properties:
 * 		For every node, Key in the left are smaller and key in the right are greater.
 * 		All Keys are typically considered distinct.There must be no duplicate nodes.
 * 		Like Linked list, it is a linked data structure
 * 		Implemented in C++ as map, set, multimap & multiset.
 * 		Implemented in java as TreeSet or TreeMap.
 * Sample Binary Search Tree:
 * 						50
 * 				/				\
 * 				30				70
 * 			/		\		/		\
 * 			20		40		60		80	
 * The above properties of Binary Search Tree provide an ordering among keys so that the operations like search, minimum and maximum can be done fast in comparison to normal Binary Trees. If there is no ordering, then we may have to compare every key to search a given key.
 * 
 * Searching a Key
 * 
 * Using the property of Binary Search Tree, we can search for an element in O(h) time complexity where h is the height of the given BST.
 * 
 * To search a given key in Binary Search Tree, first compare it with root, if the key is present at root, return root. If the key is greater than the root's key, we recur for the right subtree of the root node. Otherwise, we recur for the left subtree.
 */
