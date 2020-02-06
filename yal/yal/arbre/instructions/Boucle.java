package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction {
    private Expression exp;
    private BlocDInstructions bInst;
    private static int count = 0;

    public Boucle(Expression e, BlocDInstructions b) {
        super(e.getNoLigne());
        exp = e;
        bInst = b;
        count++;
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
        sb.append("tantque"+ count +":\n");
        sb.append(exp.toMIPS());
        sb.append("    beqz $v0, finTantQue"+ count +"\n");
        sb.append("iteration"+ count +" :\n");
        sb.append("    j tantque"+ count + " :\n");
        sb.append("finTantQue" + count + " :\n");
        return sb.toString();
    }
}
