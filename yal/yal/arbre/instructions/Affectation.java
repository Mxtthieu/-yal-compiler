package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction{

    private String idf;
    private Expression exp;
    private int dep;

    /**
     *
     * @param s
     * @param e
     */
    public Affectation(String s, Expression e) {
        super(e.getNoLigne());
        idf = s;
        exp = e;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        this.dep = s.getDep();
        exp.verifier();
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Affectation de "+ exp.toString() + " à "+ idf + "\n");
        sb.append(exp.toMIPS());
        sb.append("    sw $v0, "+dep+"($s7)\n\n");
        return sb.toString();
    }
    
}
