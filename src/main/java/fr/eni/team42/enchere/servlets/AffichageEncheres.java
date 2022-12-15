package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.ArticleManager;
import fr.eni.team42.enchere.bll.CategorieManager;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.messages.LecteurMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AffichageEncheres", value = "/accueil")
public class AffichageEncheres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManager am = new ArticleManager();
        CategorieManager cm = new CategorieManager();

        //MAJ Status enchères
        try {
            am.majEtat();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute("articles",am.selectAll());
            request.setAttribute("categories", cm.selectAllCategories());
        } catch (BusinessException e) {
            request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //récupération des catégories
        CategorieManager cm = new CategorieManager();
        ArticleManager am = new ArticleManager();

        //MAJ Status enchères
        try {
            am.majEtat();
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        //récupération des variables du filtre
        String nom = request.getParameter("nom").trim().isEmpty() ? null : request.getParameter("nom");
        String catStr = request.getParameter("categorie").equals("-1") ? null : request.getParameter("categorie");
        //Param Utilisateur Achat
        boolean aOuvertes = request.getParameter("aOuvertes") != null;
        boolean aEnCours = request.getParameter("aEnCours") != null;
        boolean aRemportees = request.getParameter("aRemportees") != null;
        //Param Utilisateur Vente
        boolean vEnCours = request.getParameter("vEnCours") != null;
        boolean vNonDeb = request.getParameter("vNonDeb") != null;
        boolean vTerminees = request.getParameter("vTerminees") != null;
        int radio = request.getParameter("achat-vente") == null ? 0 : Integer.parseInt(request.getParameter("achat-vente"));

        //Renvoie infos vers JSP
        request.setAttribute("nom",nom);
        request.setAttribute("catStr",catStr);
        request.setAttribute("aOuvertes",aOuvertes);
        request.setAttribute("aEnCours",aEnCours);
        request.setAttribute("aRemportees",aRemportees);
        request.setAttribute("vEnCours",vEnCours);
        request.setAttribute("vNonDeb",vNonDeb);
        request.setAttribute("vTerminees",vTerminees);
        request.setAttribute("radio",radio);


        Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
        List<ArticleVendu> listArt = new ArrayList<>();
        try {
            //liste des enchères
            listArt = am.recherche(user == null ? null : user.getIdUtilisateur(), nom, catStr, aOuvertes, aEnCours, aRemportees, vNonDeb, vEnCours, vTerminees);
            //liste des catégories
            request.setAttribute("categories", cm.selectAllCategories());
        } catch (BusinessException e) {
            request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
        }

        request.setAttribute("articles", listArt);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request,response);
    }
}
