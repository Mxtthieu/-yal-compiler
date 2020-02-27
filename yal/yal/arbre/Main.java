package yal.arbre;

import yal.analyse.TDS;
import yal.arbre.instructions.Instruction;

import java.util.ArrayList;

public class Main extends ArbreAbstrait {
    protected ArrayList<ArbreAbstrait> programme ;
    protected static String data = ".data\n\n" +
            "sautLigne: .asciiz \"\\n\"\n" +
            "vrai :     .asciiz \"vrai\"\n" +
            "faux :     .asciiz \"faux\"\n" +
            "errdiv :   .asciiz \"Erreur : Division par zero\"\n";
    protected static String debut = "\n.text\n"+
            "\nmain :\n";
    protected static String fin = "end :\n"+
            "    li $v0, 10\n" +
            "    syscall\n";
    private int taille;
    private BlocDInstructions bloc;

    public Main(BlocDInstructions b, int i) {
        super(i);
        bloc = b;
    }

    public Main(BlocDInstructions dec, BlocDInstructions inst, int i) {
        super(i);
        bloc = inst;
    }


    @Override
    public void verifier(){
        TDS.getInstance().debutDeBloc();
        taille = TDS.getInstance().TailleZoneVariable();
        bloc.verifier();
        TDS.getInstance().finDeBloc();
    }

    @Override
    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        sb.append(debut);
        sb.append("    #Bloc\n");
        sb.append("    li $t8, 0\n");
        sb.append("    sw $t8, 0($sp)\n");
        sb.append("    addi $sp, $sp, -4\n\n");
        sb.append("    move $s7, $sp\n");
        sb.append("    #On réserve la place pour " + taille / -4 + " variables\n");
        sb.append("    addi $sp, $sp, " + taille + "\n");
        if (taille != 0) {
            sb.append("\n");
            sb.append("    #Initialisation des variables à 0 : \n");
            for (int i = 0; i < -taille; i += 4) {
                sb.append("    sw $zero, " + -i + "($s7)\n");
            }
        }
        sb.append("\n");
        sb.append(bloc.toMIPS());
        sb.append(fin);
        return sb.toString() ;
    }

}
