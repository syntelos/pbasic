package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Prefix arithmetic operators 
 */
public class PrefixArithmetic
    extends Prefix
{
    public final static Pattern Expr = new jauk.Re("<_>*([aA][bB][sS]|[sS][qQ][rR]|[sS][iI][nN]|[dD][iI][vV]\"32\"|\"~\")<_>*");


    public PrefixArithmetic(Scanner scanner)
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
