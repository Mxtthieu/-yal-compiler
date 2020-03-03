package yal.arbre;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;

    /**
     *
     * @param n
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }

    /**
     *
     * @return
     */
    public int getNoLigne() {
            return noLigne ;
    }

    /**
     *
     */
    public abstract void verifier();

    /**
     *
     * @return
     */
    public abstract String toMIPS();

}
