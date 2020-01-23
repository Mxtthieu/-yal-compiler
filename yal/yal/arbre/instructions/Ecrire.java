package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("#Ecrire un entier \n");
        res.append(exp.toMIPS());
        res.append("move $a0, $v0\n");
        res.append("li $v0, 1 # $v0 <- 1 : Code du print entier\n");
        res.append("syscall");
        return res.toString();
    }

}
