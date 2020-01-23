package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Affectation extends Instruction {

    private String var;
    private Expression exp;

    protected Affectation(int n) {
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
