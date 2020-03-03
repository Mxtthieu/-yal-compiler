package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Retourner extends Instruction {

    private Expression exp;
    private int idRegion;

    /**
     *
     * @param exp
     */
    public Retourner(Expression exp) {
        super(exp.getNoLigne());
        this.exp = exp;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        exp.verifier();
        if (!exp.getType().equals("entier")) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur type :\n retourne " + exp + "\n une fonction doit retourner un entier");
        }
        idRegion = TDS.getInstance().getIdRegion();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isRetourner(){
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #retour de fonction\n");
        sb.append("    #Met exp dans $v0\n");
        sb.append(exp.toMIPS() + "\n");
        if(idRegion > 0){
            sb.append("    #Deplacement dans la base\n");
            sb.append("    #lw $7, 8($sp)\n");
            sb.append("    #Depile l'id de la region\n");
            sb.append("    add $sp, $sp, 4\n\n");
            sb.append("    #Depile la chaine dynamique \n");
            sb.append("    add $sp, $sp, 4\n");
            sb.append("    #Depile l'adresse de retourn \n");
            sb.append("    add $sp, $sp, 4\n");
            sb.append("    #lw $ra, 0($sp)\n");
            sb.append("    #Enregistre la valeur calculer dans $v0\n");
            sb.append("    #sw $v0, 4($sp)\n");
            sb.append("    #jr $ra\n");
        }else{
            sb.append("    #Direction fin du programme \n");
            sb.append("    j fin\n\n");
        }
        return sb.toString();
    }

}
