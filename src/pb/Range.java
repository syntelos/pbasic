package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * {@link For} range iterator
 */
public class Range
    extends Infix
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"[tT][oO]\")<_>*");


    public Range(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
            try {
                this.add(new Literal(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing for range end literal");
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
