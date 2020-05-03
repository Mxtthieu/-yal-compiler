package yal.arbre.expressions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeFonc;
import yal.analyse.symbol.SymboleFonc;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

public class AppelFonction extends Expression{

    private String idf;
    private String label;
    private String type;
    private int nbParam;
    private ArrayList<Expression> param;

    /**
     *
     * @param idf
     * @param nbLignes
     */
    public AppelFonction(String idf, int nbLignes) {
        super(nbLignes);
        this.idf = idf;
        this.nbParam = 0;
        this.param = new ArrayList<>();
    }

    public AppelFonction(String id, int noLigne, ArrayList<Expression> parameters){
        super(noLigne);
        idf = id;
        this.nbParam = parameters.size();
        this.param = parameters;
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

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeFonc e = new EntreeFonc(idf, 0);
        SymboleFonc s = (SymboleFonc) TDS.getInstance().identifier(e);
        if (s == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("fonction : ");
            sb.append(idf + "() non declarer");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
        label = s.getLabel();
        type = s.getType();
        for(int i = 0; i<param.size(); i++) {
            param.get(i).verifier();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #gestion parametres \n");
        sb.append("    add $sp, $sp,-"+this.nbParam*4+"\n");

        for(int i = 0; i < nbParam; i++){
            Expression param = this.param.get(i);
            sb.append(param.toMIPS() + "sw $v0, " + i*4 + "($sp)\n");
        }

        sb.append("    #Appel de fonction\n");
        sb.append("    #Allocation de la valeur retour\n");
        sb.append("    add $sp, $sp, -4\n\n");
        sb.append("    #Jump" + idf + "\n");
        sb.append("    jal " + label + "\n\n");
        sb.append("    #Depile dans $v0\n" );
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $v0, 0($sp)\n\n");
        sb.append("    #Dépiler les params\n");
        sb.append("    add $sp, $sp, " + nbParam *4 + "\n\n");

        return sb.toString();
    }
}
