package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * (":" (Include|Gosub|Goto|Subexpression|While|For) )
 * </pre>
 */
public class Statement
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*:<_>*");

    public Statement(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Include(reader));
            }
            catch (Jump j0){
                try {
                    this.add(new Gosub(reader));
                }
                catch (Jump j1){
                    try {
                        this.add(new Goto(reader));
                    }
                    catch (Jump j2){
                        try {
                            this.add(new Subexpression(reader));
                        }
                        catch (Jump j3){
                            try {
                                this.add(new While(reader));
                            }
                            catch (Jump j4){
                                try {
                                    this.add(new For(reader));
                                }
                                catch (Jump j5){

                                    throw new Syntax(this,reader,"Missing statement following colon");
                                }
                            }
                        }
                    }
                }
            }
        }
        else
            throw new Jump(this.comment);
    }

}
