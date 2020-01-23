package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbol;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction{

    private String var;
    private Expression exp;
    private int dep;

    protected Affectation(String s, Expression e) {
        super(e.getNoLigne());
        var = s;
        exp = e;
    }

    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(var);
        Symbol s = TDS.getInstance().identifier(e);

        if (s == null){
            throw new AnalyseSemantiqueException(exp.getNoLigne(), "Aucune déclaration de "+var+".");
        }

        this.dep = s.getDep();
        //var.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
