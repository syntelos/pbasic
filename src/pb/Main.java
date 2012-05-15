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

import java.io.File;
import java.io.IOException;

import jauk.Resource;
import jauk.Scanner;


public class Main {


    public static void main(String[] argv){
        if (0 < argv.length){

            final Resource source = new Resource(new File(argv[0]));
            try {
                final Scanner in = new Scanner(source);

                final Source doc = new Source();
                try {
                    doc.parse(in);

                    doc.print();
                }
                catch (Syntax sx){
                    sx.printStackTrace();
                    doc.print();
                    java.lang.System.exit(1);
                }
                finally {
                    in.close();
                }
            }
            catch (IOException exc){
                java.lang.System.err.printf("Unable to read file '%s'%n",argv[0]);
                java.lang.System.exit(1);
            }
        }
        else {
            java.lang.System.err.println("Usage: pbasic file.bas");
            java.lang.System.exit(1);
        }
    }
}
