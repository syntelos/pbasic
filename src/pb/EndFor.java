package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class EndFor
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[nN][eE][xX][tT]<_>*");


    public EndFor(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Identifier(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing identifier in for next");
            }
        }
        else
            throw new Jump();
    }

}
