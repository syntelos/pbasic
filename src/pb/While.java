package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * ( "While" ( Group or Subexpression ) ( Gosub or Goto or Statement or Subexpression )* EndWhile )
 * </pre>
 */
public class While
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[wW][hH][iI][lL][eE]<_>*");


    public While(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Group(reader));
            }
            catch (Jump j0){
                try {
                    this.add(new Subexpression(reader));
                }
                catch (Jump j1){

                    throw new Syntax(this,reader,"Missing group or subexpression following while");
                }
            }
            try {
                while (true){
                    try {
                        this.add(new Gosub(reader));
                    }
                    catch (Jump j0){
                        try {
                            this.add(new Goto(reader));
                        }
                        catch (Jump j1){
                            try {
                                this.add(new Statement(reader));
                            }
                            catch (Jump j2){

                                this.add(new Subexpression(reader));
                            }
                        }
                    }
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndWhile(reader));
            }
            catch (Jump j){

                throw new Syntax(this,reader,"Missing end while");
            }
        }
        else
            throw new Jump(this.comment);
    }

}
