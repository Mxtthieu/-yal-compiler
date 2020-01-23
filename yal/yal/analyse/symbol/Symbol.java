package yal.analyse.symbol;

import yal.analyse.TDS;

public abstract class Symbol {

    private int dep;

    public Symbol(){
            dep = TDS.getInstance().TailleZoneVariable();
    }

    public int getDep() {
        return dep;
    }

    public String getType() {
        return null;
    }
}
