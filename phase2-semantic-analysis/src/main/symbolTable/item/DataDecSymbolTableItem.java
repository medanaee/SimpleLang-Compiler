package main.symbolTable.item;

import main.ast.nodes.*;
import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclAssignment;
import main.ast.nodes.Expression.Expression;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DataDecSymbolTableItem extends SymbolTableItem {
    public static final String START_KEY = "Dec_";
    private NetDeclAssignment declaration;

    private Expression value;

    public static record IntPair(int times, int period) {
    }

    private ArrayList<IntPair> repeatTimes = new ArrayList<>();

    public NetDeclAssignment getDeclaration() {
        return declaration;
    }

    public int hasConflict() {
        Set<Integer> occupiedTimes = new HashSet<>();

        for (IntPair pair : repeatTimes) {
            for (int i = 1; i < pair.times(); i++) {

                int time = i * pair.period();

                if (occupiedTimes.contains(time)) {
                    return time;
                }

                occupiedTimes.add(time);
            }
        }

        return -1;
    }

    public void addRepeatTimes(IntPair repeatTime) {
        this.repeatTimes.add(repeatTime);
    }

    public void setDeclaration(NetDeclAssignment declaration) {
        this.declaration = declaration;
    }

    public DataDecSymbolTableItem(NetDeclAssignment declaration) {
        this.declaration = declaration;
        this.value = declaration.getExpression();
    }

    public boolean IsValueNull() {
        return value == null;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        // return declaration.getName();
        return START_KEY + this.declaration.getName();
    }

}
