package yal.arbre.expressions.binaire.operateur.logique;

import yal.arbre.expressions.Expression;

public class EtLogique extends Logique {

    public EtLogique(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Et logique\n");
        sb.append(super.toMIPS());
        sb.append("    and $v0, $t8, $v0\n");
        return sb.toString();
    }

}
