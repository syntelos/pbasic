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

        if (null != this.capture){

            System.out.printf("% 20s> %s%n",this.getName(),this.getCapture());
        }

        for (Node child: this){

            child.print();
        }
    }


    public final static String NameOf(Class<? extends Node> nodeclass){

        return nodeclass.getSimpleName();
    }
}
