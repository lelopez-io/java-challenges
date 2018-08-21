import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;

import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class CombineFiles {
  public static void main(String[] args) throws FileNotFoundException {
    
    // Temporarly stores an int on a line
    int lineInt;

    // program must be given at least one input file and one output file
    if(args.length > 1) {
      int fileAmnt = args.length - 1;
      int fileSums[] = new int[fileAmnt];
      int fileIndex[] = new int[fileAmnt];
      Scanner[] file = new Scanner[fileAmnt];
      ArrayList<ArrayList<Integer>> fileList = new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> intList = new ArrayList<Integer>();

      // should check if output file already exist, ask if you wanna overwrite
      File fileOut = new File(args[fileAmnt]);
      if (fileOut.exists()){
        System.out.printf("File exists, will replace: %s\n", args[fileAmnt]);
      } else {
        System.out.printf("New file wil be made: %s\n", args[fileAmnt]);
      }

      
      // iterate through files and find their sums
      for (int i = 0; i < fileAmnt; i++) {
	      try {
          // will have to make an array of scanners with the file contents
          // will end up swapping these around instead of the index array since
          // I wont need to re-read the file
		      file[i] = new Scanner(new File(args[i]));

          fileSums[i] = 0;
		      while(file[i].hasNextInt()){
			      lineInt = file[i].nextInt();
            fileSums[i] += lineInt;

            intList.add(lineInt);
            // fileList.get(i).add(lineInt);
		      }
	      }
        // flag files not found
	      catch (FileNotFoundException ex) {
		      System.out.println("File does not exist within directory");
          fileSums[i] = -1;
	      }
        
        System.out.printf("Sum: %d\n", fileSums[i]);
        fileIndex[i] = i;
        fileList.add(intList);
        intList = new ArrayList<Integer>();
      }

      
      // iterate through file sums' and order their indecies
      for (int i = 0; i < fileAmnt; i++) {
        for (int j = i + 1; j < fileAmnt; j++) {
          int temp = 0;
          Scanner tempFile;
          
          if (fileSums[i] > fileSums[j]) {
            temp = fileSums[i];
            fileSums[i] = fileSums[j];
            fileSums[j] = temp;

            temp = fileIndex[i];
            fileIndex[i] = fileIndex[j];
            fileIndex[j] = temp;  
          }
        }
      } 

      for (int i = 0; i < fileAmnt; i++) {
        System.out.printf("Order: %d\n", fileIndex[i]);
      }
     
      PrintWriter outputStream = null;
      try {
        outputStream = new PrintWriter(new FileOutputStream(args[fileAmnt]));
      } catch (FileNotFoundException e) {
        System.out.println("File Creation Failed");
        System.exit(0);
      } 
      for (int i = 0; i < fileList.size(); i++) {
        Iterator<Integer> integerIterator = fileList.get(fileIndex[i]).iterator();
        while(integerIterator.hasNext()) {
          // System.out.printf(": %d\n", integerIterator.next());
          outputStream.println(integerIterator.next());
        }
      }
      outputStream.close();
    }
  }
}
