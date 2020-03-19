package yal.analyse.symbol;

import yal.arbre.FabriqueNumero;

import java.util.ArrayList;

public class SymboleFonc extends Symbole {

    private String label;
    private ArrayList<String> paramType;

    /**
     *
     */
    public SymboleFonc() {
        super();
        label = "fonc"+ FabriqueNumero.getInstance().getNumero();
        paramType = new ArrayList<>();
        this.dep = 0;
    }

    public SymboleFonc( ArrayList<String> types) {
        super();
        this.dep = 0;
        label = "fonc"+ FabriqueNumero.getInstance().getNumero();
        this.paramType = types;
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

    /**
     *
     * @return
     */
    public String getLabel(){
        return this.label;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getParamType() {
        return this.paramType;
    }
}
