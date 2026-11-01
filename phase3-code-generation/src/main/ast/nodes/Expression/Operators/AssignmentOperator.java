package main.ast.nodes.Expression.Operators;

public enum AssignmentOperator {
    ASSIGN("="),
    MULTIPLICATION_ASSIGN("*="),
    DIVISION_ASSIGN("/="),
    MODULUS_ASSIGN("%="),
    ADDITION_ASSIGN("+="),
    SUBTRACTION_ASSIGN("-="),
    LEFT_SHIFT_ASSIGN("<<="),
    RIGHT_SHIFT_ASSIGN(">>="),
    MATHEMATICAL_LEFT_SHIFT_ASSIGN("<<<="),
    MATHEMATICAL_RIGHT_SHIFT_ASSIGN(">>>="),
    BITWISE_AND_ASSIGN("&="),
    BITWISE_XOR_ASSIGN("^="),
    BITWISE_OR_ASSIGN("|=");

    private final String symbol;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    int line;


    AssignmentOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static AssignmentOperator fromString(String symbol) {
        for (AssignmentOperator op : AssignmentOperator.values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown assignment operator: " + symbol);
    }
}
