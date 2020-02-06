package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class ArithmetiqueBinaire extends Binaire {
    protected Expression gauche;
    protected Expression droite;

    protected ArithmetiqueBinaire(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        super.verifier();
        StringBuilder sb = new StringBuilder();
        if (!gauche.getType().equals("entier") || (!droite.getType().equals("entier"))){
            sb.append("Erreur de type : Les deux opérandes ne sont pas des entiers");
        } else if (!gauche.getType().equals("entier")) {
            sb.append("Erreur de type : L'opérande de gauche n'est pas entier");
        } else if (!droite.getType().equals("entier")) {
            sb.append("Erreur de type : L'opérande de droite n'est pas entier");
        }
        throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());

    }

    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

}
