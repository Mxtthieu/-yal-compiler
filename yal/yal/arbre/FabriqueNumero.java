package yal.arbre;

public class FabriqueNumero {
    private static FabriqueNumero instance = new FabriqueNumero();
    private int num;

    /**
     * Constructeur de FabriqueNumero
     */
    private FabriqueNumero() {
        num = 0;
    }

    /**
     * Retourne l'instance FabriqueNumero
     *
     * @return FabriqueNumero
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Recupere et incremente le compteur d'étape
     *
     * @return numero + 1
     */
    public int getNumero() {
        return num++;
    }

    /**
     * Remet les compteurs d'étape et de sémaphore à 0
     */
    public void reset() {
        num = 0;
    }

}