# SimpleLang Compiler

A three-phase compiler for **SimpleLang**, a hardware-description-style language with components, ports, generate blocks, genvars, loops and a scheduler. Built in Java with ANTLR 4 for the Programming Languages and Compilers course at the University of Tehran (Fall 2025).

| Phase | Folder | What it does |
|-------|--------|--------------|
| 1 | [`phase1-parser`](phase1-parser) | ANTLR lexer and parser grammars, AST construction, and AST printing via a visitor |
| 2 | [`phase2-semantic-analysis`](phase2-semantic-analysis) | Symbol tables, name analysis and type checking, with error reporting |
| 3 | [`phase3-code-generation`](phase3-code-generation) | Scheduler handling and JVM bytecode generation with [Jasmin](https://github.com/davidar/jasmin) |

## Pipeline

```
source (.sl) → lexer/parser → AST → name analysis → type checking → scheduler → Jasmin → JVM bytecode
```

## Tech

Java, ANTLR 4.13.1, Jasmin, Jackson.

## Running

Each phase is self-contained. The required jars are in its `utilities` folder, and `samples` has inputs with expected outputs (`sampleN.sl` / `sampleN-out.sl`).

1. Generate the lexer and parser from the grammar in `src/main/grammar` with `utilities/antlr-4.13.1-complete.jar`.
2. Compile the sources and run the `SimpleLang` main class on a sample.
3. Phase 2 includes `run_test.sh`, which runs all samples.

The assignment handouts are included as PDFs in each phase folder.
