/*
https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html
 */
package com.mycompany.basicio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author david
 */
public class CopyBytes {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
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
