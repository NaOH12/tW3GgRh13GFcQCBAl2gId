package noah.wordcount;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

    public static void main(String[] args) {

        // Has an argument been passed?
        if (args.length == 0) {
            // If no argument has been passed
            System.out.println("Please enter a filename.");
        } else {
            // Otherwise get the filename and count the words
            String fileName = args[0];

            // Try reading the file and counting words
            try {
                String out = countWordsFromFile(fileName);
                System.out.println(out);
            } catch (FileNotFoundException ex) {
                // If file not found then report error
                System.out.println("Could not find filename: " + fileName);
            }
        }
    }

    static String countWordsFromFile(String fileName) throws FileNotFoundException {

        // Open file
        File file = new File(fileName);
        Scanner scanIn = new Scanner(file);

        // Create hash map to store totals of word lengths
        HashMap<Integer, Integer> wordLengthCount = new HashMap<>();

        // Split the text by the following pattern
        //     Zero or more of the following characters: \s \n . , ! ? ; : < > { } ( ) [ ] " '
        //     Followed atleast one of the following characters:
        //          \s \n <> { } ( ) [ ] " '
        scanIn.useDelimiter("[\\s\\n.,!?;:<>\\{\\}\\(\\)\\[\\]\"\']*[\\s\\n<>\\{\\}\\(\\)\\[\\]\"\']+");

        // While there is another word in the file
        while (scanIn.hasNext()) {
            // Get the next word
            String word = scanIn.next();
            // Get the existing number of words of that word length
            Integer mapVal = wordLengthCount.get(word.length());
            // Increment the value in the hashmap
            wordLengthCount.put(word.length(), mapVal == null ? 1 : mapVal + 1);
        }

        // Close the scanner
        scanIn.close();

        // Count the number of words in the file
        int wordCount = 0;
        // Stores the average word length (this will initially hold the total length)
        double averageWordLength = 0.0f;
        // Most frequent lengths
        ArrayList<Integer> lengths = new ArrayList<>();
        int maxFrequency = 0;
        // Used to build the returned string
        String out = "";

        // Iterate over the values in the hashmap
        Iterator hashIterator = wordLengthCount.entrySet().iterator();
        // For each word length (key) and its number of words (value)
        while (hashIterator.hasNext()) {
            // Get the hash pair
            Map.Entry<Integer, Integer> pair = (Map.Entry) hashIterator.next();

            // Empty lengths are possible e.g. in the case of word? 'word'
            // Avoid the key of 0
            // Build the message
            out = out + "Number of words of length " + pair.getKey() + " is " + pair.getValue() + "\n";
            // Add the pair values to the word count
            wordCount+= pair.getValue();
            // Add the (word length * number of words of that length) to the total
            averageWordLength+= pair.getValue() * pair.getKey();
            
            // If the length frequency is greater than the maximum recorded frequency,
            // then change the max frequency, reinitalise lengths with the new length
            if (pair.getValue() > maxFrequency) {
                maxFrequency = pair.getValue();
                lengths.clear();
                lengths.add(pair.getKey());
            } else if (pair.getValue() == maxFrequency) {
                // Otherwise, if a length with the same frequency has been found,
                // then add the length to the lengths array.
                lengths.add(pair.getKey());
            }
        }

        // If the word count is empty
        if (wordCount == 0) {
            out = "Word count = 0\nAverage word length = 0.000\n";
        } else {
            // If the word count isnt empty then create report

            // Finally divide the total by the word count to get an average
            averageWordLength/= wordCount;

            // Add extra statistics to the message
            out = "Average word length = " + (new DecimalFormat("0.000")).format(averageWordLength) + "\n" + out;
            out = "Word count = " + wordCount + "\n" + out;
            out+= "The most frequently occurring word length is " + maxFrequency + ", for word lengths of ";

            // For each word length with the same max frequency
            for (int i = 0; i < lengths.size() - 1; i++) {
                // Append to the return message
                out+= lengths.get(i) + " & ";
            }
            // Append last length (without the &)
            out+= lengths.get(lengths.size()-1) + "\n";
        }

        // Return the message
        return out;

    }

}