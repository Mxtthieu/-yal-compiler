package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.FabriqueNumero;
import yal.exceptions.AnalyseSemantiqueException;

public class VariableTableau extends Expression {
    private String idf;
    private Expression indice;
    private int dep;
    private int idRegion;
    private int cpt;

    public VariableTableau(String nidf, Expression indice, int n) {
        super(n);
        this.idf = nidf;
        this.indice = indice;
        this.cpt = FabriqueNumero.getInstance().getNumero();
    }


    public String getIdf() {
        return idf;
    }

    public int getDep() {
        return dep;
    }

    @Override
    public void verifier() {
        indice.verifier();

        if(!(indice.getType().equals("entier"))) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur type :\t indice " + idf + " \n\t" + indice + " n'est pas entier");
        }

        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);

        if(s == null){
            throw new AnalyseSemantiqueException(getNoLigne(), " " + idf +  "n'a pas été déclarée ");
        }

        dep = s.getDep();
        idRegion = s.getIdRegion();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Recuperation de la base\n");
        sb.append("    move $t5, $s7\n");
        sb.append("    #Recuperation du numero de region tab\n");
        sb.append("    li $v1, " + idRegion + "\n" );
        sb.append("tantquevariabletableau_" + cpt + " :\n");
        sb.append("    #Recuperation du numero de region\n");
        sb.append("    lw $v0, 4($t5)\n");
        sb.append("    sub $v0, $v0, $v1\n");
        sb.append("    #Si les numeros sont identiques : fin\n");
        sb.append("    beqz $v0, fintantquevariabletableau_" + cpt + "\n");
        sb.append("    #Sinon on refait le meme test avec la region precedente\n");
        sb.append("    lw $t5, 8($t5) \n");
        sb.append("    j tantquevariabletableau_" + cpt + "\n");
        sb.append("fintantquevariabletableau_" + cpt + " :\n\n");
        sb.append("    #Adresse du tableau\n");
        sb.append("    lw $t4, " + dep + "($t5)\n");
        sb.append(indice.toMIPS());
        sb.append("    #Gestion de l'erreur d'indice\n");
        sb.append("    bltz $v0, erreurAccesTab\n");
        sb.append("    li $t3, -4\n");
        sb.append("    mult $v0, $t3\n");
        sb.append("    mflo $t2\n");
        sb.append("    add $t2, $t2, -4\n");
        sb.append("    add $t4, $t4, $t2\n");
        sb.append("    lw $v0, 0($t4)\n");
        return sb.toString();
    }

    @Override
    public String getType() {
        return "entier";
    }

    @Override
    public boolean isConstante() {
        return false;
    }
}