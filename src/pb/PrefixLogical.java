package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Prefix logical operators
 */
public class PrefixLogical
    extends Prefix
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"!\"|[nN][oO][tT])<_>*");


    public PrefixLogical(Scanner scanner)
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
