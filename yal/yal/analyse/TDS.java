package yal.analyse;

import yal.analyse.entre.Entree;
import yal.analyse.symbol.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.AnalyseSyntaxiqueException;

import java.util.HashMap;

public class TDS {

    private static final TDS instance = new TDS();
    private HashMap<Entree, Symbole> tab;

    private int ligne;

    private TDS(){
        tab = new HashMap<>();
    }

    public static TDS getInstance(){
        return instance;
    }

    public void ajouter(Entree e, Symbole s, int n){
        ligne = n;
        if(tab.containsKey(e)){
            throw new AnalyseSemantiqueException(ligne,"Double déclaration : " + e.toString());
        }
        tab.put(e,s);
    }

    public Symbole identifier(Entree e){

        if(!tab.containsKey(e)){
            throw new AnalyseSemantiqueException(ligne,"Non déclaré : " + e.toString());
        }
        return tab.get(e);
    }

    public int TailleZoneVariable(){
        return -(tab.size()*4);
    }

    public int getIdRegion(){
        return 1;
    }

    public void debutDeBloc(){

    }

    public void finDeBloc(){

    }

}
