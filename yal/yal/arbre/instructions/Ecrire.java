package yal.arbre.instructions;

import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    /**
     *
     * @param e
     * @param n
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    /**
     *
     */
    @Override
    public void verifier() {
        exp.verifier();

    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder res = new StringBuilder();
        if (exp.getType().equals("bool")) {
            res.append("    #Ecrire une expression booléenne\n");
            res.append(exp.toMIPS());
            res.append("    beqz $v0, alorsbool"+ compteur +"\n");
            res.append("    la $a0, vrai\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, vrai\n");
            res.append("    syscall\n\n");
            res.append("    j finbool"+ compteur +"\n");
            res.append("alorsbool"+ compteur +" :\n");
            res.append("    la $a0, faux\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, faux\n");
            res.append("    syscall\n\n");
            res.append("finbool"+ compteur +" :\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, sautLigne\n");
            res.append("    syscall\n\n");
        } else {
            res.append("    #Ecrire une expression entière\n");
            res.append(exp.toMIPS());
            res.append("    move $a0, $v0\n");
            res.append("    li $v0, 1 # $v0 <- 1 : Code du print entier\n");
            res.append("    syscall\n\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, sautLigne\n");
            res.append("    syscall\n\n");
        }
        return res.toString();
    }

}
