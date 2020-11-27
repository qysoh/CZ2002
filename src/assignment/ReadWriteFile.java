package assignment;

import java.io.*;
import java.util.*;

public class ReadWriteFile {

/**
 * Writes content to the given file.
 * @param fileName Specific file to write to
 * @param allData List containing content which is written to file as String
 * @throws IOException If an input or output exception occurred
 */
 public static void writeToFile(String fileName, List allData) throws IOException {
	  PrintWriter output = new PrintWriter(new FileWriter(fileName));
	  try {
	   for (int i = 0; i < allData.size(); i++) {
	    output.println((String) allData.get(i));
	  }
	  } finally {
	  output.close();
	  }
}

 /**
  * Reads the contents of the given file.
  * @param fileName Specific file to read from
  * @return Contents of the file as List
  * @throws IOException If an input or output exception occurred
  */
public static List read(String fileName) throws IOException {
  List allData = new ArrayList();
  Scanner sc = new Scanner(new FileInputStream(fileName));
  try {
	   while (sc.hasNextLine()) {
	   allData.add(sc.nextLine());
	   }
  } finally {
	   sc.close();
  }
  return allData;
 }
}