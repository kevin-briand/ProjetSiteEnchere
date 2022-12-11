package fr.eni.team42.enchere.bo;

import java.util.List;

public class Categorie {

    private int idCategorie;
    private String libelle;
    private List<ArticleVendu> articleVendus;

    public Categorie(String libelle, List<ArticleVendu> articleVendus) {
        this.libelle = libelle;
        this.articleVendus = articleVendus;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public List<ArticleVendu> getArticleVendus() {
        return articleVendus;
    }

    public void setArticleVendus(List<ArticleVendu> articleVendus) {
        this.articleVendus = articleVendus;
    }
}
