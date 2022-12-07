package fr.eni.team42.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fr.eni.team42.enchere.bll.UtilisateurManager;
import fr.eni.team42.enchere.bo.Utilisateur;


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
		String identifiant;
		String password;
		try {
			identifiant = request.getParameter("email");
			password = request.getParameter("password");
			if(identifiant != null || password != null) {
				UtilisateurManager userManager = new UtilisateurManager();
				Utilisateur utilisateur = userManager.logIn(identifiant, password);
				if(utilisateur != null){
					HttpSession session = request.getSession(false);
					session.setAttribute("utilisateurConnecte", utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				}else {
					request.setAttribute("erreurConnexion", "Le login ou le mot de passe est incorrect");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
					rd.forward(request, response);	
				}
			}else {
				request.setAttribute("erreurConnexion", "Login ou mot de passe manquant");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
				rd.forward(request, response);	
				}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erreurConnexion", "Erreur dans la conenxion, veuillez retenter");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
			//request.getParameter("login") dans la jsp pour remettre la valeur saisie
			rd.forward(request, response);	
		}
	}
}