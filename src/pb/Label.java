package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * <pre>
 * ( Label (Gosub|Goto|Statement|Subexpression|While|For)* EndLabel? )
 * </pre>
 */
public class Label
    extends Node
{
    public final static Pattern Expr = new jauk.Re("^<_>*[A-Za-z0-9_]+<_>*:");


    public Label(Reader reader)
        throws IOException, Syntax
    {
        super(reader);
        String input = reader.next(Expr);
        if (null != input){

            this.setText(input);

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
                                try {
                                    this.add(new Subexpression(reader));
                                }
                                catch (Jump j3){
                                    try {

                                        this.add(new While(reader));
                                    }
                                    catch (Jump j4){

                                        this.add(new For(reader));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndLabel(reader));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump(this.comment);
    }

}
