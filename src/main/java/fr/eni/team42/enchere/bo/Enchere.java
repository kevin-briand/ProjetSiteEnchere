package fr.eni.team42.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {

    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;
    private LocalDateTime dateEnchere;
    private int montantEnchere;

    public Enchere() {
    }

    public Enchere(ArticleVendu articleVendu, LocalDateTime dateEnchere, int montantEnchere) {
        this.articleVendu = articleVendu;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Enchere(Utilisateur utilisateur, ArticleVendu articleVendu, LocalDateTime dateEnchere, int montantEnchere) {
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
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
