package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 *
 */
public class Identifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*<Alpha>(<Alpha>|<Digit>|_)+<_>*");

    public Identifier(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            if (IsKeyword(input)){

                scanner.revert();

                throw new Jump();
            }
            else {
                this.setText(input);

                try {
                    this.add(new Qualifier(scanner));
                }
                catch (Jump j){
                }
            }
        }
        else
            throw new Jump();
    }

    /*
     * Syntactic keywords
     */
    private final static lxl.Map<String,Boolean> Keywords = new lxl.Map();
    static {
        Keywords.put("con",Boolean.TRUE);
        Keywords.put("var",Boolean.TRUE);
        Keywords.put("define",Boolean.TRUE);
        Keywords.put("if",Boolean.TRUE);
        Keywords.put("then",Boolean.TRUE);
        Keywords.put("else",Boolean.TRUE);
        Keywords.put("elseif",Boolean.TRUE);
        Keywords.put("endif",Boolean.TRUE);
        Keywords.put("while",Boolean.TRUE);
        Keywords.put("endwhile",Boolean.TRUE);
        Keywords.put("gosub",Boolean.TRUE);
        Keywords.put("goto",Boolean.TRUE);
        Keywords.put("include",Boolean.TRUE);
        Keywords.put("for",Boolean.TRUE);
        Keywords.put("next",Boolean.TRUE);
        Keywords.put("system",Boolean.TRUE);
        Keywords.put("select",Boolean.TRUE);
        Keywords.put("case",Boolean.TRUE);
        Keywords.put("end",Boolean.TRUE);
    }
    /**
     * @param text Test
     * @return Is syntactic keyword
     */
    public final static boolean IsKeyword(String text){
        return (null != Keywords.get(text.toLowerCase()));
    }

    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
