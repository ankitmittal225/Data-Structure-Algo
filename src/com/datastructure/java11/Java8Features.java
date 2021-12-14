package com.datastructure.java11;

public class Java8Features {

	/**
	 * Some of the important Java 8 features are;
         
		- forEach() method in Iterable interface
		- default and static methods in Interfaces
		- Functional Interfaces and Lambda Expressions
		
		@FunctionalInterface annotation. Functional interfaces are a new concept introduced in Java 8. 
		An interface with exactly one abstract method becomes a Functional Interface. 
		We don’t need to use @FunctionalInterface annotation to mark an interface as a Functional Interface.
		Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println("My Runnable");
            }};
            
        Since functional interfaces have only one method, lambda expressions can easily provide the method 
        implementation. We just need to provide method arguments and business logic. For example, we can write above 
        implementation using lambda expression as:

			Runnable r1 = () -> {
			            System.out.println("My Runnable");
			        };
		
		If you have single statement in method implementation, we don’t need curly braces also. For example above 
		Interface1 anonymous class can be instantiated using lambda as follows:

				Interface1 i1 = (s) -> System.out.println(s);
				         
				i1.method1("abc");
		- Java Stream API for Bulk Data Operations on Collections
		- Java Time API
		- Collection API improvements
		- Concurrency API improvements
		- Java IO improvements
	 */
	
	
	/**
	* Java 9 
	  	- REPL (JShell)
		- Factory Methods for Immutable List, Set, Map and Map.Entry
		- Private methods in Interfaces
		- Java 9 Module System
		- Process API Improvements
		- Try With Resources Improvement
		- CompletableFuture API Improvements
		- Reactive Streams
		- Diamond Operator for Anonymous Inner Class
		- Optional Class Improvements
		- Stream API Improvements
		- Enhanced @Deprecated annotation
		- HTTP 2 Client
		- Multi-Resolution Image API
		- Miscellaneous Java 9 Features
	 * 
	 * 
	 */
	
	
	
	
}

