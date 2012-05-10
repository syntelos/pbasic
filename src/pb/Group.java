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


    public Group(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Subexpression(reader));
            }
            catch (Jump j){

                throw new Syntax(this,reader,"Missing subexpression within group");
            }

            try {
                this.add(new EndGroup(reader));
            }
            catch (Jump j){

                throw new Syntax(this,reader,"Missing close paren end group");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
