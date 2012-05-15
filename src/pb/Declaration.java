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
public class Declaration
    extends Node
{
    public final static Pattern Expr = new jauk.Re("<_>*([cC][oO][nN]|[vV][aA][rR])<_>");


    public final boolean constant;


    public Declaration(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);

            this.constant = ("con".equals(this.getText().toLowerCase()));
            try {

                this.add(new Type(scanner));
                try {

                    this.add(new Address(scanner));
                }
                catch (Jump j0){
                }
                try {

                    this.add(new System(scanner));
                }
                catch (Jump j0){
                }
            }
            catch (Jump j0){

                if (this.constant){
                    try {

                        this.add(new Identifier(scanner));
                    }
                    catch (Jump j1){
                        try {

                            this.add(new Literal(scanner));
                        }
                        catch (Jump j2){

                            throw new Syntax(this,scanner,"Missing type or identifier tail of declaration");
                        }
                    }
                }
                else {
                    try {

                        this.add(new Identifier(scanner));
                    }
                    catch (Jump j1){

                        throw new Syntax(this,scanner,"Missing type or identifier tail of declaration");
                    }
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
