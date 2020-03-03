package yal.arbre.expressions.binaire.operateur.logique;

import yal.arbre.expressions.Expression;

public class OuLogique extends Logique{

    private Expression gauche;
    private Expression droite;

    /**
     *
     * @param exp1
     * @param exp2
     */
    public OuLogique(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Ou Logique\n");
        sb.append(super.toMIPS());
        sb.append("    or $v0, $t8, $v0\n");
        return sb.toString();
    }
}
