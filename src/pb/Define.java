package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Define
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[dD][eE][fF][iI][nN][eE]<_>+(<Alpha>|\"_\")+<_>+(<AlphaNumDot>|\"_\")+<_>*");


    public Define(Reader reader)
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
