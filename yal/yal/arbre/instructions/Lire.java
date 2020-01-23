package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;

public class Lire extends Instruction{

    private String idf;
    private int dep;

    public Lire(String s, int i){
        super(i);
        idf = s;
    }

    @Override
    public void verifier() {
        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);
        dep = s.getDep();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("     #Lecture d'un entier\n");
        sb.append("     li $v0, 5 # $v0 <- 5 : Code lecture entier \n");
        sb.append("     syscall\n");
        sb.append("     #Affectation");
        sb.append("     sw $v0, "+dep+"($s7)\n\n");
        return sb.toString();
    }
}
