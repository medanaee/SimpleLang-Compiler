package main.ast.nodes.Expression.Operators;

public enum IncOrDecOperator {

    PLUS_PLUS("++"),
    MINUS_MINUS("--");

    private final String symbol;

    IncOrDecOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static IncOrDecOperator fromString(String symbol) {
        for (IncOrDecOperator op : IncOrDecOperator.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown unary operator: " + symbol);
    }

    public boolean isArithmeticOperator() {
        return this == PLUS_PLUS || this == MINUS_MINUS;
    }

    public boolean isIncrementOrDecrement() {
        return this == PLUS_PLUS || this == MINUS_MINUS;
    }
}
