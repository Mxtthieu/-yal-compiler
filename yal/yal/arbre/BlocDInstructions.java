package yal.arbre;

import yal.arbre.ArbreAbstrait;
import yal.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() {
        for (ArbreAbstrait aa : programme) {
            aa.verifier() ;
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (ArbreAbstrait aa : programme) {
            sb.append(aa.toMIPS()) ;
            sb.append("\n");
        }
        return sb.toString() ;
    }

}
