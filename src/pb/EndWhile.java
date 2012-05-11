package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class EndWhile
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[eE][nN][dD][wW][hH][iI][lL][eE]<_>*");


    public EndWhile(Scanner scanner)
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
