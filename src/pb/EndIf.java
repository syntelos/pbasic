package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 */
public class EndIf
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[eE][nN][dD][iI][fF]<_>*");

    public EndIf(Scanner scanner)
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