package yal.arbre.expressions.binaire;

import yal.arbre.expressions.Expression;

public abstract class Binaire extends Expression {

    public Expression gauche;
    public Expression droite;

    public Binaire(Expression exp1, Expression exp2) {
        super(exp1.getNoLigne());
        this.gauche = exp1;
        this.droite = exp2;
    }

    @Override
    public void verifier() {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    # Partie de gauche \n");
        sb.append(gauche.toMIPS());
        sb.append("\n");
        sb.append("    # Empilement de la partie de gauche");
        sb.append("    sw $v0, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n\n");
        sb.append("    # Partie de droite");
        sb.append(droite.toMIPS());
        sb.append("    # Depilement de la partie de gauche");
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $t8, 0($sp)\n\n");
        return sb.toString();
    }
}
