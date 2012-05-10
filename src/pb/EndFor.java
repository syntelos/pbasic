package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class EndFor
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[nN][eE][xX][tT]<_>*");


    public EndFor(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Identifier(reader));
            }
            catch (Jump j){

                throw new Syntax(this,reader,"Missing identifier in for next");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
