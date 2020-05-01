package yal.analyse.symbol;

import yal.analyse.TDS;

public class SymboleTableau extends Symbole {
    private int longeur;

    public SymboleTableau() {
        super();
        dep = -TDS.getInstance().tailleTableParam();
        this.longeur = 0;
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

    @Override
    public String getType(){
        return "tab";
    }

    public int getLongeur() {
        return longeur;
    }

    public void setLongeur(int longeur) {
        this.longeur = longeur;
    }
}
