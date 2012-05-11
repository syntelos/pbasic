package pb;

import java.io.IOException;
import java.util.StringTokenizer;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Sequence list follows identifier as function call arguments
 */
public class Sequence
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*(<DoubleQuoted>|(<AlphaNumDot>|[_])+)(<_>*\",\"<_>*(<DoubleQuoted>|(<AlphaNumDot>|[_]))+)*<_>*");


    public final String[] list;


    public Sequence(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            this.list = List(this);
        }
        else
            throw new Jump();
    }


    public final static String[] List(Node node){
        String[] list = null;
        char[] text = node.getText().toCharArray();
        final int count = text.length;
        int start = 0;
        boolean inquoted = false;
        for (int cc = 0; cc < count; cc++){
            char ch = text[cc];
            switch (ch){
            case '\"':
                if (inquoted){
                    list = Add(list,(new String(text,start,(cc-start+1))));
                    inquoted = false;
                    start = (cc+1);
                }
                else {
                    inquoted = true;
                    start = cc;
                }
                break;
            case ',':
                if (!inquoted){
                    list = Add(list,(new String(text,start,(cc-start))).trim());
                    start = (cc+1);
                }
                break;
            default:
                break;
            }
        }
        return list;
    }
    public final static String[] Add(String[] list, String item){
        if (null == item || 1 > item.length())
            return list;
        else if (null == list)
            return new String[]{item};
        else {
            final int count = list.length;
            String[] copier = new String[count+1];
            java.lang.System.arraycopy(list,0,copier,0,count);
            copier[count] = item;
            return copier;
        }
    }

    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
