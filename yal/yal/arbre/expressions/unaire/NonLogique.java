package yal.arbre.expressions.unaire;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class NonLogique extends Unaire{

    private Expression exp;

    /**
     *
     * @param exp
     */
    public NonLogique(Expression exp) {
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
        if(this.exp.getType().equals("entier")){
            sb.append("Erreur de type : Non logique sur un entier");
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
        sb.append("    # Non Logique\n");
        sb.append(super.toMIPS());
        sb.append("    xori $v0, $v0, 1\n");
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return "bool";
    }
}
