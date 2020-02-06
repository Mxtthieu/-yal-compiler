package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.expressions.Expression;

public class Diviser extends ArithmetiqueBinaire {

    protected Diviser(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String getType() {
        return "entier";
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        int hash = hashCode();
        sb.append(super.toMIPS());
        sb.append("    #Division par 0\n");
        sb.append("    beqz $v0, alors" + hash + "\n");
        sb.append("    div $v0, $t8, $v0\n");
        sb.append("    j fin" + hash + "\n");
        sb.append("alors " + hash + ":\n");
        sb.append("    #Message d'erreur car l'expression droite = 0\n");
        sb.append("    li $v0, 4\n");
        sb.append("    la $a0, errdiv\n");
        sb.append("    syscall\n");
        sb.append("    j end\n");
        sb.append("fin " + hash + " :\n");
        return sb.toString();
    }

}
