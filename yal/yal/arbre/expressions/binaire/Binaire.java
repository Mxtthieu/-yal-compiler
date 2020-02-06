package yal.arbre.expressions.binaire;

import yal.arbre.expressions.Expression;

public abstract class Binaire extends Expression {

    private Expression exp1;
    private Expression exp2;

    protected Binaire(int n) {
        super(n);
    }

    @Override
    public void verifier() {
        
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
