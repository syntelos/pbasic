package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Logical operators
 */
public class Logical
    extends Infix
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"&&\"|\"||\"|\"^^\"|[aA][nN][dD]|[oO][rR]|[xX][oO][rR]|[aA][nN][dD][nN][oO][tT]|[oO][rR][nN][oO][tT]|[xX][oO][rR][nN][oO][tT])<_>*");


    public Logical(Scanner scanner)
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
