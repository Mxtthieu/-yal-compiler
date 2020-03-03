package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    /**
     *
     * @param n
     */
    public Instruction(int n) {
        super(n);
    }

    /**
     *
     * @return
     */
    public boolean isRetourner(){
        return false;
    }

}
