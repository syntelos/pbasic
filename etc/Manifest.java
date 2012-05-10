
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Manifest {

    public final static String OF = "src/Manifest.mf";


    public static void main(String[] argv){

        String mainclass = argv[0], classpath = null;
        if (1 < argv.length)
            classpath =  argv[1];

        File manifest = new File(OF);
        try {
            PrintStream out = new PrintStream(new FileOutputStream(manifest));
            try {
                out.printf("Main-Class: %s%n",mainclass);

                if (null != classpath)
                    out.printf("Class-Path: %s%n",classpath);

            }
            finally {
                out.close();
            }
        }
        catch (IOException exc){
            System.err.printf("Unable to write to file '%s'%n",OF);
            System.exit(1);
        }
    }
}
