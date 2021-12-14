package com.datastructure.tree;

/**
A Tree is a non-linear data structure where each node is connected to a number of nodes with the help of pointers or 
references.

A Sample tree is as shown below:
						30
		/				|				\
		5				11				63
	/	|	\		/	|	\		/	|	\
	1	4	8		6	7	15		31	55	65

Basic Tree Terminologies:
-Node : All the item in the tree are Node.

-Root: The root of a tree is the first node of the tree. In the above image, the root node is the node 30.

-Children :Nodes just below the node are called children of that node like 5 has 3 children 1,4 & 8

-Edge: An edge is a link connecting any two nodes in the tree. For example, in the above image there is an edge between node 11 and 6.

-Siblings: The children nodes of same parent are called siblings. That is, the nodes with same parent are called siblings. In the above tree, nodes 5, 11, and 63 are siblings.

-Leaf Node: A node is said to be the leaf node if it has no children. In the above tree, node 15 is one of the leaf nodes.

-Height of a Tree: Height of a tree is defined as the total number of levels in the tree or the length of the path from
 the root node to the node present at the last level. The above tree is of height 2.
 
-descendants : descendents of the node are all the node that lie inthe subtree with this perticular node as root.

- degree : number of children .leaf node has 0 degree.

Binary Tree:
A Tree is said to be a Binary Tree if all of its nodes have atmost 2 children. That is, all of its node can have either no child, 1 child, or 2 child nodes.

Below is a sample Binary Tree:
								1
					2							3
			4				5			6				7
		8		9		10		11			12		13	


Properties of a Binary Tree:
1. The maximum number of nodes at level 'l' of a binary tree is (2pow(l - 1)). Level of root is 1.

	This can be proved by induction.
	For root, l = 1, number of nodes = 21-1 = 1
	Assume that the maximum number of nodes on level l is 2l-1.
	Since in Binary tree every node has at most 2 children, next level would have twice nodes, i.e. 2 * 2l-1.

2. Maximum number of nodes in a binary tree of height 'h' is (2pow(h) – 1).

	Here height of a tree is the maximum number of nodes on the root to leaf path. The height of a tree with a single
	node is considered as 1.
	This result can be derived from point 2 above. A tree has maximum nodes if all levels have maximum nodes. 
	So maximum number of nodes in a binary tree of height h is 1 + 2 + 4 + .. + 2h-1. This is a simple geometric series
	 with h terms and sum of this series is 2h – 1.
	In some books, the height of the root is considered as 0. In that convention, the above formula becomes 2pow(h+1) – 1.

3.	In a Binary Tree with N nodes, the minimum possible height or the minimum number of levels is Log2(N+1).
 	This can be directly derived from point 2 above. If we consider the convention where the height of a leaf node is considered 0, then above formula for minimum possible height becomes Log2(N+1) – 1.
	A Binary Tree with L leaves has at least (Log2L + 1) levels. A Binary tree has maximum number of leaves (and minimum number of levels) when all levels are fully filled. Let all leaves be at level l, then below is true for number of leaves L.
   			L   <=  2l-1  [From Point 1]
   			l =  Log2L + 1 
   		where l is the minimum number of levels. 
4.  In a Binary tree in which every node has 0 or 2 children, the number of leaf nodes is always one more than the nodes with two children.
   			L = T + 1
			Where L = Number of leaf nodes
   				T = Number of internal nodes with two children

Types of Binary Trees: Based on the structure and number of parents and children nodes, 
						a Binary Tree is classified into the following common types:

Full Binary Tree:
 A Binary Tree is full if every node has either 0 or 2 children. The following are examples of a full binary tree. 
 We can also say that a full binary tree is a binary tree in which all nodes except leave nodes have two children.

				  18								18							18
			/			\						/		\					/		\
			15			30						15		20					40		30
		/		\	/		\				/		\							/		\
		40		50	100		40				40		50							100		40
										/		\
										30		50
	In a Full Binary, the number of leaf nodes is number of internal nodes plus 1.
	
Complete Binary Tree:
 A Binary Tree is a complete Binary Tree if all levels are completely filled except possibly the last level and the last
  level has all keys as left as possible

Following are the examples of Complete Binary Trees:

	
				  18								18							
			/			\						/			\					
			15			30						15			30							
		/		\	/		\				/		\	/		\					
		40		50	100		40				40		50	100		40						
										/		\	/
										8		7	9
		
Perfect Binary Tree:
 A Binary tree is a Perfect Binary Tree when all internal nodes have two children and all the leave nodes are at the
  same level.

Following are the examples of Perfect Binary Trees:
	
	
				  18								18							
			/			\						/			\					
			15			30						15			30							
		/		\	/		\								
		40		50	100		40								

 
A Perfect Binary Tree of height h (where height is the number of nodes on the path from the root to leaf) has 2h – 1 node. 


Application of Binary trees:

-	To represent hierarchical data:
	-	organization structure
	-	Folder Structure
	-	XML/HTML Content
	-	In OOPS(Inheritence)
	
-	Binary Search Tree:
-	Binary Heap
-	B & B+ tree in DBMS
-	Spanning & shortest Path tree in computer networks
-	Parse tree, Expression tree in compiler
-	Trie
-	Suffix tree :  pattern search for text 
-	Binary Index tree : range query search
-	Segment tree : range query search










 */


