package yal.analyse.symbol;

public class SymboleParam extends Symbole {

    /**
     *
     * @return
     */
    @Override
    public boolean isVar() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isParam() {
        return true;
    }
}
