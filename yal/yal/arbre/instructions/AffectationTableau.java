package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.analyse.symbol.SymboleTableau;
import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class AffectationTableau extends Instruction {
    private String idf;
    private Expression exp;
    private Expression indice;
    private int dep;
    private int idRegion;

    public AffectationTableau(String idf, Expression indice, Expression exp) {
        super(exp.getNoLigne());
        this.idf = idf;
        this.indice = indice;
        this.exp = exp;

    }

    @Override
    public void verifier() {
        exp.verifier();
        indice.verifier();

        if(!(indice.getType().equals("entier"))) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur type :\t indice " + idf + " \n\t" + indice + " n'est pas entier");
        }

        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);

        if(s == null){
            throw new AnalyseSemantiqueException(getNoLigne(), " " + idf +  "n'a pas été déclarée ");
        }

        if(!(s.getType().equals("tab"))) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur :\t " + idf + ".longueur\n\t " + idf + " n'est pas un tableau");
        }

        idRegion = s.getIdRegion();
        dep = s.getDep();



    }

    @Override
    public String toMIPS() {

        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append("#Affectation tableau \n");

        sb.append( exp.toMIPS() + "\n");

        sb.append("#on empile la valeur qu'on veut mettre dans la variable\n");
        sb.append("sw $v0, 0($sp)\n" );
        sb.append("add $sp, $sp, -4\n" );

        sb.append("#On recupere la base\n" );
        sb.append("move $t5, $s7\n" );

        sb.append("#on récupere le numéro de région du tableau\n" );
        sb.append("li $v1, " + idRegion + "\n" );

        sb.append("#début tantque\n" );
        sb.append("tantqueaffecttab_" + compteur + " :\n" );

        sb.append("#on prend le numéro de région courant\n" );
        sb.append("lw $v0, 4($t5) \n" );
        sb.append("sub $v0, $v0, $v1\n" );

        sb.append("#on va a la fin si les numéros correspondent\n" );
        sb.append("beqz $v0, fintantqueaffecttab_" + compteur + "\n" );

        sb.append("#on essaye avec le numéro de région précédent sinon\n" );
        sb.append("lw $t5, 8($t5) \n" );
        sb.append("j tantqueaffecttab_" + compteur + "\n" );

        sb.append("#sortie du tantque\n" );
        sb.append("fintantqueaffecttab_" + compteur + " :\n\n" );

        sb.append("#chargement adresse tab\n" );
        sb.append("lw $t2, " + dep + "($t5)\n" );

        sb.append("#on met l'indice dans $v0\n" );
        sb.append(indice.toMIPS() );

        sb.append("#si indice de tableau inférieur a 0\n" );
        sb.append("bltz $v0, erreurAccesTab\n" );

        sb.append("#longueur du tableau\n" );
        sb.append("lw $t5, 0($t2)\n" );

        sb.append("#longueur moins l'indice\n" );
        sb.append("sub $t5, $t5, $v0\n" );

        sb.append("#si indice supérieur a la longueur du tableau\n" );
        sb.append("blez $t5, erreurAccesTab\n" );

        sb.append("li $t3, -4\n" );
        sb.append("mult $v0, $t3\n" );
        sb.append("mflo $t4\n" );

        sb.append("#on retire la place de la longueur a $t4\n" );
        sb.append("add $t4, $t4, -4\n" );

        sb.append("#on depile la valeur a mettre dans la variable\n" );
        sb.append("add $sp, $sp, 4\n" );
        sb.append("lw $v0, 0($sp)\n" );

        sb.append("add $t2, $t2, $t4\n");

        sb.append("sw $v0, 0($t2)\n");

        return sb.toString();
    }
}
