package yal.analyse.symbol;

public class SymboleBool extends Symbole {

    public SymboleBool() {
        super();
    }

    @Override
    public String getType() {
        return "bool";
    }

    @Override
    public boolean isVar() {
        return true;
    }

    @Override
    public boolean isParam() {
        return false;
    }

}
