package yal.arbre.expressions.binaire.operateur.comparaison;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class Inferieur extends Binaire {

    private Expression gauche;
    private Expression droite;

    public Inferieur(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;

    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if (gauche.getType().equals("bool")){
            sb.append("Erreur de type : les deux operandes sont de type cooleenne");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Inferieure\n");
        sb.append(super.toMIPS());
        sb.append("    slt $v0, $t8, $v0\n");
        return sb.toString();
    }
}
