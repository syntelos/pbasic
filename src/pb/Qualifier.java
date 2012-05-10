package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 * @see Type
 * @see Identifier
 */
public class Qualifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"[\"<_>*<Digit>+<_>*\"]\"|<Dot><_>*<Digit>+)<_>*");

    public Qualifier(Scanner scanner)
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
