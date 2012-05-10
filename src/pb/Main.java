package pb;

import java.io.File;
import java.io.IOException;
import jauk.Resource;


public class Main {


    public static void main(String[] argv){
        if (0 < argv.length){

            final Resource source = new Resource(new File(argv[0]));
            try {
                final Reader in = new Reader(source);
                try {
                    Source doc = new Source(in);

                    doc.print();
                }
                catch (Syntax sx){
                    sx.printStackTrace();
                    java.lang.System.exit(1);
                }
                finally {
                    in.close();
                }
            }
            catch (IOException exc){
                java.lang.System.err.printf("Unable to read file '%s'%n",argv[0]);
                java.lang.System.exit(1);
            }
        }
        else {
            java.lang.System.err.println("Usage: pbasic file.bas");
            java.lang.System.exit(1);
        }
    }
}
