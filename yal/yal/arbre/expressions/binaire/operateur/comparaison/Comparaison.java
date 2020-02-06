package yal.arbre.expressions.binaire.operateur.comparaison;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class Comparaison extends Binaire {

    private Expression gauche;
    private Expression droite;

    protected Comparaison (Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;

    }

    @Override
    public void verifier()throws AnalyseSemantiqueException{
        StringBuilder sb = new StringBuilder();
        if (!gauche.getType().equals(droite.getType())){
            sb.append("Erreur de type : l'operande ne sont pas de même type ");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }
    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }
}
