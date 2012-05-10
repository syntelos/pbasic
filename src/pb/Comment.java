package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Comment
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*'.*<Newline>");



    public Comment(Reader reader)
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
