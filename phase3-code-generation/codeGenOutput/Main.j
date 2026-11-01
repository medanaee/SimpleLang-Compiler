.class public Main
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 128
    .limit locals 128

    ldc 4
    istore 8
    iload 8
    istore 7
    ldc 6.0
    fstore 4
    fload 4
    fstore 3
    ldc 3
    ineg
    istore 10
    iload 10
    istore 9
    ldc 0
    istore 14
    ldc 0
    istore 13
    ldc 0.0
    fstore 12
    ldc 0.0
    fstore 11
    ldc 0.0
    fstore 6
    ldc 0.0
    fstore 5
    ldc 0
    istore 2
    ldc 0
    istore 1
    iload 2
    istore 1
    fload 4
    fstore 3
    fload 6
    fstore 5
    iload 8
    istore 7
    iload 10
    istore 9
    fload 12
    fstore 11
    iload 14
    istore 13
    iload 7
    ldc 1
    ishl
    iload 9
    ldc 1
    iand
    iadd
    istore 14
    iload 7
    iload 9
    if_icmpgt Ltrue_13
    iconst_0
    goto Lend_14
Ltrue_13:
    iconst_1
Lend_14:
    ifeq Lfalse_11
    ldc 1
    goto Lend_12
Lfalse_11:
    ldc 0
Lend_12:
    istore 2
    fload 3
    ldc 2.0
    fdiv
    iload 1
    i2f
    fadd
    fstore 12
    iload 13
    i2f
    fload 11
    fadd
    fstore 6
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 7
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc " "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 3
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_15
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_16
Lnot_zero_15:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_16:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc " "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 9
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 13
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 11
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_17
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_18
Lnot_zero_17:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_18:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 5
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_19
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_20
Lnot_zero_19:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_20:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    iload 2
    istore 1
    fload 4
    fstore 3
    fload 6
    fstore 5
    iload 8
    istore 7
    iload 10
    istore 9
    fload 12
    fstore 11
    iload 14
    istore 13
    iload 7
    ldc 1
    ishl
    iload 9
    ldc 1
    iand
    iadd
    istore 14
    iload 7
    iload 9
    if_icmpgt Ltrue_23
    iconst_0
    goto Lend_24
Ltrue_23:
    iconst_1
Lend_24:
    ifeq Lfalse_21
    ldc 1
    goto Lend_22
Lfalse_21:
    ldc 0
Lend_22:
    istore 2
    fload 3
    ldc 2.0
    fdiv
    iload 1
    i2f
    fadd
    fstore 12
    iload 13
    i2f
    fload 11
    fadd
    fstore 6
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 7
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc " "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 3
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_25
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_26
Lnot_zero_25:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_26:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    ldc " "
    invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 9
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    iload 13
    invokevirtual java/io/PrintStream/print(I)V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 11
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_27
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_28
Lnot_zero_27:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_28:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    getstatic java/lang/System/out Ljava/io/PrintStream;
    fload 5
    dup
    fconst_0
    fcmpg
    ifne Lnot_zero_29
    pop
    iconst_0
    invokevirtual java/io/PrintStream/print(I)V
    goto Lend_print_30
Lnot_zero_29:
    invokevirtual java/io/PrintStream/print(F)V
Lend_print_30:
    getstatic java/lang/System/out Ljava/io/PrintStream;
    invokevirtual java/io/PrintStream/println()V
    iload 2
    istore 1
    fload 4
    fstore 3
    fload 6
    fstore 5
    iload 8
    istore 7
    iload 10
    istore 9
    fload 12
    fstore 11
    iload 14
    istore 13

    return
.end method
