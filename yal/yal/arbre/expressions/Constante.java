package yal.arbre.expressions;

public abstract class Constante extends Expression {

    public String cste;

    /**
     *
     * @param texte
     * @param n
     */
    public Constante(String texte, int n) {
        super(n);
        cste = texte;
    }

    /**
     *
     */
    @Override
    public void verifier() {
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return cste;
    }

    /**
     *
     * @return
     */
    public abstract String val();

    /**
     *
     * @return
     */
    @Override
    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
            sb.append("    li $v0, "+  val() +"\n");
            return sb.toString();
    }

    @Override
    public boolean isConstante(){
        return true;
    }
    
}
