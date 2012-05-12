package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 */
public class Then
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[tT][hH][eE][nN]<_>*");

    public Then(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

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
