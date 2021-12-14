package com.basicjava.predictoutput;

interface Interface1{
	 void print(); 
} 
interface Interface2{
	 void print(); 
} 
public class InterfaceOverriding implements Interface1,Interface2{
	public static void main(String[] args) {
		InterfaceOverriding io=new InterfaceOverriding();
		io.print();
	}

	@Override
	public void print() {
		System.out.println("Hello");
		
	}
	
	
	
}
