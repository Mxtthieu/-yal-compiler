package yal.analyse.symbol;

import yal.analyse.TDS;

public class SymboleParam extends Symbole {

    /**
     *
     */
    public SymboleParam(){
       super();
       dep = 16 + TDS.getInstance().tailleTableParam();
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
        return true;
    }
}
