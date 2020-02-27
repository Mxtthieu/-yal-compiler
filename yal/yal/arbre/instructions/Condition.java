package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.FabriqueNumero;
import yal.arbre.expressions.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Condition extends Instruction{

    private Expression exp;
    private BlocDInstructions ctrue;
    private BlocDInstructions cfalse;

    public Condition(Expression e) {
        super(e.getNoLigne());
        exp = e;
        ctrue = new BlocDInstructions(noLigne +1);
        cfalse = new BlocDInstructions(noLigne +1);

    }

    public Condition(Expression e, BlocDInstructions b, int typeBloc) {
        super(e.getNoLigne());
        exp = e;
        if (typeBloc == 0) {
            ctrue = b;
            cfalse = new BlocDInstructions(noLigne + 1);
        } else {
            cfalse = b;
            ctrue = new BlocDInstructions(noLigne + 1);
        }
    }

    public Condition(Expression e, BlocDInstructions b, BlocDInstructions b2){
        super(e.getNoLigne());
        exp = e;
        ctrue = b;
        cfalse = b2;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (!this.exp.getType().equals("bool")){
            StringBuilder sb = new StringBuilder();
            sb.append("Erreur de type : L'expression conditionnelle n'est pas booléenne");
            throw new AnalyseSemantiqueException(getNoLigne(), sb.toString());
        }
        ctrue.verifier();
        cfalse.verifier();
    }

    @Override
    public String toMIPS() {
        int compteur = FabriqueNumero.getInstance().getNumero();
        StringBuilder sb = new StringBuilder();
        sb.append("#Condition \n");
        sb.append("si"+ compteur +" :\n");
        sb.append(exp.toMIPS());
        sb.append("    beqz $v0, sinon"+ compteur +" :\n");
        sb.append("alors"+ compteur +" :\n");
        sb.append(ctrue.toMIPS());
        sb.append("j fin"+ compteur +" \n");
        sb.append("sinon"+ compteur +" :\n");
        sb.append(cfalse.toMIPS());
        sb.append("finsi"+ compteur + ":\n");
        return sb.toString();
    }
}
