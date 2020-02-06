package yal.analyse;

import yal.analyse.entre.Entree;
import yal.analyse.symbol.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.AnalyseSyntaxiqueException;

import java.util.HashMap;

public class TDS {

    private static TDS instance = new TDS();
    private HashMap<Entree, Symbole> tab;

    private TDS(){
        tab = new HashMap<>();
    }

    public static TDS getInstance(){
        return instance;
    }

    public void ajouter(Entree e, Symbole s){
        if(tab.containsKey(e)){
            throw new AnalyseSyntaxiqueException("Double déclaration de " + e.toString());
        }
        tab.put(e,s);
    }

    public Symbole identifier(Entree e){
        if(!tab.containsKey(e)){
            //throw new AnalyseSemantiqueException("Non déclaré");
        }
        return tab.get(e);
    }

    public int TailleZoneVariable(){
        return -(tab.size()*4);
    }
}
