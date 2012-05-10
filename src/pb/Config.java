package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Config
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\"@\"<Line>");



    public Config(Scanner scanner)
        throws IOException, Syntax
    {
        super();
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }

}
