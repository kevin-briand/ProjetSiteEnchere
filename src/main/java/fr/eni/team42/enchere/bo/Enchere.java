package fr.eni.team42.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {

    private int idUtilisateur;
    private int idArticle;
    private LocalDateTime dateEnchere;
    private int montantEnchere;

    public Enchere() {
    }

    public Enchere(int idUtilisateur, int idArticle, LocalDateTime dateEnchere, int montantEnchere) {
        this.idUtilisateur = idUtilisateur;
        this.idArticle = idArticle;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }
}
