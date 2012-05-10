package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Type
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*([bB][yY][tT][eE]|[wW][oO][rR][dD]|[bB][iI][tT])<_>*");


    public Type(Reader reader)
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
