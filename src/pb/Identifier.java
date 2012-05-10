package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Identifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(<Alpha>|<Digit>|_)+<_>*");

    public Identifier(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Qualifier(reader));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump(this.comment);
    }

}
