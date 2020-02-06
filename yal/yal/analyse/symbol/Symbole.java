package yal.analyse.symbol;

import yal.analyse.TDS;

public abstract class Symbole {

    private int dep;


    public Symbole(){
        dep = TDS.getInstance().TailleZoneVariable();
    }

    public int getDep() {
        return dep;
    }

    public String getType() {
        return null;
    }

}
