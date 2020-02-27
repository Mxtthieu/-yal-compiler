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
    private int idRegion;
    private int idBox;

    private TDS(){
        tab = new HashMap<>();
        idRegion = -1;
        idBox = -1;
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
        return idRegion;
    }

    public void debutDeBloc(){
        idRegion++;
        idBox++;
    }

    public void finDeBloc(){
        idBox--;
    }

    public int sizeMemoryVar(){
        return tab.size() * 4;
    }

    public int getIdBox(){
        return idBox;
    }



}
