package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;
import jauk.Scanner;

/**
 * <pre>
 * ( ")" )
 * </pre>
 */
public class EndGroup
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\")\"<_>*");


    public EndGroup(Scanner scanner)
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
