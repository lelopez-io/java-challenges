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

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
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

            Head matrixOne = makeLLMatrix(fileOne);
            Head matrixTwo = makeLLMatrix(fileTwo);
            String result;

            // Process files with given operation
            switch (args[0]) {
            case "add":
                try (PrintWriter out = new PrintWriter(args[3])) {
                    result = addLLMatrix(matrixOne, matrixTwo);
                    out.println(result);
                } catch (IOException e) {
                    System.out.println("Failed to output Solution 1");
                    System.exit(0);
                }
                break;
            case "sub":
                try (PrintWriter out = new PrintWriter(args[3])) {
                    result = subLLMatrix(matrixOne, matrixTwo);
                    out.println(result);
                } catch (IOException e) {
                    System.out.println("Failed to output Solution 1");
                    System.exit(0);
                }
                break;
            case "mul":
                result = mulLLMatrix(matrixOne, matrixTwo);
                System.out.println(result);
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

    static class Node {
        int data;
        Node right = null;
        Node down = null;
    }

    static class Head {
        Node first = null;

        int rows;
        int cols;
    }

    private static Head makeLLMatrix(String file) throws IOException {

        Scanner in = new Scanner(file);
        Scanner line = null;

        int rowsTotal = 0;
        int colsTotal = 0;
        int rowCount = 0; // use to count current row
        int colCount = 0; // use to count current column
        int number = -999;

        Head tmpHead = new Head();
        Node tmpNode = new Node();
        Node prevNode = new Node();
        Node prevRow = new Node();
        Node prevRowNode = new Node();

        // Row loop
        while (in.hasNext()) {

            // Column loop
            line = new Scanner(in.nextLine());
            while (line.hasNextInt()) {
                number = line.nextInt();
                // System.out.printf("%d, ", number);
                tmpNode.data = number;

                if (rowCount == 0 && colCount == 0) {
                    tmpHead.first = tmpNode;
                    prevRow = tmpNode;
                } else if (rowCount > 0 && colCount == 0) {
                    prevRowNode = prevRow;
                    prevRow = tmpNode;
                } 

                
                if (rowCount > 0) {
                    prevRowNode.down = tmpNode;
                    prevRowNode = prevRowNode.right;
                }

                if (colCount > 0) {
                    prevNode.right = tmpNode;
                }

                
                prevNode = tmpNode;
                tmpNode = new Node();
                colCount++;
            }
            // On exit check if column size is good
            if (colsTotal == 0) {
                colsTotal = colCount;
            } else if (colCount > colsTotal) {
                System.out.println("Column size error");
                System.out.println("\tEnsure all columns are the same size.");
                System.exit(0);
            }

            // Reset column count for next row
            colCount = 0;
            rowCount++;

            // System.out.println("");
        }

        rowsTotal = rowCount;

        // will use these to confirm two matricies can be operated on
        tmpHead.rows = rowsTotal;
        tmpHead.cols = colsTotal;

        in.close();
        line.close();
        return tmpHead;
    }

    private static String addLLMatrix( Head matrixOne, Head matrixTwo) {
        // fist check to see if matrices are
        if ((matrixOne.cols != matrixTwo.cols) || (matrixOne.rows != matrixTwo.rows)) {
            System.out.println("The given matricies are not the same size");
            System.out.println("Program will now exit: FAILED TO ADD");
            System.exit(0);
        }

        String result = "";
        float tmpVal = 0.0f;
        Node colA = matrixOne.first;
        Node rowA = matrixOne.first;
        Node colB = matrixTwo.first;
        Node rowB = matrixTwo.first;
        
        for (int i = 0; i < matrixOne.rows; i++) {
            for (int j = 0; j < matrixOne.cols; j++) {
                tmpVal = (float) colA.data + colB.data;
                result += String.format("%4.1f ", tmpVal);

                colA = colA.right;
                colB = colB.right;
            }
            result = result.substring(0, result.length() - 1) + "\n";
            colA = rowA.down;
            colB = rowB.down;
            rowA = colA;
            rowB = colB;
        }

        return result.substring(0, result.length() - 1);
    }

    private static String subLLMatrix( Head matrixOne, Head matrixTwo) {
        // fist check to see if matrices are
        if ((matrixOne.cols != matrixTwo.cols) || (matrixOne.rows != matrixTwo.rows)) {
            System.out.println("The given matricies are not the same size");
            System.out.println("Program will now exit: FAILED TO SUB");
            System.exit(0);
        }

        String result = "";
        float tmpVal = 0.0f;
        Node colA = matrixOne.first;
        Node rowA = matrixOne.first;
        Node colB = matrixTwo.first;
        Node rowB = matrixTwo.first;
        
        for (int i = 0; i < matrixOne.rows; i++) {
            for (int j = 0; j < matrixOne.cols; j++) {
                tmpVal = (float) colA.data - colB.data;
                result += String.format("%4.1f ", tmpVal);

                colA = colA.right;
                colB = colB.right;
            }
            result = result.substring(0, result.length() - 1) + "\n";
            colA = rowA.down;
            colB = rowB.down;
            rowA = colA;
            rowB = colB;
        }

        return result.substring(0, result.length() - 1);
    }

    private static String mulLLMatrix (Head matrixA, Head matrixB) {
        // fist check to see if matrices are
        if ((matrixA.cols != matrixB.rows) || (matrixA.rows != matrixB.cols)) {
            System.out.println("The given matricies are compatible");
            System.out.println("Program will now exit: FAILED TO MULTIPLY");
            System.exit(0);
        }

        String result = "";
        float tmpVal = 0.0f;
        Node colA = matrixA.first;
        Node rowA = matrixA.first;
        Node colB = matrixB.first;
        Node rowB = matrixB.first;

        // for (int i = 0; i < matrixA.col; i++) {
        //     for (int j = 0; j < matrixB.rows; j++) {
        //         tempValue += colA.data * rowB.data;

        //         colA = colA.right;
        //         rowB = rowB.down;

        //     }
        // }
        result += String.format("%4.1f ", recAB(rowA, colB));

        
        return result;
    }

    private static float recAB (Node rowA, Node colB) {
        if (rowA == null || colB == null) {
            return 0.0f;
        } else {
            return (float) rowA.data * colB.data + recAB(rowA.right, colB.down);
        }
    }
}