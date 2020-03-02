package yal.analyse;

import yal.analyse.entre.Entree;
import yal.analyse.symbol.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Bloc {

    private HashMap<Entree, Symbole> tab;

    private int idRegion;

    private Bloc blocPrecedent;
    private HashMap<Integer, Bloc> blocSuivant;

    public Bloc(int idRegion) {

        this.idRegion = idRegion;
        blocPrecedent = null;

        blocSuivant = new HashMap<>();
        tab = new HashMap<>();

    }

    public Bloc(int idRegion, Bloc b) {

        this.idRegion = idRegion;
        blocPrecedent = b;

        blocSuivant = new HashMap<>();
        tab = new HashMap<>();

    }


    public void ajouter(Entree e, Symbole s, int noLigne){

        if(tab.containsKey(e)){
            throw new AnalyseSemantiqueException(noLigne,"double déclaration");
        }
        tab.put(e,s);
    }


    public Symbole identifier(Entree e){

        Symbole temp = tab.get(e);

        if(temp == null) {
            if(blocPrecedent != null) {
                temp = blocPrecedent.identifier(e);
            }
        }

        return temp;
    }

    public void ajouterSuivant(Bloc s) {

        Bloc b = blocSuivant.put(s.getIdRegion(), s);

    }

    public Bloc recupSuivant(int idRegion) {
        return blocSuivant.get(idRegion);

    }


    public int tailleTableVariable() {
        int temp = 0;
        for(Map.Entry<Entree, Symbole> map : tab.entrySet() ) {
            Symbole s = map.getValue();
            if(s.isVar()) {
                temp += s.getSpace();
            }
        }
        return temp;
    }

    public int tailleTableParam() {
        int temp = 0;

        for(Map.Entry<Entree, Symbole> map : tab.entrySet() ) {
            Symbole s = map.getValue();

            if(s.isParam()) {

                temp += s.getSpace();
            }
        }
        return temp;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public Bloc getBlocPrecedent() {
        return blocPrecedent;
    }

    @Override
    public String toString() {
        return tab.toString();
    }

}