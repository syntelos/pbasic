package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * "If" (Subexpression) (Statement)* (EndIf) (Comment)?
 * </pre>
 */
public class If
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[iI][fF]<_>*");

    public If(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            final int first = scanner.currentLine();

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

            boolean inline = false;

            try {
                int second = 0;
                while (true){

                    this.add(new Statement(scanner,inline));

                    if (0 == second){
                        second = scanner.currentLine();
                        inline = (first == second);
                    }
                }
            }
            catch (Jump j){
            }

            if (inline){

                try {
                    this.add(new Comment(scanner));
                }
                catch (Jump j){
                }
            }
            else {
                try {
                    this.add(new Else(scanner));

                    try {
                        while (true){

                            this.add(new Statement(scanner));
                        }
                    }
                    catch (Jump j){
                    }
                }
                catch (Jump j1){
                }

                try {
                    this.add(new EndIf(scanner));
                }
                catch (Jump j){
                }

                try {
                    this.add(new Comment(scanner));
                }
                catch (Jump j){
                }
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
