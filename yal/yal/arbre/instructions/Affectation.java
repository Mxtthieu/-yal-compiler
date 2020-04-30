package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.AnalyseSyntaxiqueException;

public class Affectation extends Instruction{

    private String idf;
    private Expression exp;
    private int dep;
    private int idRegion;

    /**
     *
     * @param s
     * @param e
     */
    public Affectation(String s, Expression e) {
        super(e.getNoLigne());
        idf = s;
        exp = e;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        if (s != null) {
            this.dep = s.getDep();
            exp.verifier();
            this.idRegion = s.getIdRegion();
        } else {
            throw new AnalyseSyntaxiqueException("Variable "+idf+" non définie");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append("    #Affectation de "+ exp.toString() + " à "+ idf + "\n");
        sb.append(exp.toMIPS());
        sb.append("    #on empile la valeur qu'on veut mettre dans la variable\n");
        sb.append("    sw $v0, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n");

        sb.append("    #On recupere la base\n");
        sb.append("    move $t5, $s7\n");

        sb.append("    #on recupere le numéro de région\n");
        sb.append("    li $v1, " + idRegion + "\n");

        sb.append("tantqueaffect_" + compteur + " :\n");

        sb.append("    #on recupere le numéro de région courant\n");
        sb.append("    lw $v0, 4($t5) \n");
        sb.append("    sub $v0, $v0, $v1\n");

        sb.append("    #on va a la fin si les numéros correspondent\n");
        sb.append("    beqz $v0, fintantqueaffect_" + compteur + "\n");

        sb.append("    #on essaye avec le numéro de région précédent sinon\n");
        sb.append("    lw $t5, 8($t5) \n");
        sb.append("    j tantqueaffect_" + compteur + "\n" );

        sb.append("    #sortie du tantque\n");
        sb.append("fintantqueaffect_" + compteur + " :\n\n");

        sb.append("    #on dépile la valeur qu'on veut mettre dans la variable\n");
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $v0, 0($sp)\n");

        sb.append("    sw $v0, " + dep + "($t5)\n");
        return sb.toString();
    }
    
}
