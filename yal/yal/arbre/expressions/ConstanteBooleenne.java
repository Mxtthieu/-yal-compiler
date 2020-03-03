package yal.arbre.expressions;

public class ConstanteBooleenne extends Constante {

    /**
     *
     * @param texte
     * @param n
     */
    public ConstanteBooleenne(String texte, int n) {
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
        if (cste.equals("vrai")){
            return "1";
        }
        return "0";
    }

    /**
     *
     * @return
     */
    @Override
    public String getType(){
        return "bool";
    }
}
