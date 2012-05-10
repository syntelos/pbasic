package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Config
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\"@\"<Line>");



    public Config(Reader reader)
        throws IOException, Syntax
    {
        super();
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else
            throw new Jump(this.comment);
    }

}
