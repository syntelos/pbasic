package pb;

import java.io.IOException;


/**
 *
 */
public abstract class Node
    extends lxl.ArrayList<Node>
{

    public final Comment comment;

    private String name, capture, text;


    public Node(){
        super();
        this.comment = null;
    }
    public Node(Reader reader)
        throws IOException, Syntax
    {
        super();
        this.comment = reader.comment();
    }


    /**
     * Set once capture, set many trimmed text.
     */
    protected void setText(String text){
        if (null == this.capture)
            this.capture = text;

        this.text = text.trim();
    }
    public final String getCapture(){
        return this.capture;
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

        if (null != this.comment){

            this.comment.print();
        }

        if (null != this.text){

            java.lang.System.out.printf("% 20s> %s%n",this.getName(),this.getText());
        }

        for (Node child: this){

            child.print();
        }
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
