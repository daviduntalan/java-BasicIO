/*
https://docs.oracle.com/javase/tutorial/essential/io/scanning.html
 */
package com.mycompany.basicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class ScanXan {

    public static void main(String[] args) throws IOException {

        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

            while (s.hasNext()) {
                System.out.printf("[%s]\n", s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
    
    /* 
    To use a different token separator, invoke useDelimiter(), specifying a 
    regular expression. For example, suppose you wanted the token separator 
    to be a comma, optionally followed by white space. You would invoke,

        s.useDelimiter(",\\s*");
    
    */
}
