package fr.eni.team42.enchere.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;
import fr.eni.team42.enchere.messages.LecteurMessage;


/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/enchere/info")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		String idArt = request.getParameter("id");
		try {
			int idArticle = Integer.parseInt(idArt);
			ArticleVendu article = new ArticleVendu();
			if(idArticle > 0) {
				article = DAOFactory.getArticleDAO().selectById(idArticle);
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, à hh:mm");
				LocalDateTime dateFinEnch = article.getDateFinEnchere();
				String formattedDate = dateFinEnch.format(dateFormatter);
				request.setAttribute("dateFinEnchere", formattedDate);
			}
			request.setAttribute("article", article);
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		new DAOFactory();
		
		Utilisateur utilisateur;
	    String articleVendu;
	    LocalDateTime dateEnchere;
	    int montantEnchere;
	    
		HttpSession session = request.getSession(false);
	    utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
	    articleVendu =  request.getParameter("idArticle");
	    dateEnchere = LocalDateTime.now();
	    montantEnchere = Integer.parseInt(request.getParameter("choixMontantEnchere"));

	    ArticleVendu article = new ArticleVendu();
	    try {
			article = DAOFactory.getArticleDAO().selectById(Integer.parseInt(articleVendu));
			Enchere enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
			DAOFactory.getEnchereDAO().insert(enchere);
			DAOFactory.getArticleDAO().updatePrixVente(enchere);
			request.setAttribute("article", article);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
			rd.forward(request, response);

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("article", article);
			request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
			rd.forward(request, response);		}
	    
	}

}
