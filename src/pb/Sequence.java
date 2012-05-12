package pb;

import java.io.IOException;
import java.util.StringTokenizer;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Sequence list follows identifier as function call arguments.  A
 * sequence list contains {@link QuotedString}, {@link Identifier}, or
 * {@link Literal} -- separated by commas.
 * 
 * @see Subexpression
 */
public class Sequence
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\",\"<_>*");


    public Sequence(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        while (true){
            try {
                this.add(new QuotedString(scanner));
            }
            catch (Jump j0){
                try {
                    this.add(new Identifier(scanner));
                }
                catch (Jump j1){

                    try {
                        this.add(new Literal(scanner));
                    }
                    catch (Jump j2){

                        try {
                            this.add(new Array(scanner));
                        }
                        catch (Jump j3){

                            break;
                        }
                    }
                }
            }
            String input = scanner.next(Expr);
            if (null != input)
                continue;
            else
                break;
        }

        if (this.isEmpty()){

            throw new Jump();
        }
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
