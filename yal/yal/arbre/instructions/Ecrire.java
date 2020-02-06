package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;
    private static int count = 0;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
        count++;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        if (exp.getType().equals("entier")) {
            res.append("    #Ecrire une expression entière\n");
            res.append(exp.toMIPS());
            res.append("    beqz $v0, alorsbool"+ count +"\n");
            res.append("    la $a0, vrai\n");
            res.append("    j finbool"+ count +"\n");
            res.append("alorsbool"+ count +" :\n");
            res.append("    la $a0, faux\n");
            res.append("finbool"+ count +" :\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, sautLigne\n");
            res.append("    syscall\n");
        } else {
            res.append("    #Ecrire une expression booleenne\n");
            res.append(exp.toMIPS());
            res.append("    move $a0, $v0\n");
            res.append("    li $v0, 1 # $v0 <- 1 : Code du print entier\n");
            res.append("    syscall\n\n");
            res.append("    #Affichage du saut de ligne\n");
            res.append("    li $v0, 4 # $v0 <- 4 : Code du print str\n");
            res.append("    la $a0, sautLigne\n");
            res.append("    syscall\n");
        }
        return res.toString();
    }

}
