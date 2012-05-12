package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Declaration
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*([cC][oO][nN]|[vV][aA][rR])<_>+");


    public final boolean constant;


    public Declaration(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            this.constant = ("con".equals(this.getText().toLowerCase()));
            try {

                this.add(new Type(scanner));
                try {

                    this.add(new Address(scanner));
                }
                catch (Jump j0){
                }
                try {

                    this.add(new System(scanner));
                }
                catch (Jump j0){
                }
            }
            catch (Jump j0){

                if (this.constant){
                    try {

                        this.add(new Identifier(scanner));
                    }
                    catch (Jump j1){
                        try {

                            this.add(new Literal(scanner));
                        }
                        catch (Jump j2){

                            throw new Syntax(this,scanner,"Missing type or identifier tail of declaration");
                        }
                    }
                }
                else {
                    try {

                        this.add(new Identifier(scanner));
                    }
                    catch (Jump j1){

                        throw new Syntax(this,scanner,"Missing type or identifier tail of declaration");
                    }
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
