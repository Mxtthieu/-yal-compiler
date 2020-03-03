package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     *
     * @param texte
     * @param n
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n);
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    /**
     *
     * @return
     */
    public String val(){
        return cste;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType(){
        return "entier";
    }



}


