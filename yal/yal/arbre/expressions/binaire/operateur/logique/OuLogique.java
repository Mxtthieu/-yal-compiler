package yal.arbre.expressions.binaire.operateur.logique;

import yal.arbre.expressions.Expression;

public class OuLogique extends Logique{

    private Expression gauche;
    private Expression droite;

    protected OuLogique(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;
    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Et logique\n");
        sb.append(super.toMIPS());
        sb.append("    or $v0, $t8, $v0\n");
        return sb.toString();
    }
}
