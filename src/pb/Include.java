package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * ("include" Identifier)
 * </pre>
 */
public class Include
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[iI][nN][cC][lL][uU][dD][eE]<_>*\"\"\"(<Alpha>|<Digit>|_|<Dot>)+\"\"\"<_>*");

    public Include(Reader reader)
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
                throw new Syntax(this,reader,"Missing identifier following include");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
