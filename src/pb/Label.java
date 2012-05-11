package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( Label (Statement)* EndLabel? )
 * </pre>
 */
public class Label
    extends Node
{
    public final static Pattern Expr = new jauk.Re("^<_>*[A-Za-z0-9_]+<_>*:");


    public Label(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                while (true){

                    this.add(new Statement(scanner));
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndLabel(scanner));
            }
            catch (Jump j){
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


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
