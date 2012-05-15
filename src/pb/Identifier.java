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
 *
 */
public class Identifier
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*<Alpha>(<AlphaNum>|_)+");


    public Identifier(Node parent, Scanner scanner)
        throws IOException, Syntax
    {
        super(parent,scanner);
        String input = scanner.next(Expr);
        if (null != input){

            if (IsKeyword(input)){

                scanner.revert();

                throw new Jump();
            }
            else {
                this.setText(input);

                try {
                    this.add(new Qualifier(this,scanner));
                }
                catch (Jump j){
                }
            }
        }
        else
            throw new Jump();
    }

    /*
     * Syntactic keywords
     */
    private final static lxl.Map<String,Boolean> Keywords = new lxl.Map();
    static {
        Keywords.put("con",Boolean.TRUE);
        Keywords.put("var",Boolean.TRUE);
        Keywords.put("define",Boolean.TRUE);
        Keywords.put("if",Boolean.TRUE);
        Keywords.put("then",Boolean.TRUE);
        Keywords.put("else",Boolean.TRUE);
        Keywords.put("elseif",Boolean.TRUE);
        Keywords.put("endif",Boolean.TRUE);
        Keywords.put("while",Boolean.TRUE);
        Keywords.put("endwhile",Boolean.TRUE);
        Keywords.put("gosub",Boolean.TRUE);
        Keywords.put("goto",Boolean.TRUE);
        Keywords.put("include",Boolean.TRUE);
        Keywords.put("for",Boolean.TRUE);
        Keywords.put("next",Boolean.TRUE);
        Keywords.put("system",Boolean.TRUE);
        Keywords.put("select",Boolean.TRUE);
        Keywords.put("case",Boolean.TRUE);
        Keywords.put("end",Boolean.TRUE);
    }
    /**
     * @param text Test
     * @return Is syntactic keyword
     */
    public final static boolean IsKeyword(String text){
        return (null != Keywords.get(text.toLowerCase()));
    }

    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
