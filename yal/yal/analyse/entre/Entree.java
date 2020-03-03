package yal.analyse.entre;

import java.util.Objects;

public abstract class Entree {

    private String idf;

    /**
     *
     * @param s
     */
    public Entree(String s){
        this.idf = s;
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
        return Objects.equals(idf, entree.idf);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(idf);
    }
}
