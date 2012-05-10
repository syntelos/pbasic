package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Address
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(\"$\"<Hex>+|[bB][aA][nN][kK]<Digit>+)<_>*");


    public Address(Reader reader)
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
