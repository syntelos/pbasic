package pb;

import java.io.IOException;

import jauk.Pattern;
import jauk.Scanner;

/**
 * Prefix operators include {@link PrefixArithmetic} and {@link
 * PrefixLogical}
 */
public abstract class Prefix
    extends Node
{


    public Prefix(Scanner scanner)
        throws IOException, Syntax
    {
        super(scanner);
    }

}
