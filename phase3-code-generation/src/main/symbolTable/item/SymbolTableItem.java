package main.symbolTable.item;

import main.TypeCheck.Type;

public abstract class SymbolTableItem {
    private int numOfUsed = 0;
    private Type type = new Type();

    public Type getType() {return type;}
    public void setType(Type type) {this.type = type;}

    public void addUsed()
    {
        numOfUsed = numOfUsed + 1;
    }

    public int getNumOfUsed() {
        return numOfUsed;
    }

    public void setNumOfUsed(int numOfUsed) {
        this.numOfUsed = numOfUsed;
    }

    public abstract String getKey();
}
