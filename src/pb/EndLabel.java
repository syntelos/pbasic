package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class EndLabel
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[rR][eE][tT][uU][rR][nN]<_>*");


    public EndLabel(Scanner scanner)
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
