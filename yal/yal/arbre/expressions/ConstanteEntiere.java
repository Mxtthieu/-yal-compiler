package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {

    /**
     *
     * @param idf
     * @param n
     */
    public ConstanteEntiere(String idf, int n) {
        super(idf, n);
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


