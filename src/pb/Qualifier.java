package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * 
 * @see Type
 * @see Identifier
 */
public class Qualifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"[\"<_>*<Digit>+<_>*\"]\"|<Dot><_>*<Digit>+)<_>*");

    public Qualifier(Reader reader)
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
