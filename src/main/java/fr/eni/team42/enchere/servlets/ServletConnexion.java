package fr.eni.team42.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.team42.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String login;
		String password;
		try {
			login = request.getParameter("login");
			password = request.getParameter("password");
			if((Utilisateur.getEmail() == login || Utilisateur.getPseudo() == login) && Utilisateur.getMdp() == password){
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres.jsp");
				rd.forward(request, response);		
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		//request.getParameter("login") dans la jsp pour remettre la valeur saisie
		rd.forward(request, response);		
	}

}
