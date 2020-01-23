package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        System.out.println(cste);
        StringBuilder cst = new StringBuilder();
        cst.append("li $v0, " + toString() + "\n");
        return cst.toString();
    }

}
