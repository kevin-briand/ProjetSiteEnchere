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
    private Enchere enchere;
    private Retrait lieuRetrait;
    private EtatVenteArticle etatVenteArticle;

    public ArticleVendu() {
    }

    public ArticleVendu(int idArticle, String nomArticle, LocalDateTime dateFinEnchere, Integer prixVente, Utilisateur utilisateur, EtatVenteArticle etatVenteArticle) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        setPrixVente(prixVente);
        this.utilisateur = utilisateur;
        this.etatVenteArticle = etatVenteArticle;
    }

    public ArticleVendu(int idArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, Integer prixInitial, Integer prixVente, Utilisateur utilisateur, Categorie categorie, Retrait lieuRetrait, EtatVenteArticle etatVenteArticle) {
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.lieuRetrait = lieuRetrait;
        this.etatVenteArticle = etatVenteArticle;
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

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public EtatVenteArticle getEtatVenteArticle() {
        return etatVenteArticle;
    }

    public void setEtatVenteArticle(EtatVenteArticle etatVenteArticle) {
        this.etatVenteArticle = etatVenteArticle;
    }
}
