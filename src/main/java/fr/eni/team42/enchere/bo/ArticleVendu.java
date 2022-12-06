package fr.eni.team42.enchere.bo;

import java.time.LocalDateTime;

public class ArticleVendu {

    private int idArticle;
    private String nomArticle;
    private String description;
    private LocalDateTime dateDebutEnchere;
    private LocalDateTime dateFinEnchere;
    private Integer prixInitial;
    private Integer prixVente;
    private Utilisateur utilisateur;
    private Categorie categorie;

    public ArticleVendu() {
    }

    public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixVente, Utilisateur utilisateur, Categorie categorie) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public LocalDateTime getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public Integer getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(Integer prixInitial) {
        if (prixInitial == null) {
            this.prixInitial = 0;
        } else if (prixInitial < 0) {
            throw new RuntimeException();
        } else {
            this.prixInitial = prixInitial;
        }

    }

    public Integer getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Integer prixVente) {
        if (prixVente == null) {
            this.prixVente = 0;
        } else if (prixVente < 0) {
            throw new RuntimeException();
        } else {
            this.prixVente = prixVente;
        }

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
