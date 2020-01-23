package yal.analyse;

import yal.analyse.entre.Entree;
import yal.analyse.symbol.Symbol;
import yal.exceptions.AnalyseSyntaxiqueException;

import java.util.HashMap;

public class TDS {

    private static TDS instance = new TDS();
    private HashMap<Entree, Symbol> tab;

    private TDS(){
        tab = new HashMap<>();
    }

    public static TDS getInstance(){
        return instance;
    }

    public void ajouter(Entree e, Symbol s){
        if(tab.containsKey(e)){
            throw new AnalyseSyntaxiqueException("Double déclaration");
        }
        tab.put(e,s);
    }

    public Symbol identifier(Entree e){
        return tab.get(e);
    }

    public int TailleZoneVariable(){
        return tab.size()*4;
    }
}
