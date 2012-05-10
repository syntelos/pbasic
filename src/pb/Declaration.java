package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 *
 */
public class Declaration
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*([cC][oO][nN]|[vV][aA][rR])<_>+");


    public Declaration(Reader reader)
        throws IOException, Syntax
    {
        super(reader);

        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);
            try {

                this.add(new Type(reader));
            }
            catch (Jump j0){
                try {

                    this.add(new Identifier(reader));
                }
                catch (Jump j1){

                    throw new Syntax(this,reader,"Missing type or identifier tail of declaration");
                }
            }
        }
        else
            throw new Jump(this.comment);

    }

}
