package fr.eni.team42.enchere.servlets;

import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.ErrorManager;

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet("/user/AfficherProfil")
public class AfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherProfil.jsp");
		System.out.println("Afficher");
		String idUser = request.getParameter("user");
		try {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			if(idUser != null && Integer.parseInt(idUser) > 0) {
				UtilisateurManager um = new UtilisateurManager();
				user = um.selectById(Integer.parseInt(idUser));
			}
			request.setAttribute("utilisateur", user);
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
