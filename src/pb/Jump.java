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

/**
 * Parse process flow jump out of constructor.  No match, safe to
 * resume parsing.
 * 
 * @author jdp
 */
final class Jump
    extends java.lang.RuntimeException
{

    public final Comment comment;

    Jump(){
        super();
        this.comment = null;
    }
    Jump(Comment comment){
        super();
        this.comment = comment;
    }
}
