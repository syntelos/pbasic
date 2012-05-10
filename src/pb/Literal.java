package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Literal
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"$\"<Hex>+|<Digit>+<Dot>?<Digit>*)<_>*");

    public Literal(Reader reader)
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
