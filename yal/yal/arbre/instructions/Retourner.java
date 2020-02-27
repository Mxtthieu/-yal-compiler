package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Retourner extends Instruction {

    private Expression exp;
    private int idRegion;

    protected Retourner(Expression exp) {
        super(exp.getNoLigne());
        this.exp = exp;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (exp.getType().equals("entier")) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur type :\n return" + exp + "\n une fonction doit retourner un entier");
        }
        idRegion = TDS.getInstance().getIdRegion();

    }
    @Override
    public boolean isRetourner(){
        return true;
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
