package yal.analyse;

import yal.analyse.entre.Entree;
import yal.analyse.symbol.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.HashMap;

public class TDS {

    private static final TDS instance = new TDS();
    private HashMap<Entree, Symbole> tab;

    private int ligne;
    private int idRegion;
    private int idBox;
    private Bloc main;
    private Bloc cur;
    private boolean synt;

    private TDS(){
        tab = new HashMap<>();
        idRegion = -1;
        idBox = -1;
        main = null;
        cur = null;
        synt = true;
    }

    public static TDS getInstance(){
        return instance;
    }

    public void setUp(){
        idRegion = -1;
        idBox = -1;
        synt = false;
    }

    public void ajouter(Entree e, Symbole s, int n){
        cur.ajouter(e, s, n);
    }

    public Symbole identifier(Entree e){
        return cur.identifier(e);
    }

    public int TailleZoneVariable(){
        return cur.tailleTableVariable();
    }

    public int tailleTableParam(){
        return cur.tailleTableParam();
    }

    public int getIdRegion(){
        return cur.getIdRegion();
    }

    public void debutDeBloc(){
        idRegion++;
        idBox++;
        if(synt) {
            if (idRegion != 0) {
                Bloc b = new Bloc(idRegion, cur);
                cur.ajouterSuivant(b);
                cur = b;
            } else {
                Bloc b = new Bloc(idRegion);
                main = b;
                cur = b;
            }
        } else {
            if (idRegion != 0){
                Bloc b = cur.recupSuivant(idRegion);
                cur = b;
            } else {
                cur = main;
            }
        }
    }

    public void finDeBloc(){
        Bloc b = cur.getBlocPrecedent();
        cur = b;
        idBox--;
    }

    public int sizeMemoryVar(){
        return tab.size() * 4;
    }

    public int getIdBox(){
        return idBox;
    }

    public int idPrec() {
        Bloc b = cur.getBlocPrecedent();
        if(b != null){
            return b.getIdRegion();
        }
        return -1;
    }

    public int varCount(){
        return cur.varCount();
    }

    public int parCount(){
        return cur.parCount();
    }
}
