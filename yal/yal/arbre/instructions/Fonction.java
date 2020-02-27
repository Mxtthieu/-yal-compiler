package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeFonc;
import yal.analyse.symbol.SymboleFonc;
import yal.arbre.BlocDInstructions;
import yal.exceptions.AnalyseSemantiqueException;

public class Fonction extends Instruction{
    private String idf;
    private String label;
    private int idBloc;
    private int memoryVar;
    private BlocDInstructions bloc;

    public Fonction(BlocDInstructions b, String idf, int nbParam, int nbLignes){
        super(nbLignes);
        this.idf = idf;
        bloc = b;
        idBloc = TDS.getInstance().getIdRegion();
        memoryVar = TDS.getInstance().sizeMemoryVar();
    }

    public boolean isRetourne(){
        return bloc.isRetourne();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Definition de fonction\n");
        sb.append(label+":\n");
        sb.append("sw $ra, 0($sp)\n");
        sb.append("add $sp, $sp, -4\n");
        sb.append("sw $s7, 0($sp)\n");
        sb.append("add $sp, $sp, -4\n");
        sb.append("li $t8,"+idBloc+"\n");
        sb.append("sw $t8, 0($sp)\n");
        sb.append("add $sp, $sp, -4\n");
        sb.append("move $s7, $sp\n");
        bloc.toMIPS();
        return sb.toString();
    }

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
        TDS.getInstance().finDeBloc();
        }
    }
}
