package pb;

import java.io.IOException;

import jauk.Scanner;

/**
 *
 */
public abstract class Node
    extends lxl.ArrayList<Node>
{

    private String name, capture, text;

    public final int linenumber;


    public Node(){
        super();
        this.linenumber = 0;
    }
    public Node(Scanner scanner)
        throws IOException, Syntax
    {
        super();
        this.linenumber = scanner.currentLine();
    }


    /**
     * Set once capture, set many trimmed text.
     */
    protected void setText(String text){
        if (null == this.capture)
            this.capture = text;

        this.text = text.trim();
    }
    public final boolean hasCapture(){
        return (null != this.capture);
    }
    public final String getCapture(){
        return this.capture;
    }
    public final boolean hasText(){
        return (null != this.text);
    }
    public final String getText(){
        return this.text;
    }
    public final String getName(){
        String name = this.name;
        if (null == name){
            name = NameOf(this.getClass());
            this.name = name;
        }
        return name;
    }


    public void print(){

        java.lang.System.out.println(this.toString());
    }
    public String toString(){
        return this.toString(1);
    }
    public String toString(int indent){
        StringBuilder string = new StringBuilder();

        if (null != this.text){
            Indent(indent,string);
            string.append(String.format("%s\t%s%n",this.getName(),this.getText()));
        }

        for (Node child: this){

            string.append(child.toString(indent+1));
        }
        return string.toString();
    }


    public final static String NameOf(Class<? extends Node> nodeclass){

        return nodeclass.getSimpleName();
    }
    public final static void Indent(int indent, StringBuilder string){
        for (int cc = 0; cc < indent; cc++){
            string.append('\t');
        }
    }
}
