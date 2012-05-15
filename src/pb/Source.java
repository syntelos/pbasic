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
 * Document root container node
 * 
 * The following (top level) syntactic alternatives are ordered for
 * matching specificity as well as a notion of frequency of occurance.
 * 
 * <pre>
 * (Comment)
 * (Config)
 * (Define)
 * (Label)
 * (Statement)
 * </pre>
 */
public class Source
    extends Node
{


    public Source(){
        super();
    }
    public Source(Scanner scanner)
        throws IOException, Syntax
    {
        super();
        this.parse(scanner);
    }


    public void parse(Scanner scanner)
        throws IOException, Syntax
    {
        while (scanner.isNotEmpty()){
            try {
                this.add(new Comment(scanner));
            }
            catch (Jump j0){
                try {
                    this.add(new Config(scanner));
                }
                catch (Jump j1){
                    try {
                        this.add(new Define(scanner));
                    }
                    catch (Jump j2){
                        try {
                            this.add(new Label(scanner));
                        }
                        catch (Jump j3){
                            try {
                                this.add(new Statement(scanner));
                            }
                            catch (Jump j4){
                                try {
                                    this.add(new WhiteSpace(scanner));
                                }
                                catch (Jump j5){

                                    throw new Syntax(this,scanner,"Unrecognized input");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
