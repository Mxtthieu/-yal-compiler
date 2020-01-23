package yal.analyse.symbol;

public class SymboleBool extends Symbole {

    public SymboleBool(String t) {
        super(t);
    }

    @Override
    public String getType() {
        return "bool";
    }

}
