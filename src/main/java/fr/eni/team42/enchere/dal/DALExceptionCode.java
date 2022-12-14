package fr.eni.team42.enchere.dal;

// Code erreurs entre 1 et 100
public class DALExceptionCode {

    /**
     * Tentative d'ajout d'un objet NULL
     */
    public static final int INSERT_OBJET_NULL = 1;

    /**
     * L'utilisateur n'existe pas
     */
    public static final int UTILISATEUR_INCONNU = 2;

    /**
     * Une erreur s'est produite durant l'insertion de l'objet
     */
    public static final int INSERT_ERREUR = 3;

    /**
     * Une erreur inconnue s'est produite
     */
    public static final int GENERAL_ERREUR = 4;

    /**
     * Le pseudo existe déjà
     */
    public static final int DUPLICATION_PSEUDO = 5;

    /**
     * L'email existe déjà
     */
    public static final int DUPLICATION_EMAIL = 6;

    /**
     * L'article n'existe pas
     */
    public static final int ARTICLE_INCONNU = 7;

    /**
     * La catégorie n'existe pas
     */
    public static final int CATEGORIE_INCONNUE = 8;

}
