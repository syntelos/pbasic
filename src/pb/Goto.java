package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ("goto" Identifier)
 * </pre>
 */
public class Goto
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[gG][oO][tT][oO]<_>*");

    public Goto(Scanner scanner)
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

                throw new Syntax(this,scanner,"Missing identifier following goto");
            }

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
