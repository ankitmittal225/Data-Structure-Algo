package com.basicjava.predictoutput;

public class IntChar {
	
	private void function(int c)
    {
       System.out.println("int ->"+c);
    }
	private void function(char c)
    {
       System.out.println("char ->"+c);
    }
	
//	private void function(char c,int i)
//    {
//       System.out.println("char ->"+c+" : "+"int ->"+i);
//    }
	
	private void function(int i,char c)
    {
       System.out.println("int ->"+i+" : "+"char ->"+c);
    }
	
	private void function(int i,int j)
    {
       System.out.println("int ->"+i+" : "+"j ->"+j);
    }
    public static void main(String[] args)
    {
    	IntChar obj = new IntChar();
        obj.function('c');
        obj.function(32);
        obj.function(44,'a');
        obj.function('b',55);
    }

}
