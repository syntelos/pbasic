package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * ("goto" Identifier)
 * </pre>
 */
public class Goto
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*goto<_>*");

    public Goto(Reader reader)
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
                throw new Syntax(this,reader,"Missing identifier following goto");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
