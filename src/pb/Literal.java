package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Literal
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"$\"<Hex>+|<Digit>+(<Dot><Digit>+)*|\"%\"[01]+)<_>*");

    public Literal(Scanner scanner)
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

}
