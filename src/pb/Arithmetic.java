package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Arithmetic operators includes assignment
 */
public class Arithmetic
    extends Infix
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"+\"|\"-\"|\"*\"|\"**\"|\"*/\"|\"/\"|\"=\"|\"//\"|[hH][yY][pP]|[mM][iI][nN]|[mM][aA][xX]|[rR][eE][vV]|[dD][iI][gG]|\"<<\"|\">>\"|[aA][tT][nN]]|\"&\"|\"|\"|\"^\"|\"~\"|\"&/\"|\"|/\"|\"^/\")<_>*");


    public Arithmetic(Scanner scanner)
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
