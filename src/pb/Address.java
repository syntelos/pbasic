package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Address
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"$\"<Hex>+|[bB][aA][nN][kK]<Digit>+)<_>*");


    public Address(Scanner scanner)
        throws IOException, Syntax
    {
        super();
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else
            throw new Jump();
    }

}
