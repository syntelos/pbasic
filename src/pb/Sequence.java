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
import java.util.StringTokenizer;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Sequence list follows identifier as function call arguments.  A
 * sequence list contains {@link QuotedString}, {@link Identifier}, or
 * {@link Literal} -- separated by commas.
 * 
 * @see Subexpression
 */
public class Sequence
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*\",\"<_>*");


    public Sequence(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        while (true){
            try {
                this.add(new QuotedString(scanner));
            }
            catch (Jump j0){
                try {
                    this.add(new Identifier(scanner));
                }
                catch (Jump j1){

                    try {
                        this.add(new Literal(scanner));
                    }
                    catch (Jump j2){

                        try {
                            this.add(new Array(scanner));
                        }
                        catch (Jump j3){

                            break;
                        }
                    }
                }
            }
            String input = scanner.next(Expr);
            if (null != input)
                continue;
            else
                break;
        }

        if (this.isEmpty()){

            throw new Jump();
        }
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
