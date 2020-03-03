package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;

public class Diviser extends ArithmetiqueBinaire {

    /**
     *
     * @param exp1
     * @param exp2
     */
    public Diviser(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append(super.toMIPS());
        sb.append("    #Division par 0\n");
        sb.append("    beqz $t8, alorsdiv" + compteur + "\n");
        sb.append("    div $v0, $t8, $v0\n");
        sb.append("    j findiv" + compteur + "\n\n");
        sb.append("alorsdiv" + compteur + " :\n");
        sb.append("    #Message d'erreur car l'expression droite = 0\n");
        sb.append("    li $v0, 4\n");
        sb.append("    la $a0, errdiv\n");
        sb.append("    syscall\n");
        sb.append("    j end\n\n");
        sb.append("findiv" + compteur + " :\n");
        return sb.toString();
    }

}
