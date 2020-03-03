package yal.exceptions;

public class AnalyseSyntaxiqueException extends AnalyseException {

    /**
     *
     * @param m
     */
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }
}
