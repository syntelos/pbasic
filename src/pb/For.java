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
 * "For" (Subexpression) (Statement)* (EndFor) (Comment)?
 * </pre>
 */
public class For
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*[fF][oO][rR]<_>*");

    public For(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            try {
                this.add(new Subexpression(scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing range intializer subexpression following for");
            }
            try {
                this.add(new Range(scanner));
            }
            catch (Jump j1){

                throw new Syntax(this,scanner,"Missing range iterator subexpression following for");
            }

            try {
                while (true){

                    this.add(new Statement(scanner));
                }
            }
            catch (Jump j){
            }

            try {
                this.add(new EndFor(scanner));
            }
            catch (Jump j){

                throw new Syntax(this,scanner,"Missing end for: next");
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
