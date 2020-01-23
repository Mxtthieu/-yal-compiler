package yal.analyse.symbol;

import yal.analyse.TDS;

public abstract class Symbole {

    private int dep;
    private String type;

    public Symbole(String t){
        type = t;
        dep = TDS.getInstance().TailleZoneVariable();
    }

    public int getDep() {
        return dep;
    }

    public String getType() {
        return type;
    }
}
