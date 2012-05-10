package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( "For" ( Group or Subexpression ) ( Gosub or Goto or Statement or Subexpression )* EndFor )
 * </pre>
 */
public class For
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[fF][oO][rR]<_>*");

    public For(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Group(scanner));
            }
            catch (Jump j0){
                try {
                    this.add(new Subexpression(scanner));
                }
                catch (Jump j1){

                    throw new Syntax(this,scanner,"Missing group or subexpression following for");
                }
            }
            try {
                while (true){

                    this.add(new Statement(scanner));
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndFor(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing end for: next");
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
