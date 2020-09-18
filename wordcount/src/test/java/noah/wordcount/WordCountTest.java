package noah.wordcount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

class WordCountTest {
    

    // Test the given example test
    @Test
    void testExample() throws FileNotFoundException {
        String out;
        out = WordCount.countWordsFromFile("test_files/example.txt");
        assertEquals(out, "Word count = 9\nAverage word length = 4.556\nNumber of words of length 1 is 1\nNumber of words of length 2 is 1\nNumber of words of length 3 is 1\nNumber of words of length 4 is 2\nNumber of words of length 5 is 2\nNumber of words of length 7 is 1\nNumber of words of length 10 is 1\nThe most frequently occurring word length is 2, for word lengths of 4 & 5\n");
    }

    // Test an empty file
    @Test
    void testEmptyFile() throws FileNotFoundException {
        String out;
        out = WordCount.countWordsFromFile("test_files/empty.txt");
        assertEquals(out, "Word count = 0\nAverage word length = 0.000\n");
    }

    // Test complex punctuation
    @Test
    void testPunctuation() throws FileNotFoundException {
        String out;
        out = WordCount.countWordsFromFile("test_files/punctuation.txt");
        assertEquals(out, "Word count = 13\nAverage word length = 5.000\nNumber of words of length 5 is 13\nThe most frequently occurring word length is 13, for word lengths of 5\n");
    }

    // Test numbers
    @Test
    void testNumbers() throws FileNotFoundException {
        String out;
        out = WordCount.countWordsFromFile("test_files/numbers.txt");
        assertEquals(out, "Word count = 5\nAverage word length = 8.800\nNumber of words of length 4 is 1\nNumber of words of length 20 is 1\nNumber of words of length 5 is 1\nNumber of words of length 6 is 1\nNumber of words of length 9 is 1\nThe most frequently occurring word length is 1, for word lengths of 4 & 20 & 5 & 6 & 9\n");
    }

    // Test numbers with punctuation
    @Test
    void testNumbersWithPunctuation() throws FileNotFoundException {
        String out;
        out = WordCount.countWordsFromFile("test_files/numbers_2.txt");
        assertEquals(out, "Word count = 4\nAverage word length = 4.000\nNumber of words of length 4 is 4\nThe most frequently occurring word length is 4, for word lengths of 4\n");
    }

}
