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
 * ("goto" Identifier)
 * </pre>
 */
public class Case
    extends Node
{
    public enum Use {
        Head, Body;
    }

    public final static Pattern Expr = new jauk.Re("<_>*[cC][aA][sS][eE]");
    public final static Pattern Colon = new jauk.Re("<_>*:");


    public Case(Scanner scanner, Use use)
        throws IOException, Syntax
    {
        super(scanner);

        final String input;

        switch(use){
        case Head:
            input = scanner.next(Expr);
            if (null != input){

                this.setText(input);

                try {
                    this.add(new Identifier(scanner));
                }
                catch (Jump j0){
                    try {
                        this.add(new Literal(scanner));
                    }
                    catch (Jump j1){

                        throw new Syntax(this,scanner,"Missing identifier following select 'case'");
                    }
                }

                try {
                    this.add(new Comment(scanner));
                }
                catch (Jump j){
                }
            }
            else
                throw new Jump();

            return;

        case Body:
            input = scanner.next(Expr);
            if (null != input){

                this.setText(input);

                try {
                    this.add(new Identifier(scanner));
                }
                catch (Jump j0){
                    try {
                        this.add(new Literal(scanner));
                    }
                    catch (Jump j1){

                        throw new Syntax(this,scanner,"Missing identifier following select 'case'");
                    }
                }

                final String colon = scanner.next(Colon);
                if (null != colon){

                    try {
                        this.add(new Comment(scanner));
                    }
                    catch (Jump j){
                    }

                    try {
                        while (true){

                            this.add(new Statement(scanner));
                        }
                    }
                    catch (Jump j){
                    }
                }
                else
                    throw new Syntax(this,scanner,"Missing colon (:) following select 'case' <identifier>");
            }
            else
                throw new Jump();

            return;

        default:
            throw new Error(use.name());
        }
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
