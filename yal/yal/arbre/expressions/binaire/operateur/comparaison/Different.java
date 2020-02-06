package yal.arbre.expressions.binaire.operateur.comparaison;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class Different extends Comparaison {

    private Expression gauche;
    private Expression droite;

    protected Different(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;

    }


    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Diffrence\n");
        sb.append(super.toMIPS());
        sb.append("    sne $v0, $t8, $v0\n");
        return sb.toString();
    }

}
