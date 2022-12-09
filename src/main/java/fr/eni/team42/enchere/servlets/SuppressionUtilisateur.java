package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/SuppressionUtilisateur")
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
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (BusinessException businessException) {
                businessException.printStackTrace();
                request.setAttribute("erreur", "Erreur dans la suppression, veuillez réessayer");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");
                rd.forward(request, response);
            }
        }
    }
}
