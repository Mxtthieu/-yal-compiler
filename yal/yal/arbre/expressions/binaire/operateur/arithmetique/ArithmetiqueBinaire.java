package yal.arbre.expressions.binaire.operateur.arithmetique;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.binaire.Binaire;
import yal.exceptions.AnalyseSemantiqueException;

public class ArithmetiqueBinaire extends Binaire {

    /**
     *
     * @param exp1
     * @param exp2
     */
    public ArithmetiqueBinaire(Expression exp1, Expression exp2) {
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
        System.out.println("gauche : " + gauche.getClass());
        System.out.println("droite : " + droite.getClass());
        if (!gauche.getType().equals("entier") && (!droite.getType().equals("entier"))) {
            sb.append("Erreur de type : Les deux opérandes ne sont pas des entiers");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        } else if (!gauche.getType().equals("entier")) {
            sb.append("Erreur de type : L'opérande de gauche n'est pas entier");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        } else if (!droite.getType().equals("entier")) {
            sb.append("Erreur de type : L'opérande de droite n'est pas entier");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS(){
        return super.toMIPS();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType(){
        return "entier";
    }
}
