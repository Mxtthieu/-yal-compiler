package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeFonc;
import yal.analyse.symbol.SymboleFonc;
import yal.arbre.BlocDInstructions;
import yal.exceptions.AnalyseSemantiqueException;

public class Fonction extends Instruction {
    private String idf;
    private String label;
    private int idBloc;
    private int memoryVar;
    private int nbParam;
    private BlocDInstructions bloc;
    private BlocDInstructions bloc2;

    /**
     *
     * @param b
     * @param idf
     * @param nbParam
     * @param nbLignes
     */
    public Fonction(BlocDInstructions b, String idf, int nbParam, int nbLignes){
        super(nbLignes);
        this.idf = idf;
        bloc = b;
        this.nbParam = nbParam;
        idBloc = TDS.getInstance().getIdRegion();
        memoryVar = TDS.getInstance().TailleZoneVariable();
    }

    public Fonction(BlocDInstructions b, BlocDInstructions b2, String idf, int nbParam, int nbLignes){
        super(nbLignes);
        this.idf = idf;
        bloc = b;
        bloc2 = b2;
        this.nbParam = nbParam;
        idBloc = TDS.getInstance().getIdRegion();
        memoryVar = TDS.getInstance().sizeMemoryVar();
    }
    /**
     *
     * @return
     */
    public boolean isRetourner(){
        return bloc.isRetourne();
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #Fonction\n" + label + ":\n");
        sb.append("    #Empile l'adresse de retour\n");
        sb.append("    sw $ra, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Empilement chainage dynamique\n");
        sb.append("    sw $s7, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Empilement de l'id de la region\n");
        sb.append("    li $t8, " + idBloc + "\n");
        sb.append("    sw $t8, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Deplacement de la base\n");
        sb.append("    move $s7, $sp\n" + "\n");
        sb.append("    #Allocation des variables \n");
        sb.append("    add $sp, $sp , -" + this.memoryVar+ "\n");
        sb.append("    # initialisation de toutes les variables a 0\n") ;

        for(int dep = 0; dep < this.memoryVar; dep += 4) {
            sb.append("sw $zero, -");
            sb.append(dep);
            sb.append("($s7)\n");
        }

        sb.append("#Instruction dans la fonction\n");
        if (bloc2!=null){
            sb.append(bloc2.toMIPS());
        }
        sb.append(bloc.toMIPS());
        sb.append("\n");
        return sb.toString();
    }

    /**
     *
     */
    @Override
    public void verifier() {
        EntreeFonc e = new EntreeFonc(idf, 0);
        SymboleFonc s = (SymboleFonc) TDS.getInstance().identifier(e);
        if (s == null) {
            StringBuilder res = new StringBuilder();
            res.append("Pas de déclaration de la fonction : ");
            res.append(idf);
            res.append("()");
            throw new AnalyseSemantiqueException(getNoLigne(), res.toString());
        }
        label = s.getLabel();
        TDS.getInstance().debutDeBloc();
        bloc.verifier();

        if (!isRetourner()) {
            throw new AnalyseSemantiqueException(getNoLigne(), "retourne peut ne pas être atteint dans la fonction " + idf + "( avec " + nbParam + " parametres )");
        }

        TDS.getInstance().finDeBloc();
    }
}

