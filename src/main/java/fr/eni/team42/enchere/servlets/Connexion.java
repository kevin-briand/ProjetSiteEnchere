package fr.eni.team42.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.messages.LecteurMessage;


/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String identifiant = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			//si null, renvoie dans le block catch
			if(identifiant.isEmpty() || password.isEmpty()) throw new BusinessException(ServletExceptionCode.CHAMP_VIDE);

			UtilisateurManager userManager = new UtilisateurManager();
			Utilisateur utilisateur = userManager.logIn(identifiant, password);

			//si null, renvoie dans le block catch
			if(utilisateur == null) throw new BusinessException(ServletExceptionCode.UTILISATEUR_NULL);

			//Utilisateur correct, ajout dans la session
			HttpSession session = request.getSession(true);
			session.setAttribute("utilisateurConnecte", utilisateur);

			//Retour sur la page d'accueil
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);

		} catch(BusinessException e){
			request.setAttribute("email", identifiant); //renvoie du champ mail
			request.setAttribute("erreurConnexion", LecteurMessage.getMessageErreur(e.getCodeErreur()));

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
			rd.forward(request, response);
		}
	}
}