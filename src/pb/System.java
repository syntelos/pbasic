package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class System
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[sS][yY][sS][tT][eE][mM]<_>*");


    public System(Reader reader)
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
