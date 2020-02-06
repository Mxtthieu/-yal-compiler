package yal.arbre.expressions;

public abstract class Constante extends Expression {

    public String cste;

    public Constante(String texte, int n) {
        super(n);
        cste = texte;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste;
    }

    public abstract String val();
    
    @Override
    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
            sb.append("    li $v0, "+  val() +"\n");
            return sb.toString();
    }

    @Override
    public String getType(){
        return null;
    }

}
