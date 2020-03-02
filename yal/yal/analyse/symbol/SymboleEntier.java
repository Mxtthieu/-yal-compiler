package yal.analyse.symbol;

public class SymboleEntier extends Symbole {


    public SymboleEntier() {
        super();
    }

    @Override
    public String getType() {
        return "entier";
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
