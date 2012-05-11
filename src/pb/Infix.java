package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Infix operators include arithmetic and logic and iterator
 */
public class Infix
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"+\"|\"-\"|\"*\"|\"/\"|\"=\"|\"%\"|\"==\"|\"!=\"|\"<>\"|\"<\"|\">\"|\"<=\"|\">=\"|\"&&\"|\"||\"|\":\")<_>*");


    public Infix(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
