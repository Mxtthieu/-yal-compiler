package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.FabriqueNumero;
import yal.exceptions.AnalyseSemantiqueException;

public class Longueur extends Expression {

    private String idf;
    private int dep;
    private int idRegion;

    /**
     *
     * @param idf
     * @param n
     */
    public Longueur(String idf, int n) {
        super(n);
        this.idf = idf;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return "entier";
    }

    @Override
    public boolean isConstante() {
        return true;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);

        if(s == null){
            StringBuilder sb = new StringBuilder();
            sb.append("La variable " + idf + "n'a pas etait declarer'");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }

        if(!(s.getType().equals("tab"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("La variable " + idf + " n'est pas un tableau");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
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
        StringBuilder mips = new StringBuilder();
        mips.append("    #Recuperation de la base\n");
        mips.append("    move $t5, $s7\n");
        mips.append("    #Numéro de région du tableau\n");
        mips.append("    li $v1, " + idRegion + "\n");
        mips.append("tantquelongueur_" + compteur + " :\n");
        mips.append("    #Numéro de région actuel\n");
        mips.append("    lw $v0, 4($t5)\n");
        //mips.append("    sub $v0, $v0, $v1\n");
        mips.append("    #Si les numéros correspondent : fin\n");
        //mips.append("    beqz $v0, fintantquelongueur_" + compteur + "\n");
        mips.append("    beq $v0, $v1, fintantquelongueur_" + compteur + "\n");
        mips.append("    #Sinon on essaye avec le numéro de région précédent\n");
        mips.append("    lw $t5, 8($t5) \n");
        mips.append("    j tantquelongueur_" + compteur + "\n");
        mips.append("fintantquelongueur_" + compteur + " :\n\n");
        mips.append("    #L'adresse du tableau dans $t4\n");
        mips.append("    lw $t4, " + dep + "($t5)\n");
        mips.append("    #Chargement de la longueur dans v0\n");
        mips.append("    lw $v0, 0($t4)\n");
        return mips.toString();

    }
}