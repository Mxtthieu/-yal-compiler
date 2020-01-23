package yal.arbre.expressions;

public abstract class Variable extends Expression{

    private String idf;
    
    protected Variable(int n) {
        super(n);
    }

    @Override
    public void verifier() {}

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String toString() {
        return idf;
    }
}
