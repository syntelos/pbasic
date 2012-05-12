package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * "ElseIf" (Subexpression) (Statement)* (EndIf) (Comment)?
 * </pre>
 */
public class Elseif
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[eE][lL][sS][eE][iI][fF]<_>*");

    public Elseif(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Subexpression(scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing range intializer subexpression following if");
            }

            try {
                this.add(new Then(scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing then following if");
            }

            try {
                while (true){

                    this.add(new Statement(scanner));
                }
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
