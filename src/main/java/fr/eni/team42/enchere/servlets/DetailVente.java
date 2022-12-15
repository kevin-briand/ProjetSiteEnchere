package fr.eni.team42.enchere.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.dal.DAOFactory;


/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/encheres/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
    }

    @GET
    @Path("/encheres/DetailVente/{id}")
    public int getID(@PathParam("id") int id) {
		return id;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		int idArticle = 4; //pas encore trouvé la bonne méthode pour récupérer l'id article
		try {
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
		doGet(request, response);
	}

}
