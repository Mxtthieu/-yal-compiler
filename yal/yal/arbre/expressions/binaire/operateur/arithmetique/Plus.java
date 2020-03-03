package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.expressions.Expression;

public class Plus extends ArithmetiqueBinaire {
    /**
     *
     * @param exp1
     * @param exp2
     */
    public Plus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toMIPS());
        sb.append("    add $v0, $v0, $t8\n");
        return sb.toString();
    }
}
