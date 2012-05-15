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
 * "If" (Subexpression) (Statement)* (EndIf) (Comment)?
 * </pre>
 */
public class If
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[iI][fF]");

    public If(Node parent, Scanner scanner)
        throws IOException, Syntax
    {
        super(parent,scanner);
        String input = scanner.next(Expr);
        if (null != input){

            final int first = scanner.currentLine();

            this.setText(input);

            try {
                this.add(new Subexpression(this,scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing range intializer subexpression following if");
            }

            try {
                this.add(new Then(this,scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing then following if");
            }

            boolean inline = false;
            try {

                while (true){

                    this.add(new Statement(this,scanner,inline));

                    inline = (first == scanner.currentLine());
                }
            }
            catch (Jump j){
            }

            if (inline){

                try {
                    this.add(new Comment(this,scanner));
                }
                catch (Jump j){
                }
            }
            else {
                try {
                    this.add(new Else(this,scanner));

                    try {
                        while (true){

                            this.add(new Statement(this,scanner));
                        }
                    }
                    catch (Jump j){
                    }
                }
                catch (Jump j1){
                }

                try {
                    this.add(new EndIf(this,scanner));
                }
                catch (Jump j){
                }

                try {
                    this.add(new Comment(this,scanner));
                }
                catch (Jump j){
                }
            }
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
