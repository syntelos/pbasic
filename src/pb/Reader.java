/*
 * Parse Basic
 * Copyright (C) 2012 John Pritchard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package pb;

import jauk.Resource;
import jauk.Match;
import jauk.Pattern;

import java.io.IOException;

/**
 * 
 * @author jdp
 */
public final class Reader
    extends jauk.Scanner
{

    public final String sourcepath;

    private Comment comment;


    public Reader(Resource source)
        throws IOException
    {
        super(source);
	this.sourcepath = source.path;
    }


    public void comment(Jump jump){

        this.comment = jump.comment;
    }
    public Comment comment()
        throws IOException, Syntax
    {
        Comment comment = this.comment;

        this.comment = null;

        try {
            return new Comment(this);
        }
        catch (Jump to){

            return comment;
        }
    }
    public String next(Pattern pattern){

        this.comment = null;

        return super.next(pattern);
    }
}
