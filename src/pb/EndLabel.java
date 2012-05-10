package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class EndLabel
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[rR][eE][tT][uU][rR][nN]<_>*");


    public EndLabel(Reader reader)
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
