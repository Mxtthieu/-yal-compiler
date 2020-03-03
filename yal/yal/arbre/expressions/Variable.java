package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.analyse.symbol.SymboleEntier;

public class Variable extends Expression {

    private String idf;
    private String type;
    private int dep;

    /**
     *
     * @param s
     * @param n
     */
    public Variable(String s, int n) {
        super(n);
        idf = s;
    }

    /**
     *
     */
    @Override
    public void verifier()  {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        dep = s.getDep();
        type = s.getType();
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder cst = new StringBuilder();
        cst.append("    lw $v0, " + dep + "($s7)\n");
        return cst.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return idf;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return type;
    }
}
