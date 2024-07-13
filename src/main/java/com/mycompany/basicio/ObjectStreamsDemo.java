/*
Object Streams
https://docs.oracle.com/javase/tutorial/essential/io/objectstreams.html
Just as data streams support I/O of primitive data types, object streams support 
I/O of objects. Most, but not all, standard classes support serialization of 
their objects. Those that do implement the marker interface Serializable.

The object stream classes are ObjectInputStream and ObjectOutputStream. 
These classes implement ObjectInput and ObjectOutput, which are subinterfaces 
of DataInput and DataOutput. That means that all the primitive data I/O methods 
covered in Data Streams are also implemented in object streams. So an object 
stream can contain a mixture of primitive and object values. The ObjectStreams 
example illustrates this. ObjectStreams creates the same application as DataStreams, 
with a couple of changes. First, prices are now BigDecimalobjects, to better 
represent fractional values. Second, a Calendar object is written to the data file, 
indicating an invoice date.

If readObject() doesn't return the object type expected, attempting to cast it 
to the correct type may throw a ClassNotFoundException. In this simple example, 
that can't happen, so we don't try to catch the exception. Instead, we notify 
the compiler that we're aware of the issue by adding ClassNotFoundException to 
the main method's throws clause.

Output and Input of Complex Objects

The writeObject and readObject methods are simple to use, but they contain some 
very sophisticated object management logic. This isn't important for a class 
like Calendar, which just encapsulates primitive values. But many objects contain 
references to other objects. If readObject is to reconstitute an object from a 
stream, it has to be able to reconstitute all of the objects the original object 
referred to. These additional objects might have their own references, and so on. 
In this situation, writeObject traverses the entire web of object references and 
writes all objects in that web onto the stream. Thus a single invocation of 
writeObject can cause a large number of objects to be written to the stream.

This is demonstrated in the following figure, where writeObject is invoked to 
write a single object named a. This object contains references to objects b and c, 
while b contains references to d and e. Invoking writeobject(a) writes not just a,
but all the objects necessary to reconstitute a, so the other four objects in this 
web are written also. When a is read back by readObject, the other four objects are 
read back as well, and all the original object references are preserved.

                           Stream
writeObject(a) -->[ ][ ][c][e][d][b][a][ ][ ]--> readObject()

        +--[a]--+                               +--[a]--+
        |       |                               |       |
    +--[b]--+  [c]                          +--[b]--+  [c]
    |       |                               |       |
   [d]     [e]                             [d]     [e]

                I/O of multiple referred-to objects

You might wonder what happens if two objects on the same stream both contain 
references to a single object. Will they both refer to a single object when 
they're read back? The answer is "yes." A stream can only contain one copy of 
an object, though it can contain any number of references to it. Thus if you 
explicitly write an object to a stream twice, you're really writing only the 
reference twice. For example, if the following code writes an object ob twice 
to a stream:

    Object ob = new Object();
    out.writeObject(ob);
    out.writeObject(ob);

Each writeObject has to be matched by a readObject, so the code that reads the 
stream back will look something like this:

    Object ob1 = in.readObject();
    Object ob2 = in.readObject();

This results in two variables, ob1 and ob2, that are references to a single object.

However, if a single object is written to two different streams, it is 
effectively duplicated — a single program reading both streams back will see 
two distinct objects.
 */
package com.mycompany.basicio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author david
 */
public class ObjectStreamsDemo {

    static final String dataFile = "invoicedata";

    static final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
    static final int[] units = {12, 8, 13, 29, 50};
    static final String[] descs = {
        "Java T-shirt",
        "Java Mug",
        "Duke Juggling Dolls",
        "Java Pin",
        "Java Key Chain"
    };

    public static void main(String[] args) throws FileNotFoundException, IOException {

        var out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(dataFile)));

        for (int i = 0; i < prices.length; ++i) {
            out.writeDouble(prices[i]);
            out.writeInt(units[i]);
            out.writeUTF(descs[i]);
        }
        out.close();

        ///////////////////////////////////////////////////////////////////////
        var in = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(dataFile)));

        double price;
        int unit;
        String desc;
        double total = 0.0;

        try {
            // read each record in stream
            while (true) {
                price = in.readDouble();
                unit = in.readInt();
                desc = in.readUTF();
                System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                        unit, desc, price);
                total += unit * price;
            }
        } catch (EOFException e) {
        }
        in.close();
    }

}

/*
import java.math.BigDecimal;

public class BigDecimalCalculations {
    public static void main(String[] args) {
        // Initialize an array of BigDecimal with 10 values
        BigDecimal[] bigDecimals = new BigDecimal[10];

        // Assign values to the BigDecimal array
        bigDecimals[0] = new BigDecimal("0.01");
        bigDecimals[1] = new BigDecimal("1.23");
        bigDecimals[2] = new BigDecimal("2.34");
        bigDecimals[3] = new BigDecimal("3.45");
        bigDecimals[4] = new BigDecimal("4.56");
        bigDecimals[5] = new BigDecimal("5.67");
        bigDecimals[6] = new BigDecimal("6.78");
        bigDecimals[7] = new BigDecimal("7.89");
        bigDecimals[8] = new BigDecimal("8.90");
        bigDecimals[9] = new BigDecimal("9.01");

        // Perform some calculations
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal product = BigDecimal.ONE;

        for (BigDecimal bd : bigDecimals) {
            sum = sum.add(bd);       // Adding each BigDecimal value to sum
            product = product.multiply(bd); // Multiplying each BigDecimal value to product
        }

        // Print the results
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);

        // Example of subtraction and division with first two values
        BigDecimal difference = bigDecimals[1].subtract(bigDecimals[0]);
        BigDecimal quotient = bigDecimals[1].divide(bigDecimals[0], BigDecimal.ROUND_HALF_UP); // Specifying rounding mode

        // Print the subtraction and division results
        System.out.println("Difference: " + difference);
        System.out.println("Quotient: " + quotient);
    }
}
*/
