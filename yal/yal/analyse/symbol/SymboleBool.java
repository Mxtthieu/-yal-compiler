package yal.analyse.symbol;

public class SymboleBool extends Symbole {

    /**
     *
     */
    public SymboleBool() {
        super();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return "bool";
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isVar() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isParam() {
        return false;
    }

}
