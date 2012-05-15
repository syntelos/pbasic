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
 * (":")? (Include|Gosub|Goto|Subexpression|While|For) (Comment)?
 * </pre>
 */
public class Statement
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*:");


    public Statement(Scanner scanner)
        throws IOException, Syntax
    {
        this(scanner,false);
    }
    public Statement(Scanner scanner, boolean inline)
        throws IOException, Syntax
    {
        super(scanner);
        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else if (inline)
            throw new Jump();

        try {
            this.add(new Include(scanner));
        }
        catch (Jump j0){
            try {
                this.add(new Gosub(scanner));
            }
            catch (Jump j1){
                try {
                    this.add(new Goto(scanner));
                }
                catch (Jump j2){
                    try {
                        this.add(new If(scanner));
                    }
                    catch (Jump j3){
                        try {
                            this.add(new Select(scanner));
                        }
                        catch (Jump j4){
                            try {
                                this.add(new While(scanner));
                            }
                            catch (Jump j5){
                                try {
                                    this.add(new For(scanner));
                                }
                                catch (Jump j6){
                                    try {
                                        this.add(new Subexpression(scanner,inline));
                                    }
                                    catch (Jump j7){

                                        if (this.hasText())
                                            throw new Syntax(this,scanner,"Missing statement following colon");
                                        else
                                            throw new Jump();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        try {
            this.add(new Comment(scanner));
        }
        catch (Jump j){
        }
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
