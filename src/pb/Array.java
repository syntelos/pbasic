package pb;

import java.io.IOException;
import java.util.StringTokenizer;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Array list follows identifier as function call arguments.  A
 * array list contains {@link QuotedString}, {@link Identifier}, or
 * {@link Literal} -- separated by commas.
 * 
 * @see Subexpression
 */
public class Array
    extends Node
{
    public final static Pattern Open = new jauk.Re("<_>*\"[\"<_>*");
    public final static Pattern Sepa = new jauk.Re("<_>*\",\"<_>*");
    public final static Pattern Close = new jauk.Re("<_>*\"]\"<_>*");


    public Array(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Open);
        if (null != input){

            do {
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

                            break;
                        }
                    }
                }
            }
            while (null != scanner.next(Sepa));

            if (null == scanner.next(Close))
                throw new Syntax(this,scanner,"Missing array close");
        }
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Open.toString());
        java.lang.System.out.println(Sepa.toString());
        java.lang.System.out.println(Close.toString());
    }
}
