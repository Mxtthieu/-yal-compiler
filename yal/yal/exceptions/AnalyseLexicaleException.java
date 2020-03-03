package yal.exceptions;

public class AnalyseLexicaleException extends AnalyseException {

    /**
     *
     * @param ligne
     * @param colonne
     * @param m
     */
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE :\n\tligne " + ligne + " colonne " + colonne + "\n\t" + m + " : caractère non reconnu");
    }

}
