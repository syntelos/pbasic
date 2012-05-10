package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * 
 * <pre>
 * (Group) (Comment)?
 * (Identifier) (Declaration) (Comment)?
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
            try {
                if (this.isHeadIdentifier()){
                    this.add(new Declaration(scanner));
                    try {
                        this.add(new Comment(scanner));
                    }
                    catch (Jump j){
                    }
                    return;
                }
                else
                    throw new Jump();
            }
            catch (Jump j1){

                if (this.isHeadIdentifier() || this.isHeadLiteral()){

                    throw new Syntax(this,scanner,"Missing infix or declaration following identifier or literal");
                }
                else if (this.isHeadGroup()){
                    try {
                        this.add(new Comment(scanner));
                    }
                    catch (Jump j){
                    }
                    return;
                }
                else
                    throw new Jump();
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
