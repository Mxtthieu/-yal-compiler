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
    private BlocDInstructions inst;
    private BlocDInstructions fcts;
    private BlocDInstructions tab;


    /**
     *
     * @param b
     * @param i
     */
    public Main(BlocDInstructions b, int i) {
        super(i);
        inst = b;
        fcts = new BlocDInstructions(i + 1);
        tab = new BlocDInstructions(i + 1);
    }

    /**
     *
     * @param b
     * @param b2
     * @param i
     */
    public Main(BlocDInstructions b, BlocDInstructions b2, int i) {
        super(i);
        inst = b2;
        fcts = new BlocDInstructions(i + 1);
        tab = new BlocDInstructions(i + 1);
        for(int j = 0; j < b.inst.size(); j++){
            Instruction decl = b.inst.get(j);
            if (decl.isTab()){
                tab.ajouter(decl);
            } else {
                fcts.ajouter(decl);
            }
        }
    }

    /**
     *
     */
    @Override
    public void verifier(){
        TDS.getInstance().debutDeBloc();
        taille = TDS.getInstance().TailleZoneVariable();
        inst.verifier();
        fcts.verifier();
        TDS.getInstance().finDeBloc();
    }

    /**
     *
     * @return
     */
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
        sb.append("    #On réserve la place pour " + taille /4 + " variables\n");
        sb.append("    addi $sp, $sp, " + taille + "\n");
        if (taille != 0) {
            sb.append("\n");
            sb.append("    #Initialisation des variables à 0 : \n");
            for (int i = 0; i < -taille; i += 4) {
                sb.append("    sw $zero, " + -i + "($s7)\n");
            }
        }
        sb.append("\n");
        sb.append(inst.toMIPS());
        sb.append(fin);
        sb.append("\n");
        if(fcts != null) {
            sb.append(fcts.toMIPS());
        }
        return sb.toString() ;
    }

}
