package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( "(" Subexpression (EndGroup=")"))
 * </pre>
 */
public class Group
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\"(\"<_>*");


    public Group(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Subexpression(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing subexpression within group");
            }

            try {
                this.add(new EndGroup(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing close paren end group");
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
