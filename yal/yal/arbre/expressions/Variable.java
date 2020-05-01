package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.analyse.symbol.SymboleEntier;
import yal.arbre.FabriqueNumero;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.AnalyseSyntaxiqueException;

public class Variable extends Expression {

    private String idf;
    private String type;
    private int dep;
    private int idRegion;

    /**
     *
     * @param s
     * @param n
     */
    public Variable(String s, int n) {
        super(n);
        idf = s;
    }

    /**
     *
     */
    @Override
    public void verifier()  {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        if (s != null) {
            this.dep = s.getDep();
            type = s.getType();
            idRegion = s.getIdRegion();
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
        int num = FabriqueNumero.getInstance().getNumero();
        StringBuilder cst = new StringBuilder();
        cst.append("    #On recupere la base\n");
        cst.append("    move $t5, $s7\n");
        cst.append("    #on récupere le numéro de région de la variable\n");
        cst.append("    li $v1, " + idRegion + "\n");

        cst.append("    #début du tantque\n");
        cst.append("tantquevariable_" + num + " :\n");

        cst.append("    #on prend le numéro de région courant\n");
        cst.append("    lw $v0, 4($t5)\n");
        cst.append("    sub $v0, $v0, $v1\n");

        cst.append("    #on va a la fin si les numéros correspondent\n");
        cst.append("    beqz $v0, fintantquevariable_" + num + "\n");

        cst.append("    #on essaye avec le numéro de région précédent sinon\n");
        cst.append("    lw $t5, 8($t5) \n");
        cst.append("    j tantquevariable_" + num + "\n");

        cst.append("    #sortie du tantque\n");
        cst.append("    fintantquevariable_" + num + " :\n\n");

        cst.append("    #chargement classique dans $v0\n");
        cst.append("    lw $v0, " + dep + "($t5)\n");

        return cst.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return idf;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isConstante() {
        return false;
    }
}
