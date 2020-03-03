package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeFonc;
import yal.analyse.symbol.SymboleFonc;
import yal.exceptions.AnalyseSemantiqueException;

public class AppelFonction extends Expression{

    private String idf;
    private String label;
    private String type;

    /**
     *
     * @param idf
     * @param nbLignes
     */
    public AppelFonction(String idf, int nbLignes) {
        super(nbLignes);
        this.idf = idf;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeFonc e = new EntreeFonc(idf, 0);
        SymboleFonc s = (SymboleFonc) TDS.getInstance().identifier(e);
        if (s == null){
            StringBuilder sb = new StringBuilder();
            sb.append("Pas de déclaration de :");
            sb.append(idf+"()");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
        label = s.getLabel();
        type = s.getType();
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Appel de fonction\n");
        sb.append("    add $sp, $sp, -4\n");
        sb.append("    jal "+label+"\n");
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $v0, 0($sp)\n");
        return sb.toString();
    }
}
