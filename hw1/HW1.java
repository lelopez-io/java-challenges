import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////

/* HW1 - completed ab inito, 
 *  using only the following librarys & structs:
 *  - String operations ex. split()
 *  - java.util.regex to match target words and phrases
 *  - ArrayList
 *  - Standard file IO facilites for reading/writing 
 * 
 *  Requirements:
 *  1. List the most frequent word(s) in the whole file and its frequency.
 *      Δ 
 * 
 *  2. List the 3rd most frequent word(s) in the whole file and its frequency.
 * 
 *  3. List the word(s) with the highest frequency in a sentence across all 
 *      sentences in the whole file, also print its frequency and the 
 *      corresponding sentence.
 * 
 *  4. List sentence(s) with the maximum no. of occurrences of the word "the" 
 *      in the entire file and also list the corresponding frequency.
 * 
 *  5. List sentence(s) with the maximum no. of occurrences of the word "of" in 
 *      the entire file and also list the corresponding frequency.
 * 
 *  6. List sentence(s) with the maximum no. of occurrences of the word "was" 
 *      in the entire file and also list the corresponding frequency.
 * 
 *  7. List sentence(s) with the maximum no. of occurrences of the phrase 
 *      "but the" in the entire file and also list the corresponding frequency.
 * 
 *  8. List sentence(s) with the maximum no. of occurrences of the phrase 
 *      "it was" in the entire file and also list the corresponding frequency.
 * 
 *  9. List sentence(s) with the maximum no. of occurrences of the phrase 
 *      "in my" in the entire file and also list the corresponding frequency.
 * */

public class HW1 {
    // Program has two arguments: path to input file & prefix of outputfiles
    public static void main(String[] args) throws IOException {

        // sentenceList: ArrayList of sentences, containing ArrayList of words.
        // newline defines a paragraph so '\n' and '.' will be sentence delimiters.
        // spaces char '32' will be the word delimiter within sentences

        if (args.length > 1) {
            try {
                List<String> paragraphList = readFile("./tiny1.txt");
            } catch (IOException e) {
                System.out.println("Failed to read file in given path");
                System.exit(0);
            }
            

        } else {
            // Exit Program if minimum requirments are not met
            System.out.println("program requires two inputs:");
            System.out.println("\t1. Path to input .txt file");
            System.out.println("\t2. prefix to output files");
            System.exit(0);
        }

    } // END main

    private static List<String> readFile(String filename) throws IOException {
        try {
            String paragraph = null;
            List<String> paragraphList = new ArrayList<String>();

            // "It is advisable to wrap a BufferedReader around any Reader whose
            // read() operations may be costly"
            BufferedReader in = new BufferedReader(new FileReader(filename));

            // Read one line at a time and add to sentenceList

            while ((paragraph = in.readLine()) != null) {
                System.out.println(paragraph);
                System.out.println("");
                paragraphList.add(paragraph);
            }

            // Finish by closing BufferedReader and return results

            in.close();

            return paragraphList;

        } catch (IOException ex) {
            System.out.println("File does not exist within given directory");
            return null;
        }
    }
}