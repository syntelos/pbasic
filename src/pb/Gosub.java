package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * ("gosub" Identifier)
 * </pre>
 */
public class Gosub
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*gosub<_>*");


    public Gosub(Reader reader)
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

                throw new Syntax(this,reader,"Missing identifier following gosub");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
