package fr.eni.team42.enchere;

public class BusinessException extends Exception {
    private int codeErreur;

    public BusinessException() {
        super();
        this.codeErreur = 0;
    }

    public BusinessException(int erreurCode) {
        super();
        this.codeErreur = erreurCode;
    }

    public int getCodeErreur() {
        return codeErreur;
    }

    public void setCodeErreur(int codesErreur) {
        this.codeErreur = codesErreur;
    }

    public boolean hasErreur() {
        return codeErreur != 0;
    }
}
