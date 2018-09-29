///////////////////////////////// [Homework 2] /////////////////////////////////

/*  REQUIREMENTS: 
 *      - Conduct experimental analysis on the given algorithms
 *      - 
 * 
 *  INPUT: 
 *      - Program should take three arguments
 *          1. Algorithm (function) to test
 *          2. Name of the output file
 *          3. Upper limit of input array size as an integer
 * 
 *  OUTPUT: 
 *      - A file with the various times it took to complete the function
 *      - time should be measured in milliseconds then stored after taking
 *          its Log10, also one mesurement per line
 *      
 * */


import java.io.IOException;
import java.io.PrintWriter;

public class HW3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            // Exit program if minimum requirments are not met
            System.out.println("Program requires three arguments:");
            System.out.println("\t1. Function to test");
            System.out.println("\t2. Name of the output file");
            System.out.println("\t3. Upper limit of input array size");
            System.exit(0);
        }

        PrintWriter out = new PrintWriter(args[1]);
        
        int upprLmt = Integer.parseInt(args[2]);
        int currentSize;
        double result;

        for (int i = 1; i <= upprLmt; i++) {
            currentSize = (int) Math.pow(10, i);
            result = timeAlgo(args[0], currentSize);
            out.println( Double.isInfinite(result) ? "0" : result );
        }
        out.close();
        System.exit(0);
    }

    public static double timeAlgo(String algo, int arrSize) {
        long time = 0;

        switch (algo) {
            case "p1":
                time = System.currentTimeMillis();
                prefixAverage1(new double[arrSize]);
                time = System.currentTimeMillis() - time;
                break;
            case "p2":
                time = System.currentTimeMillis();
                prefixAverage2(new double[arrSize]);
                time = System.currentTimeMillis() - time;
                break;
            case "e1":
                time = System.currentTimeMillis();
                example1(new int[arrSize]); 
                time = System.currentTimeMillis() - time;
                break;
            case "e2":
                time = System.currentTimeMillis();
                example2(new int[arrSize]); 
                time = System.currentTimeMillis() - time;
                break;
            case "e3":
                time = System.currentTimeMillis();
                example3(new int[arrSize]); 
                time = System.currentTimeMillis() - time;
                break;
            case "e4":
                time = System.currentTimeMillis();
                example4(new int[arrSize]); 
                time = System.currentTimeMillis() - time;
                break;
            case "e5":
                time = System.currentTimeMillis();
                example5(new int[arrSize], new int[arrSize]);
                time = System.currentTimeMillis() - time;
                break;
            default:
                System.out.println("First argument is an invalid option");
                System.out.println("Choose: p1, p2, e1, e2, e3, e4, or e5");
                System.exit(0);     
        }

        return Math.log10(time);
    }

    // p1
    public static double[] prefixAverage1(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        for (int j = 0; j < n; j++) {
            double total = 0;
            for (int i = 0; i <= j; i++) {
                total += x[i];
            }
            a[j] = total / (j + 1);
        }
        return a;
    }

    // p2
    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        double total = 0;
        for (int j = 0; j < n; j++) {
            total += x[j];
            a[j] = total / (j + 1);
        }
        return a;
    }

    // e1
    public static int example1(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j++)
            total += arr[j];
        return total;
    }

    // e2
    public static int example2(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j += 2)
            total += arr[j];
        return total;
    }

    // e3
    public static int example3(int[] arr) {
        int n = arr.length, total = 0;
        for (int j = 0; j < n; j++)
            for (int k = 0; k <= j; k++)
                total += arr[j];
        return total;
    }

    // e4
    public static int example4(int[] arr) {
        int n = arr.length, prefix = 0, total = 0;
        for (int j = 0; j < n; j++) {
            prefix += arr[j];
            total += prefix;
        }
        return total;
    }

    // e5
    public static int example5(int[] first, int[] second) {
        int n = first.length, count = 0;
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (int j = 0; j < n; j++)
                for (int k = 0; k <= j; k++)
                    total += first[k];
            if (second[i] == total) count++;
        }
        return count;
    }
}