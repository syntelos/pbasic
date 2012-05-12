package pb;

import java.io.IOException;
import java.util.StringTokenizer;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 */
public class QuotedString
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*<DoubleQuoted><_>*");



    public QuotedString(Scanner scanner)
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
