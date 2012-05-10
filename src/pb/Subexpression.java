package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 * <pre>
 * (Group|Identifier|Literal) (Infix|Declaration) (Group|Identifier|Literal)
 * </pre>
 */
public class Subexpression
    extends Node
{


    public Subexpression(Reader reader)
        throws IOException, Syntax
    {
        super(reader);

        try {
            this.add(new Group(reader));
        }
        catch (Jump j0){
            try {
                this.add(new Identifier(reader));
            }
            catch (Jump j1){

                this.add(new Literal(reader));
            }
        }

        try {
            this.add(new Infix(reader));
        }
        catch (Jump j0){
            try {
                this.add(new Declaration(reader));
            }
            catch (Jump j1){

                throw new Syntax(this,reader,"Missing subexpression infix or declaration following group or identifier or literal");
            }
        }

        try {
            this.add(new Group(reader));
        }
        catch (Jump j0){
            try {
                this.add(new Identifier(reader));
            }
            catch (Jump j1){
                try {
                    this.add(new Literal(reader));
                }
                catch (Jump j2){

                    throw new Syntax(this,reader,"Missing subexpression group or identifier or literal following infix or declaration");
                }
            }
        }
    }


    public boolean isOperative(){
        return (this.get(1) instanceof Infix);
    }
    public boolean isDeclarative(){
        return (this.get(1) instanceof Declaration);
    }
}
