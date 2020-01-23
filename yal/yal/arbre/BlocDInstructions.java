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
    protected static String data = ".data\n\n" +
            "sautLigne: .asciiz \"\\n\"\n";
    protected static String debut = "\n.text\n"+
            "\nmain :\n";
    protected static String fin = "end :\n"+
            "   li $v0, 10\n" +
            "   syscall\n";

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
        sb.append(data);
        sb.append(debut);
        for (ArbreAbstrait aa : programme) {
            sb.append(aa.toMIPS()) ;
            sb.append("\n");
        }
        sb.append(fin);
        return sb.toString() ;
    }

}
