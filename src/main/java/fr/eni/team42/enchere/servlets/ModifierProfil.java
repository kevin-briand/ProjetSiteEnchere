package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.PasswordHashManager;
import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.messages.LecteurMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ModifierProfil", value = "/user/modifierProfil")
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
        String codePostal = request.getParameter("cp");
        String ville = request.getParameter("ville");
        String password = request.getParameter("password");
        String oldPassword= request.getParameter("oldPassword");
        RequestDispatcher rd;

        try {
            Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
            if(user != null) {
                user.setPseudo(pseudo);
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setTelephone(telephone);
                user.setRue(rue);
                user.setCodePostal(codePostal);
                user.setNom(nom);
                user.setVille(ville);
                if(!oldPassword.isEmpty() && !password.isEmpty() && PasswordHashManager.hashMdp(oldPassword).equals(user.getMdp()))
                    user.setMdp(PasswordHashManager.hashMdp(password));
            }
            UtilisateurManager um = new UtilisateurManager();
            um.updateUtilisateur(user);
            HttpSession session = request.getSession(false);
            session.setAttribute("utilisateurConnecte", user);
            request.setAttribute("utilisateur", user);

            rd = request.getRequestDispatcher("/WEB-INF/afficherProfil.jsp");
        } catch (BusinessException e) {
            e.printStackTrace();
            request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
            rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
        }

        rd.forward(request,response);
    }
}
