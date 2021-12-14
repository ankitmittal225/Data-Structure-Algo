package com.datastructure.java11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * New String Methods : Java 11 adds a few new methods to the String class: isBlank, lines, strip, stripLeading, stripTrailing, and repeat.
 * 
 */

//public class Jav11Features {
//	public static void main(String[] args) {
//		
//		
//		String multilineString = "Baeldung helps \n \n developers \n explore Java.";
//		List<String> lines = multilineString.lines()
//		  .filter(line -> !line.isBlank())
//		  .map(String::strip)
//		  .collect(Collectors.toList());
////		assert(lines).containsExactly("Baeldung helps", "developers", "explore Java.");
//		
//		/**
//		 * New File Methods
//		Additionally, it's now easier to read and write Strings from files.
//		We can use the new readString and writeString static methods from the Files class:
//		*/
//		
//		Path filePath = Files.writeString(Files.createTempFile("", "demo", ".txt"), "Sample text");
//		String fileContent = Files.readString(filePath);
//		assertThat(fileContent).isEqualTo("Sample text");
//	}
//}
