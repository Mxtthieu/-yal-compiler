package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Negation extends Unaire{

    private Expression exp;

    public Negation(Expression exp) {
        super(exp);
        this.exp = exp;
    }

    @Override
    public void verifier() {
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if(this.exp.getType().equals("bool")){
            sb.append("Erreur de type : negation sur une operande boolenne");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#non logique");
        sb.append(super.toMIPS());
        sb.append("xori $v0, $v0, 1\n");
        return sb.toString();
    }

}
