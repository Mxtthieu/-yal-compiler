package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    public Instruction(int n) {
        super(n);
    }

    public boolean isRetourner(){
        return false;
    }

}
