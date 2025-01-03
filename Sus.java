/*
Shortest Unique Substring (sus)

program to read in a single text file and return the
shortest substring in the text file that only contains
one instance of the string in the file

Authors- Evan Myran, Andrew Martens
Dependcies- algs4 library

Compilation- javac-algs4 Sus.java
Run command- java-algs4 Sus example.txt
*/


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Sus {
    public static void main(String[] args) {
        if(args.length != 1) {
            StdOut.println("Please provide a text input");
            return;
        }
        In in = new In(args[0]);
        String text = in.readAll().trim().replaceAll("\\s+", " ");
        StdOut.println("The shortest unique substring is: '" + sus(text) + "'");
    }


    // STEPS FOR SUS algorithm:
    // [1] Compare each suffix in suffix array with the previous and next suffix
    // [2] If the lengths are the same from step [1], a unique substring was found
    // [3] Compare length of current SUS word with new unique substring
    // [4] If new substring is shorter, update sus variable
    // [5] repeat until complete

    // This method returns the Shortest Unique Substring from the inputted text
    // @params text - the string this algorithm operates on
    // @returns String - Shortest Unique Substring
    private static String sus(String text) {
        // instance variables
        SuffixArray sa = new SuffixArray(text);
        int n = text.length();
        String sus = text;
        int length;
        // Iterates through each suffix
        for (int i = 1; i < n; i++) {
            // check if suffix is first, middle, or last
            // if the suffix is the first entry only check against the next suffix
            if (i == n - 1) {
                length = sa.lcp(i, true);
                // if the suffix is in the middle check against previous and next suffixes
            } else {
                length = sa.lcp(i, true);
                if(length != sa.lcp(i,false)) {
                    continue;
                }
            }

            // if length is shorter than current sus word & isn't 0
            // a shorter unique substring was found. Update variable
            if(length < sus.length() && length != 0) {
                sus = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        return sus;
    }
}
