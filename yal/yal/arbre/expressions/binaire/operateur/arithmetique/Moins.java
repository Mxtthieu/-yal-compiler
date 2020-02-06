package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.expressions.Expression;

public class Moins extends ArithmetiqueBinaire{
    public Moins(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toMIPS());
        sb.append("    sub $v0, $t8, $v0\n");
        return sb.toString();
    }
}
