package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.FabriqueNumero;

public class Lire extends Instruction{

    private String idf;
    private int dep;
    private int idRegion;

    /**
     *
     * @param s
     * @param i
     */
    public Lire(String s, int i){
        super(i);
        idf = s;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        dep = s.getDep();
        idRegion = s.getIdRegion();
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append("    #Lecture\n");
        sb.append("    li $v0, 5\n");
        sb.append("    syscall\n");
        sb.append("    #Empilement de la valeur à stocker dans la variable\n");
        sb.append("    sw $v0, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n");
        sb.append("    #Récupération de la base\n");
        sb.append("    move $t5, $s7\n");
        sb.append("    #Récupération du numéro de région\n");
        sb.append("    li $v1, " + idRegion + "\n");
        sb.append("    #Boucle\n");
        sb.append("tantquelire"+compteur+" :\n");
        sb.append("    #Numéro de la région courante\n");
        sb.append("    lw $v0, 4($t5) \n");
        sb.append("    sub $v0, $v0, $v1\n");
        sb.append("    #Si les numéros correspondent : fini\n");
        sb.append("    beqz $v0, fintantquelire"+compteur+"\n");
        sb.append("    #Sinon on remonte aux regions precedentes\n");
        sb.append("    lw $t5, 8($t5) \n");
        sb.append("    j tantquelire"+compteur+"\n");
        sb.append("fintantquelire"+compteur+" :\n\n");
        sb.append("    #Valeur lu\n");
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $v0, 0($sp)\n");
        sb.append("    sw $v0, " + dep + "($t5)\n");
        return sb.toString();
    }
}
