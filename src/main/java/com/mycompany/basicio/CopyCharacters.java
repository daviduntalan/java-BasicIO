/*
https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html
https://docs.oracle.com/javase/tutorial/essential/io/buffers.html
 */
package com.mycompany.basicio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author david
 */
public class CopyCharacters {

    public static void main(String[] args) throws IOException {

        FileReader in = null;
        FileWriter out = null;        

        try {            
            in = new FileReader("xanadu.txt");
            out = new FileWriter("characteroutput.txt"); 
            /* 
            can convert an unbuffered stream into a buffered stream using the 
            wrapping idiom we've used several times now, where the unbuffered 
            stream object is passed to the constructor for a buffered stream class.
            
            in = new BufferedReader(new FileReader("xanadu.txt"));
            out = new BufferedWriter(new FileWriter("characteroutput.txt")); 
            */
            int c;                       

            /* reads a byte of data from this input stream. 
            This method blocks if no input is yet available. */
            while ((c = in.read()) != -1) {
                out.write(c);
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
