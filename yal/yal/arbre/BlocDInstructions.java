package yal.arbre;

import yal.arbre.instructions.Instruction;
import java.util.ArrayList;

public class BlocDInstructions extends ArbreAbstrait {
    protected ArrayList<Instruction> inst;

    /**
     *
     * @param n
     */
    public BlocDInstructions(int n) {
        super(n);
        inst = new ArrayList<>() ;
    }

    /**
     *
     * @param a
     */
    public void ajouter(Instruction a) {
        inst.add(a) ;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return inst.toString() ;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        for (Instruction i : inst) {
            i.verifier();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (Instruction i : inst) {
            sb.append(i.toMIPS()) ;
            sb.append("\n");
        }
        return sb.toString() ;

    }

    /**
     *
     * @return
     */
    public boolean isRetourne(){
        boolean res = false;
        for(Instruction i : inst){
            res = i.isRetourner();
        }
        return res;
    }

    /**
     *
     * @return
     */
    public ArrayList<Instruction> getInst(){
        return inst;
    }
}
