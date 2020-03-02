package yal.analyse.symbol;

public class SymboleParam extends Symbole {
    @Override
    public boolean isVar() {
        return false;
    }

    @Override
    public boolean isParam() {
        return true;
    }
}
