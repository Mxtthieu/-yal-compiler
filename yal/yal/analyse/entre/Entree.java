package yal.analyse.entre;

import java.util.Objects;

public abstract class Entree {

    private String idf;

    public Entree(String s){
        this.idf = s;
    }

    @Override
    public String toString(){
        return "Entree{ idf = '"+idf+"\'}";
    }

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
}
