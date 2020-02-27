package yal.analyse.symbol;

import java.util.ArrayList;

public class SymboleFonc extends Symbole {

    private int compteur;
    
    private ArrayList<String> paramType;


    public SymboleFonc() {
        super();
    }

    @Override
    public String getType() {
        return "fonc";
    }
}
