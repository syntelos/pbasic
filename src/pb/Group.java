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

import jauk.Pattern;
import jauk.Scanner;

/**
 * <pre>
 * ( "(" Subexpression (EndGroup=")"))
 * </pre>
 */
public class Group
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\"(\"<_>*");


    public Group(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Subexpression(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing subexpression within group");
            }

            try {
                this.add(new EndGroup(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing close paren end group");
            }

            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j){
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
