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
 * Prefix arithmetic operators 
 */
public class PrefixArithmetic
    extends Prefix
{
    public final static Pattern Expr = new jauk.Re("<_>*([aA][bB][sS]|[sS][qQ][rR]|[sS][iI][nN]|[dD][iI][vV]\"32\"|\"~\")");


    public PrefixArithmetic(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);

        String input = scanner.next(Expr);
        if (null != input){

            this.setText(input);
        }
        else
            throw new Jump();
    }


    public static void main(String[] argv){
        java.lang.System.out.println(Expr.toString());
    }
}
