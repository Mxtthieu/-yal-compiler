package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction {
    private Expression exp;
    private BlocDInstructions bInst;

    protected Boucle(Expression e, BlocDInstructions b) {
        super(e.getNoLigne());
        exp = e;
        bInst = b;
    }

    @Override
    public void verifier() {
        if (!this.exp.getType().equals("bool")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Erreur de type: L'expression conditionnelle n'est pas booléenne");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
        bInst.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Boucle while\n");
        sb.append("tantque :\n");
        sb.append(exp.toMIPS());
        sb.append("    beqz $v0, finTantQue\n");
        sb.append("    iteration\n");
        sb.append("    j tantque\n");
        return sb.toString();
    }
}
