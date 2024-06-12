package com.problems;

class QuadTreeNode {
    int val;
    boolean isLeaf;
    QuadTreeNode topLeft;
    QuadTreeNode topRight;
    QuadTreeNode bottomLeft;
    QuadTreeNode bottomRight;

    //if requiremnet comes to keep size of original metrix
    int size;

    public QuadTreeNode(int val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
    }

    public QuadTreeNode(int val, boolean isLeaf, QuadTreeNode topLeft, QuadTreeNode topRight, QuadTreeNode bottomLeft, QuadTreeNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    // only add these constructor if asked to keep size
    public QuadTreeNode(int val, boolean isLeaf, int size) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.size = size;
    }

    public QuadTreeNode(int val, boolean isLeaf, QuadTreeNode topLeft, QuadTreeNode topRight, QuadTreeNode bottomLeft, QuadTreeNode bottomRight,int size) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.size = size;
    }
}

public class ImageRepresentationUsingQuadtree {
    public static QuadTreeNode buildQuadTree(int[][] image) {
        return build(image, 0, 0, image.length);
    }

    private static QuadTreeNode build(int[][] image, int x, int y, int length) {
        if (isUniform(image, x, y, length)) {
            return new QuadTreeNode(image[x][y], true);
        }

        int half = length / 2;
        QuadTreeNode topLeft = build(image, x, y, half);
        QuadTreeNode topRight = build(image, x, y + half, half);
        QuadTreeNode bottomLeft = build(image, x + half, y, half);
        QuadTreeNode bottomRight = build(image, x + half, y + half, half);

        return new QuadTreeNode(0, false, topLeft, topRight, bottomLeft, bottomRight);
        //incase of size
//        return new QuadTreeNode(0, false, topLeft, topRight, bottomLeft, bottomRight, length);
    }

    private static boolean isUniform(int[][] image, int x, int y, int length) {
        int val = image[x][y];
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (image[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] unarchiveQuadTree(QuadTreeNode root, int size) {
        int[][] image = new int[size][size];
        fill(image, root, 0, 0, size);
        return image;
    }

    private static void fill(int[][] image, QuadTreeNode node, int x, int y, int length) {
        if (node.isLeaf) {
            for (int i = x; i < x + length; i++) {
                for (int j = y; j < y + length; j++) {
                    image[i][j] = node.val;
                }
            }
        } else {
            int half = length / 2;
            fill(image, node.topLeft, x, y, half);
            fill(image, node.topRight, x, y + half, half);
            fill(image, node.bottomLeft, x + half, y, half);
            fill(image, node.bottomRight, x + half, y + half, half);
        }
    }

    public static void main(String[] args) {
        int[][] image = {
                {2, 2, 3, 3},
                {2, 2, 3, 3},
                {4, 2, 5, 5},
                {2, 3, 5, 5}
        };

        QuadTreeNode root = buildQuadTree(image);
        System.out.println("QuadTree built successfully.");

        int[][] decompressedImage = unarchiveQuadTree(root, image.length);

//        int[][] decompressedImage = unarchiveQuadTree(root, root.size);
        System.out.println("Original image reconstructed from quadtree:");
        for (int[] row : decompressedImage) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}


/**
 *
 * Explanation:
 * QuadTreeNode Class: Represents each node in the quadtree.A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 *
 * val: The value of the node.
 * isLeaf: Indicates whether the node is a leaf.
 * topLeft, topRight, bottomLeft, bottomRight: Pointers to the four children of the node.
 * buildQuadTree Method: Starts building the quadtree from the top-left corner of the image.
 *
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 * 1. If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * 2. If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * 3. Recurse for each of the children with the proper sub-grid.
 *
 * Key point to note here is when to stop diving the QuadTree node further.
 *
 * 1. When all values are same in a grid, stop dividing it further.
 * 2. If values are different in a grid, find coordinates of 4 sub-grids that it needs to be divided to and buildQuadTree() for all those sub-grids.
 *
 * Recursively divides the image into four quadrants until the size of each quadrant is 1.
 * If all four children of a node are leaves and have the same value, they are merged into a single leaf node.
 * Otherwise, a parent node with four children is created.
 * This implementation allows you to build a quadtree for an input image represented as a 2D array of integers. You can extend it with additional methods to traverse and print the quadtree for verification.
 */
