package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.EtatVenteArticle;
import fr.eni.team42.enchere.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

@WebServlet(name = "AffichageEncheres", value = "/enchere/")
public class AffichageEncheres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManager am = new ArticleManager();
        request.setAttribute("articles",am.selectAll());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //récupération des variables du filtre
        String nom = request.getParameter("nom");
        String catStr = request.getParameter("categorie");
        //Param Utilisateur Achat
        boolean aOuvertes = Boolean.getBoolean(request.getParameter("aOuvertes") == null ? "false" : request.getParameter("aOuvertes"));
        boolean aEnCours = Boolean.getBoolean(request.getParameter("aEnCours") == null ? "false" : request.getParameter("aEnCours"));
        boolean aRemportees = Boolean.getBoolean(request.getParameter("aRemportees") == null ? "false" : request.getParameter("aRemportees"));
        //Param Utilisateur Vente
        boolean vEnCours = Boolean.getBoolean(request.getParameter("vEnCours") == null ? "false" : request.getParameter("vEnCours"));
        boolean vNonDeb = Boolean.getBoolean(request.getParameter("vNonDeb") == null ? "false" : request.getParameter("vNonDeb"));
        boolean vTerminees = Boolean.getBoolean(request.getParameter("vTerminees") == null ? "false" : request.getParameter("vTerminees"));

        ArticleManager am = new ArticleManager();
        List<ArticleVendu> listArt;

        if(catStr != null && !catStr.isEmpty()) {
            EtatVenteArticle cat = EtatVenteArticle.values()[Integer.parseInt(catStr)];
            listArt = am.selectByNomArticleEtCategorie(nom, cat);
        } else {
            listArt = am.selectByNomArticle(nom);
        }

        Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
        if(user != null) {
            //Filtre
            List<ArticleVendu> listeFiltree = new ArrayList<>();
            List<EtatVenteArticle> filtreEtat = new ArrayList<>();
            if (aOuvertes || vNonDeb) {
                filtreEtat.add(EtatVenteArticle.NON_DEBUTEE);
            }
            if (aEnCours || vEnCours) {
                filtreEtat.add(EtatVenteArticle.EN_COURS);
            }
            if (aRemportees || vTerminees) {
                filtreEtat.add(EtatVenteArticle.TERMINEE);
            }

            //intégrer l'enchère utilisateur

            if(aOuvertes || aEnCours || aRemportees) { //filtre Achat
                for (ArticleVendu art : listArt) {
                    if (filtreEtat.contains(art.getEtatVenteArticle())
                            && art.getEncheres().get(0).getUtilisateur().getIdUtilisateur() == user.getIdUtilisateur()) {
                        listeFiltree.add(art);
                    }
                }
            } else if (vNonDeb || vEnCours || vTerminees) { //Filtre Ventes
                for (ArticleVendu art : listArt) {
                    if (filtreEtat.contains(art.getEtatVenteArticle())
                            && art.getUtilisateur().getIdUtilisateur() == user.getIdUtilisateur()) {
                        listeFiltree.add(art);
                    }
                }
            }
            listArt = listeFiltree;
        }
        request.setAttribute("articles",listArt);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request,response);
    }
}
