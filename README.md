# KTPaint

A Microsoft Paint-like application made in Kotlin, recreated from Java code for
my Object Oriented Programming class.

![img](https://cdn.discordapp.com/attachments/385581275160903680/751879185382768660/unknown.png)

## Overview

The project uses Java's `javax.swing` GUI library to create and render an
application window. This allows the application to be platform independent,
lightweight, and with a swappable look and feel.

The individual shapes are children of abstract shape objects, and the
application demonstrates both inheritance and object utilization, as each
individual brush stroke or object is an individual object itself.

As noted earlier, the project is a recreation of the original project,
[JPaint](https://github.com/sharmavins23/JPaint). It is rewritten in Kotlin, but
still executes and runs on the Java Virtual Machine. Certain other differences
in code occur, as well - In Kotlin, the `main` entry point of the application
is a single instance object rather than just a class. Furthermore, various
constructors were moved up to the class declaration, as was stylistically
appropriate.

# License TL;DR

This project is distributed under the MIT license. This is a paraphrasing of a
[short summary](https://tldrlegal.com/license/mit-license).

This license is a short, permissive software license. Basically, you can do
whatever you want with this software, as long as you include the original
copyright and license notice in any copy of this software/source.

## What you CAN do:

-   You may commercially use this project in any way, and profit off it or the
    code included in any way;
-   You may modify or make changes to this project in any way;
-   You may distribute this project, the compiled code, or its source in any
    way;
-   You may incorporate this work into something that has a more restrictive
    license in any way;
-   And you may use the work for private use.

## What you CANNOT do:

-   You may not hold me (the author) liable for anything that happens to this
    code as well as anything that this code accomplishes. The work is provided
    as-is.

## What you MUST do:

-   You must include the copyright notice in all copies or substantial uses of
    the work;
-   You must include the license notice in all copies or substantial uses of the
    work.

If you're feeling generous, give credit to me somewhere in your projects.
