package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    /**
     *
     * @param n
     */
    protected Expression(int n) {
        super(n) ;
    }

    /**
     *
     * @return
     */
    public abstract String getType();

}
