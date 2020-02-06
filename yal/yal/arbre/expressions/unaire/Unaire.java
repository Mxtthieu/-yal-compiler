package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;

public abstract class Unaire extends Expression {

    protected Expression exp;

    public Unaire(Expression exp){
        super(exp.getNoLigne());
        this.exp = exp;
    }


    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toMIPS());
        return sb.toString();
    }
}
