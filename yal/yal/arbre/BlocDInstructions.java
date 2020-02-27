package yal.arbre;

import yal.analyse.TDS;
import yal.arbre.ArbreAbstrait;
import yal.arbre.instructions.Instruction;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    protected ArrayList<Instruction> inst;

    public BlocDInstructions(int n) {
        super(n);
        inst = new ArrayList<>() ;
    }

    public void ajouter(Instruction a) {
        inst.add(a) ;
    }

    @Override
    public String toString() {
        return inst.toString() ;
    }

    @Override
    public void verifier() {
        for (Instruction i : inst) {
            i.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (Instruction i : inst) {
            sb.append(i.toMIPS()) ;
            sb.append("\n");
        }
        return sb.toString() ;

    }

    public boolean isRetourne(){
        boolean res = false;
        for(Instruction i : inst){
            res = i.isRetourne();
        }
        return res;
    }


    public ArrayList<Instruction> getInst(){
        return inst;
    }
}
