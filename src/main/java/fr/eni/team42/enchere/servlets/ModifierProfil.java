package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.bll.BLLException;
import fr.eni.team42.enchere.bll.PasswordHashManager;
import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.rest.encheres;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "ModifierProfil", value = "/profil/modification")
public class ModifierProfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String codePostal = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
        String password = request.getParameter("password");
        String oldPassword= request.getParameter("oldPassword");
        RequestDispatcher rd;

        try {
            Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if(user != null) {
                user.setPseudo(pseudo);
                user.setNom(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setTelephone(telephone);
                user.setRue(rue);
                user.setCodePostal(codePostal);
                user.setVille(ville);
                if(PasswordHashManager.hashMdp(oldPassword).equals(user.getMdp()) && !password.isEmpty())
                    user.setMdp(PasswordHashManager.hashMdp(password));
            }
            UtilisateurManager um = new UtilisateurManager();
            um.updateUtilisateur(user);
            HttpSession session = request.getSession(false);
            session.setAttribute("utilisateurConnecte", user);

            rd = request.getRequestDispatcher("/WEB-INF/afficherProfil.jsp");
        } catch (Exception e) {
            request.setAttribute("erreur","Un champ n'a pas correctement été rempli");
            rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
        }

        rd.forward(request,response);
    }
}
