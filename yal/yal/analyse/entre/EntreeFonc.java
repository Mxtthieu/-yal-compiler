package yal.analyse.entre;

public class EntreeFonc extends Entree {

    private int nbParam;

    public EntreeFonc(String s, int n) {
        super(s);
        nbParam = n;
    }

    public int getNbParam(){
        return nbParam;
    }

}
