/*
https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html
 */
package com.mycompany.basicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author david
 */
public class CopyLines {

    public static void main(String[] args) throws IOException {

        BufferedReader in = null;
        PrintWriter out = null;        

        try {
            in = new BufferedReader(new FileReader("xanadu.txt"));
            out = new PrintWriter(new FileWriter("characteroutput.txt"));            
            String line;

            while ((line = in.readLine()) != null) {
                out.println(line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    
    /*
    Notice that both CopyBytes and CopyCharacters use an int variable to read 
    to and write from. However, in CopyCharacters, the int variable holds a 
    character value in its last 16 bits; in CopyBytes, the int variable holds 
    a byte value in its last 8 bits.
    */
}
