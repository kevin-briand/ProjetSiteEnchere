package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.messages.LecteurMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/SuppressionUtilisateur")
public class SuppressionUtilisateur extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
            try {
                UtilisateurManager utilisateurManager = new UtilisateurManager();
                utilisateurManager.removeUtilisateur(utilisateur);
                session.invalidate();
                request.setAttribute("info","Compte supprimé avec succès !");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (BusinessException e) {
                e.printStackTrace();
                request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
                rd.forward(request, response);
            }
        }
    }
}
