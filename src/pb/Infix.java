package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Infix operator
 * 
 * <pre>
 * </pre>
 */
public class Infix
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"+\"|\"-\"|\"*\"|\"/\"|\"=\"|\"%\"|\"==\"|\"!=\"|\"<>\"|\"<\"|\">\"|\"<=\"|\">=\"|\"&&\"|\"||\")<_>*");


    public Infix(Reader reader)
        throws IOException, Syntax
    {
        super(reader);

        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else
            throw new Jump(this.comment);
    }

}
