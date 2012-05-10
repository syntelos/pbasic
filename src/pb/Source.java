package pb;

import java.io.IOException;

import jauk.Pattern;

/**
 * Document root container node
 * 
 * The following syntactic alternatives are ordered for matching
 * specificity as well as a notion of frequency of occurance.
 * 
 * <pre>
 * (Comment)
 * (Label)
 * (Gosub)
 * (Goto)
 * (Statement)
 * (Subexpression)
 * (While)
 * (For)
 * </pre>
 */
public class Source
    extends Node
{


    public Source(Reader reader)
        throws IOException, Syntax
    {
        super();

        while (reader.isNotEmpty()){
            try {
                this.add(new Comment(reader));
            }
            catch (Jump j0){
                try {
                    this.add(new Label(reader));
                }
                catch (Jump j1){
                    try {
                        this.add(new Gosub(reader));
                    }
                    catch (Jump j2){
                        try {
                            this.add(new Goto(reader));
                        }
                        catch (Jump j3){
                            try {
                                this.add(new Statement(reader));
                            }
                            catch (Jump j4){
                                try {
                                    this.add(new Subexpression(reader));
                                }
                                catch (Jump j5){
                                    try {
                                        this.add(new While(reader));
                                    }
                                    catch (Jump j6){
                                        try {
                                            this.add(new For(reader));
                                        }
                                        catch (Jump j7){

                                            throw new Syntax(this,reader,"Unrecognized input");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
