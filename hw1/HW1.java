import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import java.io.IOException;

// import java.util.List;
// import java.util.ArrayList;

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


        if (args.length > 1) {
            String fileText = "";

            try {
                // Read entire file to a string
                fileText = readFile(args[0]);
                
                
            } catch (IOException e) {
                System.out.println("Failed to read file in given path");
                System.exit(0);
            }

            fileText = fileText.toLowerCase();

            // 1. Get top word.
            try ( PrintWriter out = new PrintWriter(args[1] + "_1.txt") ) {
                String topWord = getTopWord(1, fileText);
                out.println(topWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 1");
                System.exit(0);
            }

            // 2. Get 3rd most frequent word.
            try ( PrintWriter out = new PrintWriter(args[1] + "_2.txt") ) {
                String topWord = getTopWord(3, fileText);
                out.println(topWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 2");
                System.exit(0);
            }
            
            
            

            








            
            // Split file into Sentences
            String[] sentences = fileText.split("\\. ");
            // for (int i = 0; i < sentences.length; i++) {
            //     System.out.println(sentences[i]);
            //     System.out.printf("\n");
            // }
            

        } else {
            // Exit Program if minimum requirments are not met
            System.out.println("program requires two inputs:");
            System.out.println("\t1. Path to input .txt file");
            System.out.println("\t2. prefix to output files");
            System.exit(0);
        }

    } // END main

    // This will sanitize the input making '. ' the only delimeter
    private static String readFile(String filename) throws IOException {
        try {
            // "It is advisable to wrap a BufferedReader around any Reader whose
            // read() operations may be costly"
            BufferedReader in = new BufferedReader(new FileReader(filename));

            String fileText = "";
            String lineText = null;

            // Read one line at a time and add to fileText
            while (( lineText = in.readLine()) != null) {

                if (lineText.charAt(lineText.length() - 1) != '.') {
                    fileText = fileText + lineText + ". ";
                } else {
                    fileText = fileText + lineText + " ";
                }
                
            }

            // Finish by closing BufferedReader and return results
            in.close();

            return fileText;

        } catch (IOException ex) {
            System.out.println("File does not exist within given directory");
            return null;
        }
    }

    // Gets top word in position 1 through 3
    private static String getTopWord(int pos, String in) {
        // We'll find the top 3 
        int n = 3;
        String[] word = new String[n];
        int[] frequency = new int[n];

        in = in.replaceAll("\\.", "");

        String[] allWords = in.split(" ");
        String[] uniqueWords;
        int count = 0;

        uniqueWords = getUniqueWords(allWords);
        
        for (int j = 0; j < n; j++) {
            frequency[j] = 0;
        }

        for (String l: uniqueWords) {
            if ("".equals(l) || null == l) {
                break;
            }

            for (String s: allWords) {
                if (l.equals(s)) {
                    count++;
                }
            }

            // sub if the unique word 'l' has a higher 'count'
            for(int i=0; i<n; i++){
                if(count > frequency[i]){
                    frequency[i] = count;
                    word[i] = l;
                    break;
                }
            }
            count = 0;
        }


        return word[pos - 1] + ":" + frequency[pos -1];

    } 

   

    private static String[] getUniqueWords(String[] keys) {
        String[] uniqueKeys = new String[keys.length];
        
        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for (int i = 1; i < keys.length; i++) {
            for (int j = 0; j <= uniqueKeyIndex; j++) {
                if(keys[i].equals(uniqueKeys[j])) {
                    keyAlreadyExists = true;
                }
            }

            if(!keyAlreadyExists) {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;  
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }
}