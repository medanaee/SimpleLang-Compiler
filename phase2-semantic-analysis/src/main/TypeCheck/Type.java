package main.TypeCheck;

import java.util.List;

public class Type {
    public enum BaseType {
        NO_TYPE,
        BOOL,
        INT,
        FLOAT,
        SUPER,
        PORTS
    }
    public BaseType baseType;
    public int width;
    public Boolean isSigned;
    public List<String> ports;

    public Type(){
        this.baseType = BaseType.NO_TYPE;
    }
    public Type(BaseType baseType, int width, boolean isSigned){
        this.baseType = baseType;
        this.isSigned = isSigned;
        this.width = width;
    }
    public Type(List<String> ports){
        this.ports = ports;
    }
    public boolean isNoType() {return baseType==BaseType.NO_TYPE;}
    public void copy(Type type){
        this.baseType = type.baseType;
        this.width = type.width;
        this.isSigned = type.isSigned;
        this.ports = type.ports;
    }
    public BaseType getBaseType() {return baseType;}
    public void setBaseType(BaseType baseType) {this.baseType = baseType;}
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Type other) {
            if (this.baseType != other.baseType)
                return false;
            if(this.baseType == BaseType.PORTS){
                if(this.ports.size() != other.ports.size())
                    return false;
                for(int i=0;i<this.ports.size();i++){
                    if(!this.ports.get(i).equals(other.ports.get(i)))
                        return false;
                }
                return true;
            }
            return this.width == other.width && this.isSigned == other.isSigned;
        }
        return false;
    }

    public List<String> getPorts() {
        return ports;
    }

    // @Override
    // public String toString(){
    //     return baseType.toString();
    // }
}
