package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.arbre.BlocDInstructions;

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

    }

    @Override
    public void verifier() {
        
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
