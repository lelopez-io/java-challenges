///////////////////////////////// [Homework 2] /////////////////////////////////

/*  REQUIREMENTS: 
 *      - Read two matricies as inputs from two different text files.
 *      - Employ a singly/doubly linked list representation to store the two
 *        matricies internally and perform the following equations:
 *          1. Add
 *          2. Subtract
 *          3. Multiply
 *          4. Transpose
 *          5. Determinant
 * 
 *  RESTRICTIONS:
 *      - No existing frameworks or library methods for matrix operations
 *      - No Arrays, Lists, ArrayLists, Vectors 
 *      - No reading the file more than once
 * 
 *  PERMITTED:
 *      - Nodes can retain row/column id for recursion during determinant op
 *      - Design a custom node representation
 * 
 *  INPUT: 
 *      - Contains only integers
 * 
 *  OUTPUT: 
 *      - Floating point format with 1 decimal place (e.g. -3.0)
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintWriter;

import java.io.IOException;

public class HW2 {
    public static void main(String[] args) throws IOException {

        // Program may have 3 or 4 arugments: operation, input(s).txt, ouput.txt
        if (args.length == 4) {
            String fileOne = "";
            String fileTwo = "";

            // Store files to varibles
            try {
                fileOne = readFile(args[1]);
                fileTwo = readFile(args[2]);
            } catch (IOException e) {
                System.out.println("Failed to read files in given path");
                System.exit(0);
            }

            // Process files with given operation
            switch (args[0]) {
            case "add":
                System.out.println("will add");
                String out = make2DLL(fileOne);
                break;
            case "sub":
                System.out.println("will sub");
                break;
            case "mul":
                System.out.println("will mul");
                break;
            default:
                opErr(args[0]);
                break;
            }

        } else if (args.length == 3) {
            String fileOne = "";

            // Store file to variable
            try {
                fileOne = readFile(args[1]);
                System.out.println(fileOne);
            } catch (IOException e) {
                System.out.println("Failed to read files in given path");
                System.exit(0);
            }

            // Process file with given operation
            switch (args[0]) {
            case "tra":
                System.out.println("will transpose");
                break;
            case "det":
                System.out.println("will find determinant");
                break;
            default:
                opErr(args[0]);
                break;
            }

        } else {
            // Exit program if minimum requirments are not met
            System.out.println("Program requires three or four arguments:");
            System.out.println("\t1. Operation (tra, det) or [add, sub, mul]");
            System.out.println("\t2. first input file");
            System.out.println("\t3. second input file [optional]");
            System.out.println("\t4. output file name");
            System.exit(0);
        }
    }

    private static void opErr(String operation) {
        System.out.printf("'%s' is invalid for given input.\n", operation);
        System.out.println("USE: 'add', 'sub', or 'mul' for two input files");
        System.out.println("USE: 'tra' or 'det' for one input file");
        System.exit(0);
    }

    private static String readFile(String filename) throws IOException {
        try {
            // "It is advisable to wrap a BufferedReader around any Reader whose
            // read() operations may be costly"
            BufferedReader in = new BufferedReader(new FileReader(filename));

            String fileText = "";
            String line = null;

            // Read one line at a time and add to fileText
            while ((line = in.readLine()) != null) {
                fileText = fileText + line + "\n";
            }

            // Finish by closing BufferedReader and return results
            in.close();

            return fileText;

        } catch (IOException ex) {
            System.out.println("File does not exist within given directory");
            return null;
        }
    }

    private static String make2DLL(String file) throws IOException {

        Scanner in = new Scanner(file);
        Scanner line = null;

        int number = -999;

        while (in.hasNext()) {
   

            line = new Scanner(in.nextLine());
            while (line.hasNextInt()) {
                number = line.nextInt();
                System.out.printf("%d, ", number);
            }

            System.out.println("");
        }

        in.close();
        line.close();
        return null;
    }
}