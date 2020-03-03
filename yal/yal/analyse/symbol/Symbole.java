package yal.analyse.symbol;

import yal.analyse.TDS;

public abstract class Symbole {

    protected int dep;
    private int idRegion;
    private int idBox;

    /**
     *
     */
    public Symbole(){
        dep = TDS.getInstance().TailleZoneVariable();
        idRegion = TDS.getInstance().getIdRegion();
        idBox = TDS.getInstance().getIdBox();
    }

    /**
     *
     * @return
     */
    public int getDep() {
        return dep;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return null;
    }

    /**
     *
     * @return
     */
    public int getIdBox() {
        return idBox;
    }

    /**
     *
     * @return
     */
    public int getIdRegion() {
        return idRegion;
    }

    /**
     *
     * @return
     */
    public abstract boolean isVar();

    /**
     *
     * @return
     */
    public int getSpace() {
        return 0;
    }

    /**
     *
     * @return
     */
    public abstract boolean isParam();
}