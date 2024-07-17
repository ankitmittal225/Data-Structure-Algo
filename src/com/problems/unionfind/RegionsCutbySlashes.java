package com.problems.unionfind;

/**
 * An n×n grid is composed of n, 1×1 squares, where each 1×1
 * square consists of a “/”, “\”, or a blank space. These characters divide the square into adjacent regions.
 *
 * Given the grid represented as a string array, return the number of regions.
 *
 * Note: Backslash characters are escaped, so “\” is represented as “\\”.
 * A 1×1 square in the grid will be referred to as a box.
 */
public class RegionsCutbySlashes {

}


/**
 * The idea is to divide each 1×1
 *  box in the grid into four regions representing its north, west, east, and south regions. At the start, an
 * n×n grid will contain (4×n×n)
 *  regions.
 * We will gradually reduce the number of regions by merging the regions inside each box based on the character they contain and then connecting them with their neighboring boxes. This merging and connecting will be done using the union find algorithm. In the union find algorithm, the union by rank and path compression optimizations will be applied. In the end, the number of connected components will represent the correct number of regions inside the grid.
 *
 * Here’s how the algorithm works:
 *
 * We create a parent array containing 4×n×n elements where each element equals its corresponding index.
 *
 * Every four consecutive elements in this array represent a 1×1 box in the grid.
 *
 * We associate each 1×1 grid square with four nodes—north, west, east, and south—representing four triangles inside the square. That’s how every one of these four elements inside the parent list represents the north, west, east, and south components of the box, respectively.
 *
 * Each element in the array is initially equal to its respective index. For example:
 *
 * The grid[0][0] box will comprise of the elements: 0, 1, 2, and 3.
 *
 * The grid[0][1] box will comprise of the elements: 4, 5, 6, and 7, and so on.
 *
 * Next, we traverse each character of the grid array:
 *
 * We use the union method to merge the regions within each box depending on the character it contains:
 *
 * If the box contains a “/” character, it will be partitioned into two regions. Therefore, we will merge the north-west and east-south regions to convert the box into two connected components.
 *
 * If the box contains a “\\” character, it will be partitioned into two regions. Therefore, we will merge the north-east and west-south regions to convert the box into two connected components.
 *
 * If the box contains a " " character, it will be converted to a single region. Therefore, we combine all four regions of the box to convert the box into a single connected component.
 *
 */
