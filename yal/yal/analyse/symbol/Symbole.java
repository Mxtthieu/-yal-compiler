package yal.analyse.symbol;

import yal.analyse.TDS;

public abstract class Symbole {

    protected int dep;
    private int idRegion;
    private int idBox;

    public Symbole(){
        dep = TDS.getInstance().TailleZoneVariable();
        idRegion = TDS.getInstance().getIdRegion();
        idBox = TDS.getInstance().getIdRegion();
    }

    public int getDep() {
        return dep;
    }

    public String getType() {
        return null;
    }

    public int getIdBox() {
        return idBox;
    }

    public int getIdRegion() {
        return idRegion;
    }
}