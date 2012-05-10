package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class System
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[sS][yY][sS][tT][eE][mM]<_>*");


    public System(Scanner scanner)
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
