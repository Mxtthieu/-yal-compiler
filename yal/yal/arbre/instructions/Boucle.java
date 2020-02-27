package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction {
    private Expression exp;
    private BlocDInstructions bInst;

    public Boucle(Expression e, BlocDInstructions b) {
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
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append("    #Boucle while\n");
        sb.append("tantque"+ compteur +":\n");
        sb.append(exp.toMIPS());
        sb.append("    beqz $v0, finTantQue"+ compteur +"\n");
        for (ArbreAbstrait e : bInst.getInst()){
            e.toMIPS();
        }
        sb.append(bInst.toMIPS());
        sb.append("    j tantque"+ compteur + "\n");
        sb.append("finTantQue" + compteur + " :\n");
        return sb.toString();
    }
}
