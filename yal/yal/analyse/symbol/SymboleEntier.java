package yal.analyse.symbol;

public class SymboleEntier extends Symbole {

    /**
     *
     */
    public SymboleEntier() {
        super();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return "entier";
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isVar() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isParam() {
        return false;
    }
}
