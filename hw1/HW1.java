
import java.io.FileReader;
import java.io.PrintWriter;

import java.io.IOException;

////////////////////////////////////////////////////////////////////////////////

/* HW1 - completed ab inito, 
 *  using only the following librarys & structs:
 *  - String operations ex. split()
 *  - Standard file IO facilites for reading/writing 
 * 
 *  Requirements:
 * 
 *  1. List the most frequent word(s) in the whole file and its frequency.
 * 
 *  2. List the 3rd most frequent word(s) in the whole file and its frequency.
 * 
 * * *
 * 
 *  3. List the word(s) with the highest frequency in a sentence across all 
 *      sentences in the whole file, also print its frequency and the 
 *      corresponding sentence.
 * 
 * * *
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
 * * *
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
            try (PrintWriter out = new PrintWriter(args[1] + "1.txt")) {
                String topWord = getTopWord(1, fileText);
                out.println(topWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 1");
                System.exit(0);
            }

            // 2. Get 3rd most frequent word.
            try (PrintWriter out = new PrintWriter(args[1] + "2.txt")) {
                String topWord = getTopWord(3, fileText);
                out.println(topWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 2");
                System.exit(0);
            }

            // 3. Get most frequent words in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "3.txt")) {
                String topSentence = getTopSentences(fileText);
                out.println(topSentence);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 3 ");
                System.exit(0);
            }

            // 4. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "4.txt")) {
                String topSentenceWord = getSentenceWord("the", fileText);
                out.println(topSentenceWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 4 ");
                System.exit(0);
            }

            // 5. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "5.txt")) {
                String topSentenceWord = getSentenceWord("of", fileText);
                out.println(topSentenceWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 5 ");
                System.exit(0);
            }

            // 6. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "6.txt")) {
                String topSentenceWord = getSentenceWord("was", fileText);
                out.println(topSentenceWord);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 6 ");
                System.exit(0);
            }

            // 7. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "7.txt")) {
                String topSentencePhrase = getSentencePhrase("but the", fileText);
                out.println(topSentencePhrase);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 7 ");
                System.exit(0);
            }

            // 8. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "8.txt")) {
                String topSentencePhrase = getSentencePhrase("it was", fileText);
                out.println(topSentencePhrase);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 8 ");
                System.exit(0);
            }

            // 9. Get most frequent word in all sentences.
            try (PrintWriter out = new PrintWriter(args[1] + "9.txt")) {
                String topSentencePhrase = getSentencePhrase("in my", fileText);
                out.println(topSentencePhrase);
            } catch (IOException e) {
                System.out.println("Failed to output Solution 9 ");
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

    // This will sanitize the input making '. ' the only delimeter
    private static String readFile(String filename) throws IOException {
        try {
            // "It is advisable to wrap a BufferedReader around any Reader whose
            // read() operations may be costly"
            BufferedReader in = new BufferedReader(new FileReader(filename));

            String fileText = "";
            String lineText = null;

            // Read one line at a time and add to fileText
            while ((lineText = in.readLine()) != null) {

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
        in = in.replaceAll("\\.", "");

        String[] allWords = in.split(" ");
        String[] uniqueWords;

        uniqueWords = getUniqueWords(allWords);

        // I'll find the top 5
        int total = uniqueWords.length;
        int[] frequency = new int[total];
        String[] word = new String[total];
        int count = 0;

        for (int j = 0; j < total; j++) {
            frequency[j] = 0;
        }

        for (String l : uniqueWords) {
            if ("".equals(l) || null == l) {
                break;
            }

            for (String s : allWords) {
                if (l.equals(s)) {
                    count++;
                }
            }

            // sub if the unique word 'l' has a higher 'count'
            for (int i = 0; i < total; i++) {

                if (count > frequency[i]) {

                    // Shift over array values so that we don't lose any.
                    for (int j = total - 1; j > i; j--) {
                        frequency[j] = frequency[j - 1];
                        word[j] = word[j - 1];
                    }

                    // Insert the current value where room was made
                    frequency[i] = count;
                    word[i] = l;
                    break;
                }
            }

            count = 0;
        }

        // Only going to return the most frequent one.
        String result = "";
        int collect = pos;

        for (int i = 0; i < total && collect != 0; i++) {

            if (i > 0 && frequency[i] != frequency[i - 1]) {
                collect--;

                if (collect > 0) {
                    result = "";
                    result += word[i] + ":" + frequency[i] + "\n";
                }
            } else {
                result += word[i] + ":" + frequency[i] + "\n";
            }
        }

        return result.trim();
    }

    private static String[] getUniqueWords(String[] keys) {
        String[] uniqueKeys = new String[keys.length];

        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for (int i = 1; i < keys.length; i++) {
            for (int j = 0; j <= uniqueKeyIndex; j++) {
                if (keys[i].equals(uniqueKeys[j])) {
                    keyAlreadyExists = true;
                }
            }

            if (!keyAlreadyExists) {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }

    private static String getTopSentences(String in) {

        String[] allSentences = in.split("\\. ");

        int total = allSentences.length;
        int[] frequency = new int[total];
        String[] wordFreq = new String[total];
        String[] sentence = new String[total];

        for (int j = 0; j < total; j++) {
            frequency[j] = 0;
        }

        for (String s : allSentences) {
            if ("".equals(s) || null == s) {
                break;
            }

            // get the top word and frequency for each one
            String words = getTopWord(1, s);
            String line[] = words.split("\\r?\\n");

            for (String l : line) {

                // Grab int value
                int f = l.charAt(l.length() - 1) - '0';

                // Do the comparison for top selection array
                for (int i = 0; i < total; i++) {
                    if (f > frequency[i]) {
                        // Shift over array values so that we don't lose any.
                        for (int j = total - 1; j > i; j--) {
                            frequency[j] = frequency[j - 1];
                            wordFreq[j] = wordFreq[j - 1];
                            sentence[j] = sentence[j - 1];
                        }

                        // Insert the current value where room was made
                        frequency[i] = f;
                        wordFreq[i] = l;
                        sentence[i] = s;
                        break;
                    }
                }

            }

        }

        // Only going to return the most frequent one.
        String result = "";
        int collect = 1;

        for (int i = 0; i < total && collect != 0; i++) {

            if (i > 0 && frequency[i] != frequency[i - 1]) {
                collect--;

                if (collect > 0) {
                    result = "";
                    result += wordFreq[i] + ":" + sentence[i] + "\n";
                }
            } else {
                result += wordFreq[i] + ":" + sentence[i] + "\n";
            }
        }

        return result.trim();
    }

    private static String getSentenceWord(String word, String in) {
        // I'll iterate through all sentences
        String[] allSentences = in.split("\\. ");

        int total = allSentences.length;
        int[] frequency = new int[total];
        String[] sentences = new String[total];

        for (int j = 0; j < total; j++) {
            frequency[j] = 0;
        }

        for (String s : allSentences) {
            String[] sWords = s.split(" ");
            int count = 0;

            for (String w : sWords) {
                if ("".equals(w) || null == w) {
                    break;
                }

                if (w.equals(word)) {
                    count++;
                }
            }

            // Sub current sentence if it has a greater count
            for (int i = 0; i < total; i++) {
                if (count > frequency[i]) {

                    for (int j = total - 1; j > i; j--) {
                        frequency[j] = frequency[j - 1];
                        sentences[j] = sentences[j - 1];
                    }

                    frequency[i] = count;
                    sentences[i] = s;
                    break;
                }
            }
        }

        String result = "";
        int collect = 1;

        for (int i = 0; i < total && collect != 0; i++) {

            if (i > 0 && frequency[i] != frequency[i - 1]) {
                collect--;

                if (collect > 0) {
                    result = "";
                    result += word + ":" + frequency[i] + ":" + sentences[i] + "\n";
                }
            } else {
                result += word + ":" + frequency[i] + ":" + sentences[i] + "\n";
            }
        }

        return result.trim();
    }

    private static String getSentencePhrase(String phrase, String in) {
        // I'll iterate through all sentences
        String[] allSentences = in.split("\\. ");

        int total = allSentences.length;
        int[] frequency = new int[total];
        String[] sentences = new String[total];


        for (int j = 0; j < total; j++) {
            frequency[j] = 0;
        }

        for (String sentence : allSentences) {
            String s = sentence;
            String p = phrase;
            int count = (s.length() - s.replace(p, "").length()) / p.length(); 
            

            // Sub current sentence if it has a greater count
            for (int i = 0; i < total; i++) {
                if (count >= frequency[i]) {

                    // Make room for current selection
                    for (int j = total - 1; j > i; j--) {
                        frequency[j] = frequency[j - 1];
                        sentences[j] = sentences[j - 1];
                    }

                    frequency[i] = count;
                    sentences[i] = sentence;
                    break;
                }
            }
        }

        String result = "";
        int collect = 1;

        for (int i = 0; i < total && collect != 0; i++) {

            if (i > 0 && frequency[i] != frequency[i - 1]) {
                collect--;

                if (collect > 0) {
                    result = "";
                    result += phrase + ":" + frequency[i] + ":" + sentences[i] + "\n";
                }
            } else {
                result += phrase + ":" + frequency[i] + ":" + sentences[i] + "\n";
            }
        }

        return result.trim();
    }
}