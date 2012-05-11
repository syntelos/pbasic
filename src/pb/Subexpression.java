package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 * <pre>
 * (Group) (Comment)?
 * (Identifier) (Declaration) (Comment)?
 * (Identifier) (Sequence) (Comment)?
 * (Group|Identifier|Literal) (Infix) (Group|Identifier|Literal)? (Comment)?
 * </pre>
 */
public class Subexpression
    extends Node
{


    public Subexpression(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        try {
            this.add(new Group(scanner));
        }
        catch (Jump j0){
            try {
                this.add(new Identifier(scanner));
            }
            catch (Jump j1){

                this.add(new Literal(scanner));
            }
        }

        try {
            this.add(new Infix(scanner));
        }
        catch (Jump j0){

            if (this.isHeadIdentifier()){
                try {
                    this.add(new Declaration(scanner));
                }
                catch (Jump j1){
                    try {
                        /*
                         * This pattern will match liberally
                         * (including Identifiers, Declaration and
                         * Literals).  It must follow more particular
                         * patterns.
                         */
                        this.add(new Sequence(scanner));
                    }
                    catch (Jump j2){

                        throw new Syntax(this,scanner,"Missing declaration or sequence following identifier");
                    }
                }
                try {
                    this.add(new Comment(scanner));
                }
                catch (Jump j){
                }
                return;
            }
            else if (this.isHeadLiteral()){

                throw new Syntax(this,scanner,"Missing infix following literal");
            }
            else {
                /*
                 * Solitary Group
                 */
                try {
                    this.add(new Comment(scanner));
                }
                catch (Jump j){
                }
                return;
            }
        }

        try {
            this.add(new Group(scanner));
        }
        catch (Jump j0){
            try {
                this.add(new Identifier(scanner));
            }
            catch (Jump j1){
                try {
                    this.add(new Literal(scanner));
                }
                catch (Jump j2){

                    if (this.isOperative()){

                        throw new Syntax(this,scanner,"Missing subexpression group or identifier or literal following infix or declaration");
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


    public boolean isHeadGroup(){
        return (this.get(0) instanceof Group);
    }
    public boolean isHeadIdentifier(){
        return (this.get(0) instanceof Identifier);
    }
    public boolean isHeadLiteral(){
        return (this.get(0) instanceof Identifier);
    }
    public boolean isOperative(){
        return (this.get(1) instanceof Infix);
    }
    public boolean isDeclarative(){
        return (this.get(1) instanceof Declaration);
    }
    public boolean isFunctional(){
        return (this.get(1) instanceof Sequence);
    }
    public boolean isTailGroup(){
        return (this.get(2) instanceof Group);
    }
    public boolean isTailIdentifier(){
        return (this.get(2) instanceof Identifier);
    }
    public boolean isTailLiteral(){
        return (this.get(2) instanceof Identifier);
    }
}
