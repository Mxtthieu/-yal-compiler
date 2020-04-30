package yal.analyse.entre;

import java.util.Objects;

public abstract class Entree {

    private String idf;
    private int nbParam;

    /**
     *
     * @param s
     */
    public Entree(String s){
        this.idf = s;
        this.nbParam = 0;
    }

    /**
     *
     * @param s
     * @param nbParam
     */
    public Entree(String s, int nbParam){
        this.idf = s;
        this.nbParam = nbParam;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return idf;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entree entree = (Entree) o;
        return nbParam == entree.nbParam && Objects.equals(idf, entree.idf);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(idf,nbParam);
    }
}
