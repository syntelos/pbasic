package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Infix operators include {@link Arithmetic}, {@link Logical}, {@link
 * Relational} and {@link Iterator}.
 */
public abstract class Infix
    extends Node
{


    public Infix(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
    }

}
