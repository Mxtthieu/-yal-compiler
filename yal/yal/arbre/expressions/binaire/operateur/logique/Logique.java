package yal.arbre.expressions.binaire.operateur.logique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class Logique extends Binaire {

    private Expression gauche;
    private Expression droite;

    public Logique(Expression exp1, Expression exp2) {
        super(exp1, exp2);
        gauche = exp1;
        droite = exp2;

    }

    @Override
    public void verifier()throws AnalyseSemantiqueException{
        StringBuilder sb = new StringBuilder();
        if(gauche.getType().equals("entier") && droite.getType().equals("entier")) {
            sb.append("Erreur de type : l'operande de gauche et droite sont de type entier ");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());

        }else if(gauche.getType().equals("entier")) {
            sb.append("Erreur de type : l'operande de gauche est de type entier ");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());

        }else if(droite.getType().equals("entier")){
            sb.append("Erreur de type : l'operande de droite est de type antier ");
            throw new AnalyseSemantiqueException(getNoLigne(),sb.toString());
        }

    }

    @Override
    public String toMIPS() {
        return super.toMIPS();
    }


    @Override
    public String getType() {
        return "bool";
    }
}
