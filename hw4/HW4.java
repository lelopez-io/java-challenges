///////////////////////////////// [Homework 4] /////////////////////////////////

/*  REQUIREMENTS: 
 *      - Solve the puzzle of Tower of Hanoi for 3, 4, and 5 pegs
 *      - The tower may have any number of pegs
 *      - The tower will start on the left most peg with the goal of stacking
 *          it on the right most peg
 *      - Disks are numbered with 1 being the smalles and N being the largest
 *      - Pegs are lettered with the first peg being A the last being C, D, or E
 *      - Solution should be recursive and support any number of disks
 *      - Solution should use all pegs even for cases with 4 and 5 pegs
 * 
 *  INPUT: 
 *      - Program should take three arguments
 *          1. Number of Pegs (3, 4, or 5)
 *          2. Number of Disks
 *          3. Output File Name
 * 
 *  OUTPUT: 
 *      - A file with the various moves of the various pegs
 *          [DISK] [FROM] [TO]
 *      
 *      - ex. Moving disk 1 from peg A to B will produce
 *          1 A B
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.IntBuffer;

public class HW4 {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            // Exit program if minimum requirments are not met
            System.out.println("Program requires three arguments:");
            System.out.println("\t1. Number of Pegs (3, 4, or 5)");
            System.out.println("\t2. Number of Disks");
            System.out.println("\t3. Output File Name");
            System.exit(0);
        }

        int numPegs = Integer.parseInt(args[0]);
        int numDisks = Integer.parseInt(args[1]);
        PrintWriter out = new PrintWriter(args[2]);
        String moves = "";

        switch(numPegs) {
            case 3:
                moves = moveThree(numDisks, 'A', 'C', 'B');
                break;
            case 4:
                moves = moveFour(numDisks, 'A', 'D', 'B', 'C');
                break;
            case 5:
                moves = moveFive(numDisks, 'A', 'E', 'B', 'C', 'D');
                break;
            default:
                System.out.println("Invalid number of Pegs");
                System.exit(0);
        }

        out.println(moves.trim());
        out.close();
        System.exit(0);

        System.out.println();
    }

    public static String moveThree(int disk, char from, char to, char spare) {
        if (disk == 1) return String.format("%d %c %c\n", disk, from, to);

        String moves = "";
        moves += moveThree(disk - 1, from, spare, to);
        moves += String.format("%d %c %c\n", disk, from, to);
        moves += moveThree(disk - 1, spare, to, from);

        return moves;
    }

    public static String moveFour(int disk, char from, char to, char spare1, char spare2){
        if (disk == 0) return "";
        if (disk == 1) return String.format("%d %c %c\n", disk, from, to);

        String moves = "";
        moves += moveFour(disk - 2, from, spare1, spare2, to);
        moves += String.format("%d %c %c\n", disk-1, from, spare2);
        moves += String.format("%d %c %c\n", disk, from, to);
        moves += String.format("%d %c %c\n", disk-1, spare2, to);
        moves += moveFour(disk - 2, spare1, to, from, spare2);

        return moves;
    }

    public static String moveFive(int disk, char from, char to, char s1, char s2, char s3) {
        if (disk <= 0) return "";
        if (disk == 1) return String.format("%d %c %c\n", disk, from, to);

        String moves = "";
        moves += moveFive(disk -3, from, s1, s2, s3, to);
        moves += String.format("%d %c %c\n", disk-2, from, s3);
        moves += String.format("%d %c %c\n", disk-1, from, s2);
        moves += String.format("%d %c %c\n", disk, from, to);
        moves += String.format("%d %c %c\n", disk-1, s2, to);
        moves += String.format("%d %c %c\n", disk-2, s3, to);
        moves += moveFive(disk -3, s1, to, from, s2, s3);


        return moves;
    }
}