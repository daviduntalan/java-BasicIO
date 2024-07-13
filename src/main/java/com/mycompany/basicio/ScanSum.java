/* Translating Individual Tokens
https://docs.oracle.com/javase/tutorial/essential/io/scanning.html
The ScanXan example treats all input tokens as simple String values. Scanner 
also supports tokens for all of the Java language's primitive types (except for
char), as well as BigInteger and BigDecimal. Also, numeric values can use 
thousands separators. Thus, in a US locale, Scanner correctly reads the string
"32,767" as representing an integer value.

We have to mention the locale, because thousands separators and decimal symbols
are locale specific. So, the following example would not work correctly in all
locales if we didn't specify that the scanner should use the US locale. That's
not something you usually have to worry about, because your input data usually 
comes from sources that use the same locale as you do. But this example is part
of the Java Tutorial and gets distributed all over the world.

The ScanSum example reads a list of double values and adds them up. 
Here's the source:
 */
package com.mycompany.basicio;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class ScanSum {

    public static void main(String[] args) throws IOException {

        Scanner s = null;
        double sum = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    sum += s.nextDouble();
                } else {
                    s.next();
                }
            }
        } finally {
            s.close();
        }

        System.out.println(sum);
    }
}
/* 
And here's the sample input file, usnumbers.txt

8.5
32,767
3.14159
1,000,000.1

The output string is "1032778.74159". The period will be a different character
in some locales, because System.out is a PrintStream object, and that class 
doesn't provide a way to override the default locale. We could override the 
locale for the whole program — or we could just use formatting, as described 
in the next topic, Formatting.
*/
