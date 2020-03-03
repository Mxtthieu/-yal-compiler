package yal.analyse.entre;

public class EntreeFonc extends Entree {

    private int nbParam;

    /**
     *
     * @param s
     * @param n
     */
    public EntreeFonc(String s, int n) {
        super(s);
        nbParam = n;
    }

    /**
     *
     * @return
     */
    public int getNbParam(){
        return nbParam;
    }

}
