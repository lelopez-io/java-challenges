import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;

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
 *  3. List the word(s) with the highest frequency in a sentence across all sentences
 *     in the whole file, also print its frequency and the corresponding sentence.
 * 
 *  4. List sentence(s) with the maximum no. of occurrences of the word "the" in the
 *     entire file and also list the corresponding frequency.
 * 
 *  5. List sentence(s) with the maximum no. of occurrences of the word "of" in the 
 *     entire file and also list the corresponding frequency.
 * 
 *  6. List sentence(s) with the maximum no. of occurrences of the word "was" in the 
 *     entire file and also list the corresponding frequency.
 * 
 *  7. List sentence(s) with the maximum no. of occurrences of the phrase "but the" 
 *     in the entire file and also list the corresponding frequency.
 * 
 *  8. List sentence(s) with the maximum no. of occurrences of the phrase "it was" in
 *     the entire file and also list the corresponding frequency.
 * 
 *  9. List sentence(s) with the maximum no. of occurrences of the phrase "in my" in 
 *     the entire file and also list the corresponding frequency.
 * */

public class HW1 {
    // Program has two arguments: path to input txt file & name of outputfiles' prefix
    public static void main(String[] args) throws FileNotFoundException {
    
    // sentenceList: ArrayList of sentences, containing ArrayList of words.
    // newline defines a paragraph so '\n' and '.' will be sentence delimiters.
    // spaces char '32' will be the word delimiter within sentences
    ArrayList<ArrayList<String>> sentenceList = new ArrayList<ArrayList<String>>();
    ArrayList<String> wordList = new ArrayList<String>();
    // Temporarly holds word being read.
    String tempWord;

    if (args.length > 1) {


    } else {
        System.out.println("program requires two inputs:");
        System.out.println("\t1. Path to input .txt file");
        System.out.println("\t2. prefix to output files");
        System.exit(0);
    }



    }
}