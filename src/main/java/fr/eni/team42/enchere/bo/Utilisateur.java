package fr.eni.team42.enchere.bo;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.PasswordHashManager;
import fr.eni.team42.enchere.outils.Outils;

public class Utilisateur {

    private int idUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String mdp;
    private int credit;
    private boolean admin;

    public Utilisateur() {
    }

    public Utilisateur(int idUtilisateur, String pseudo) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
    }

    public Utilisateur(int idUtilisateur, String pseudo, String rue, String codePostal, String ville) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
                       String codePostal, String ville, String mdp, int credit) throws BusinessException {
        setPseudo(pseudo);
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.mdp = PasswordHashManager.hashMdp(mdp);
        this.credit = credit;
    }

    public Utilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String mdp, int credit, boolean admin) throws BusinessException {
        this.idUtilisateur = idUtilisateur;
        setPseudo(pseudo);
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.mdp = mdp;
        this.credit = credit;
        this.admin = admin;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) throws BusinessException {
        if(!Outils.isAlphaNumeric(pseudo))
            throw new BusinessException(BOExceptionCode.ERREUR_ILLEGAL_CHAR_PSEUDO);
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws BusinessException {
        if (!email.matches(".+@.+\\.[a-z]+")) {
            throw new BusinessException(BOExceptionCode.ERREUR_ILLEGAL_FORMAT_EMAIL);
        }
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
