package yal.analyse.symbol;

import yal.analyse.TDS;

public class SymboleTableau extends Symbole {

    public SymboleTableau() {
        super();
        dep = -TDS.getInstance().tailleTableParam();
    }
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
        return false;
    }
}
