package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( (":")? (Include|Gosub|Goto|Subexpression|While|For) )
 * </pre>
 */
public class Statement
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*:<_>*");

    public Statement(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
        }

        try {
            this.add(new Include(scanner));
        }
        catch (Jump j0){
            try {
                this.add(new Gosub(scanner));
            }
            catch (Jump j1){
                try {
                    this.add(new Goto(scanner));
                }
                catch (Jump j2){
                    try {
                        this.add(new Subexpression(scanner));
                    }
                    catch (Jump j3){
                        try {
                            this.add(new While(scanner));
                        }
                        catch (Jump j4){
                            try {
                                this.add(new For(scanner));
                            }
                            catch (Jump j5){

                                if (this.hasText())
                                    throw new Syntax(this,scanner,"Missing statement following colon");
                                else
                                    throw new Jump();
                            }
                        }
                    }
                }
            }
        }
        try {
            this.add(new Comment(scanner));
        }
        catch (Jump j){
        }
    }

}
