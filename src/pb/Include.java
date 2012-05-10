package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ("include" Identifier)
 * </pre>
 */
public class Include
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[iI][nN][cC][lL][uU][dD][eE]<_>*\"\"\"(<Alpha>|<Digit>|_|<Dot>)+\"\"\"<_>*");

    public Include(Scanner scanner)
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
                throw new Syntax(this,scanner,"Missing identifier following include");
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
