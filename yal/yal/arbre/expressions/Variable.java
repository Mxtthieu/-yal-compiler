package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;

public abstract class Variable extends Expression{

    private String idf;
    private String type;
    private int dep;
    public Variable(int n) {
        super(n);
    }

    @Override
    public void verifier()  {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        dep = s.getDep();
        type = s.getType();
    }

    @Override
    public String toMIPS() {
        StringBuilder cst = new StringBuilder();
        cst.append("    lw $v0, " + dep + "($s7)\n");
        return cst.toString();
    }

    @Override
    public String toString() {
        return idf;
    }
}
