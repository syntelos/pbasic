package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Identifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(<Alpha>|<Digit>|_)+<_>*");

    public Identifier(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Qualifier(scanner));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }

}
