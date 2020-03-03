package yal.arbre.expressions.binaire.operateur.comparaison;

import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Superieur extends Comparaison {

    /**
     *
     * @param exp1
     * @param exp2
     */
    public Superieur (Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     *
     * @throws AnalyseSemantiqueException
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if (gauche.getType().equals("bool")){
            sb.append("Erreur de type : les deux operandes sont de type booleenne");
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
        sb.append("    # Superieur\n");
        sb.append(super.toMIPS());
        sb.append("    sgt $v0, $t8, $v0\n");
        return sb.toString();
    }
    
}
