/*
 * Parse BASIC
 * Copyright (C) 2012 John Pritchard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package pb;

import java.io.IOException;
import java.io.PrintStream;

import jauk.Scanner;

/**
 *
 */
public abstract class Node
    extends lxl.ArrayList<Node>
{

    private String name, capture, text;

    private Node parent;

    public final int linenumber;


    public Node(){
        super();
        this.linenumber = 0;
    }
    public Node(Node parent, Scanner scanner)
        throws IOException, Syntax
    {
        super();
        this.parent = parent;
        this.linenumber = scanner.currentLine();
    }


    public final void destroy(){
        this.parent = null;

        for (Node child: this){

            child.destroy();
        }
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
    public final boolean hasParent(){
        return (null != this.parent);
    }
    public final Node getParent(){
        return this.parent;
    }
    public final void print(PrintStream out){

        out.println(this.toString());
    }
    /**
     * List the node tree from the oldest unattached ancestor,
     * otherwise list the subtree from this node.
     * 
     * In each node constructor, the pattern <code>add(new
     * Node())</code> implies that the syntax exception (thrown from
     * the node constructor) is called on an unattached subtree.  The
     * add method has not been entered within the offending chain of
     * constructors, and the nodes in this chain are not present in
     * the list of children of their respective parents.  
     * 
     * This method lists a subtree of unattached nodes by finding and
     * listing the oldest, unattached ancestor.
     * 
     * @see Syntax
     * @see #toString()
     */
    public final String trace(){

        Node child = this;
        Node parent = this.parent;
        if (null != parent){
            StringBuilder string = new StringBuilder();

            while (null != parent && (!parent.contains(child))){
                final int d = child.depth();

                string.append(String.format("trace depth %02d ------------------ %02d ------------------ %02d ------------------%n",d,d,d));

                string.append(child.toString(d));

                child = parent;
                parent = parent.getParent();
            }
            return string.toString();
        }
        else
            return this.toString();
    }
    public final int depth(){
        int d = 0;
        Node p = this.parent;
        while (null != p){
            d += 1;
            p = p.parent;
        }
        return d;
    }
    public final String toString(){

        return this.toString(1);
    }
    public String toString(int indent){
        StringBuilder string = new StringBuilder();

        Indent(indent,string);

        if (null != this.text){

            string.append(String.format("%s:%d:\t%s%n",this.getName(),this.linenumber,this.getText()));
        }
        else {

            string.append(String.format("%s:%d:%n",this.getName(),this.linenumber));
        }


        for (Node child: this){

            string.append(child.toString(indent+1));
        }
        return string.toString();
    }
    @Override
    public final int indexOf(Node child){
        for (int cc = 0, count = this.size(); cc < count; cc++){
            if (child == this.get(cc))
                return cc;
        }
        return -1;
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
