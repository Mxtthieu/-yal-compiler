package yal.analyse.symbol;

import yal.arbre.FabriqueNumero;

import java.util.ArrayList;

public class SymboleFonc extends Symbole {

    private String label;
    private ArrayList<String> paramType;

    public SymboleFonc() {
        super();
        label = "fonc"+ FabriqueNumero.getInstance().getNumero();
        paramType = new ArrayList<>();
        this.dep = 0;
    }

    @Override
    public String getType() {
        return "fonc";
    }

    public String getLabel(){
        return label;
    }

    public ArrayList<String> getParamType() {
        return paramType;
    }
}
