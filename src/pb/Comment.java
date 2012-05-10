package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Comment
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*'<Line>");



    public Comment(Scanner scanner)
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
