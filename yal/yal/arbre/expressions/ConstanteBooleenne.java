package yal.arbre.expressions;

public class ConstanteBooleenne extends Constante {

    public ConstanteBooleenne(String texte, int n) {
        super(texte, n);
    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }

    public String val(){
        if (cste.equals("vrai")){
            return "1";
        }
        return "0";
    }

    @Override
    public String getType(){
        return "bool";
    }
}
