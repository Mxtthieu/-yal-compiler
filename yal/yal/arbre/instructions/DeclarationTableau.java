package yal.arbre.instructions;

import yal.analyse.TDS;
import yal.analyse.entre.EntreeVar;
import yal.analyse.symbol.Symbole;
import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class DeclarationTableau extends Instruction{

    private String idf;
    private Expression size;
    private int dep;
    private int idRegion;



    public DeclarationTableau(String nidf, Expression e) {
        super(e.getNoLigne());
        this.idf = nidf;
        this.size = e;
    }

    public boolean isDeclTab() {
        return true;
    }

    @Override
    public void verifier() {
        idRegion = TDS.getInstance().getIdRegion();
        size.verifier();

        if(idRegion == 0) {
            if(!size.isConstante()) {
                throw new AnalyseSemantiqueException(getNoLigne(), " erreur decl tab " + size + " n'est pas une constante ");
            }

        }

        if(!(size.getType().equals("entier"))) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur type :\t longueur de " + idf + " \n\t" + size + " n'est pas entier");
        }

        EntreeVar e = new EntreeVar(idf);
        Symbole s = TDS.getInstance().identifier(e);

        if(s == null){
            throw new AnalyseSemantiqueException(getNoLigne(), " " + idf +  "n'a pas été déclarée ");
        }

        if(!(s.getType().equals("tab"))) {
            throw new AnalyseSemantiqueException(getNoLigne(), "erreur :\t " + idf + ".longueur\n\t " + idf + " n'est pas un tableau");
        }

        dep = s.getDep();
        idRegion = s.getIdRegion();

    }

    @Override
    public String toMIPS() {

        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append( "#Déclaration d'un tableau\n" );

        sb.append("#on range l'adresse du debut du tab\n" );
        sb.append("sw $sp, " + dep + "($s7)\n" );

        sb.append("#on met la longueur dans v0\n" );
        sb.append(size.toMIPS() );

        sb.append("#on check si la longueur est > 0\n" );
        sb.append("blez $v0, erreurLongueurTab\n" );

        sb.append("#on range la longueur\n" );
        sb.append("sw $v0, 0($sp)\n" );

        sb.append("#on initialise le tableau\n" );
        sb.append("tantquedecltab_" + compteur + " :\n" );

        sb.append("beqz $v0, fintantquedecltab_" + compteur + "\n" );

        sb.append("addi $v0, $v0, -1\n" );
        sb.append("addi $sp, $sp, -4\n" );
        sb.append("sw $zero, 0($sp)\n" );

        sb.append("j tantquedecltab_" + compteur + "\n" );

        sb.append("fintantquedecltab_" + compteur + " :\n" );

        sb.append("addi $sp, $sp, -4\n");

        return sb.toString();


    }
}
