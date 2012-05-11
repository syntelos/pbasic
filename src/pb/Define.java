package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Define
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[dD][eE][fF][iI][nN][eE]<_>+(<Alpha>|\"_\")+<_>+(<AlphaNumDot>|\"_\")+<_>*");


    public Define(Scanner scanner)
        throws IOException, Syntax
    {
        super();
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
