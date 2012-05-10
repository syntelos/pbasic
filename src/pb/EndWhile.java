package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class EndWhile
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[eE][nN][dD][wW][hH][iI][lL][eE]<_>*");


    public EndWhile(Reader reader)
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
