package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ("gosub" Identifier)
 * </pre>
 */
public class Gosub
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[gG][oO][sS][uU][bB]<_>*");


    public Gosub(Scanner scanner)
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

                throw new Syntax(this,scanner,"Missing identifier following gosub");
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


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
