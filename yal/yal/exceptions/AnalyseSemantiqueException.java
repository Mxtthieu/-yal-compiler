package yal.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {

    /**
     *
     * @param ligne
     * @param m
     */
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE : ligne " + ligne + "\n\t" + m + "\n") ;
    }

}
