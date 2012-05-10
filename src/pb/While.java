package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * "While" ( Subexpression ) ( Statement )* (EndWhile) (Comment)?
 * </pre>
 */
public class While
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[wW][hH][iI][lL][eE]<_>*");


    public While(Scanner scanner)
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

                throw new Syntax(this,scanner,"Missing group or subexpression following while");
            }

            try {
                while (true){

                    this.add(new Statement(scanner));
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndWhile(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing end while");
            }

            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }

}
