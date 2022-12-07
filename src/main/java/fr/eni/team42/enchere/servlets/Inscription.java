package fr.eni.team42.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.bll.UtilisateurManager;


/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pseudo, nom, prenom, email, telephone, codePostal, rue, ville, mdp;
		Integer credit;
		try {
			pseudo = request.getParameter("pseudo");
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			email = request.getParameter("email");
			telephone = request.getParameter("telephone");
			rue = request.getParameter("rue");
			codePostal = request.getParameter("cp");
			ville = request.getParameter("ville");
			mdp = request.getParameter("password");
			credit = 0;
			Utilisateur u = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit, false);
			UtilisateurManager userManager = new UtilisateurManager();
			userManager.addUtilisateur(u);
			HttpSession session = request.getSession(false);
			session.setAttribute("utilisateurConnecte", u);
			//quelque chose côté jsp pour signifier que l'utilisateur est passé à l'état connecté ?
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);		
		}
		catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion/connexionJSP.jsp");
			//request.getParameter("login") dans la jsp pour remettre la valeur saisie
			rd.forward(request, response);		
		}	
		
	}

}