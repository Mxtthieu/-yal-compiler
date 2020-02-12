package yal.arbre.expressions.binaire.operateur.comparaison;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class Comparaison extends Binaire {

    public Comparaison (Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public void verifier()throws AnalyseSemantiqueException{
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if (!gauche.getType().equals(droite.getType())){
            sb.append("Erreur de type : Les opérandes ne sont pas de même type ");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }
    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

    @Override
    public String getType() {
        return "bool";
    }
}
