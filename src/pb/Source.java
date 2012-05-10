package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Document root container node
 * 
 * The following (top level) syntactic alternatives are ordered for
 * matching specificity as well as a notion of frequency of occurance.
 * 
 * <pre>
 * (Comment)
 * (Config)
 * (Define)
 * (Label)
 * (Statement)
 * </pre>
 */
public class Source
    extends Node
{


    public Source(Scanner scanner)
        throws IOException, Syntax
    {
        super();

        while (scanner.isNotEmpty()){
            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j0){
                try {
                    this.add(new Config(scanner));
                }
                catch (Jump j1){
                    try {
                        this.add(new Define(scanner));
                    }
                    catch (Jump j2){
                        try {
                            this.add(new Label(scanner));
                        }
                        catch (Jump j3){
                            try {
                                this.add(new Statement(scanner));
                            }
                            catch (Jump j4){

                                throw new Syntax(this,scanner,"Unrecognized input");
                            }
                        }
                    }
                }
            }
        }
    }

}
