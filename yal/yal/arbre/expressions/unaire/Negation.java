package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Negation extends Unaire{

    private Expression exp;

    /**
     *
     * @param exp
     */
    public Negation(Expression exp) {
        super(exp);
        this.exp = exp;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if(this.exp.getType().equals("bool")){
            sb.append("Erreur de type : Négation sur une opérande booléenne");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    # Negation\n");
        sb.append(super.toMIPS());
        sb.append("    neg $v0, $v0\n");
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return "entier";
    }

    @Override
    public boolean isConstante() {
        return false;
    }
}
