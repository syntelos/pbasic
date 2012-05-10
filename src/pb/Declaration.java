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


    public Declaration(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
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
                try {

                    this.add(new Identifier(scanner));
                }
                catch (Jump j1){

                    throw new Syntax(this,scanner,"Missing type or identifier tail of declaration");
                }
            }
        }
        else
            throw new Jump();
    }

}
