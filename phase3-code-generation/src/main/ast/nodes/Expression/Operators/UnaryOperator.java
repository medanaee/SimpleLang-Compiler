package main.ast.nodes.Expression.Operators;

public enum UnaryOperator {
    ADDRESS_OF("&"),
    DEREFERENCE("*"),
    PLUS("+"),
    MINUS("-"),
    BITWISE_NOT("~"),
    LOGICAL_NOT("!"),
    SIZEOF("sizeof"),
    PLUS_PLUS("++"),
    MINUS_MINUS("--");

    private final String symbol;

    UnaryOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static UnaryOperator fromString(String symbol) {
        for (UnaryOperator op : UnaryOperator.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown unary operator: " + symbol);
    }

    public boolean isArithmeticOperator() {
        return this == PLUS || this == MINUS || this == PLUS_PLUS || this == MINUS_MINUS;
    }

    public boolean isLogicalOperator() {
        return this == LOGICAL_NOT;
    }

    public boolean isBitwiseOperator() {
        return this == BITWISE_NOT || this == ADDRESS_OF || this == DEREFERENCE;
    }

    public boolean isSizeOperator() {
        return this == SIZEOF;
    }

    public boolean isIncrementOrDecrement() {
        return this == PLUS_PLUS || this == MINUS_MINUS;
    }
}
