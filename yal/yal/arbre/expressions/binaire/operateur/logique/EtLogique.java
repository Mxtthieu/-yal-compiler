package yal.arbre.expressions.binaire.operateur.logique;

import yal.arbre.expressions.Expression;

public class EtLogique extends Logique {

    /**
     *
     * @param exp1
     * @param exp2
     */
    public EtLogique(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Et Logique\n");
        sb.append(super.toMIPS());
        sb.append("    and $v0, $t8, $v0\n");
        return sb.toString();
    }

}
