package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( ")" )
 * </pre>
 */
public class EndGroup
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\")\"<_>*");


    public EndGroup(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){
            this.setText(input);
        }
        else
            throw new Jump(this.comment);
    }

}
