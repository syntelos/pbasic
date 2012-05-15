

Parse BASIC

  A Jauk application to parse PIC BASIC.

  Got a big hairy PIC BASIC program to grok?  The project to convert
  it to another language for simulation or porting is a good way to do
  this.


Included dependencies

  Jauk
     http://github.com/syntelos/jauk

  LXL
     http://code.google.com/p/lxl


Overview

  Main

    Print the syntax tree for a PIC BASIC program with

      java -jar pbasic.jar file.bas

  Source

    Document root node: load a PIC BASIC source file into a syntax tree.

  Node

    Common base class for the nodes of the syntax tree uses a LXL List.

  Comment, Config, Define, Label, Statement

    Primary syntax nodes: see Source

  Include, Gosub, Goto, If, Select, While, For, Subexpression

    Secondary syntax nodes: see Statement

  Scanner

    Jauk input scanner.  To debug Jauk applications set a break point
    in the match method on "return match".

    http://github.com/syntelos/jauk/blob/master/src/jauk/Scanner.java

