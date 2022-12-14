package fr.eni.team42.enchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bll.ArticleManager;
import fr.eni.team42.enchere.bll.CategorieManager;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Categorie;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.EtatVenteArticle;
import fr.eni.team42.enchere.bo.Retrait;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DAOFactory;
import fr.eni.team42.enchere.messages.LecteurMessage;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/enchere/NouvelleVente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categorie> categories;
			new DAOFactory();
			categories = DAOFactory.getCategorieDAO().selectAllCategories();
			request.setAttribute("categories", categories);
		} catch (BusinessException e) {
			//si erreur pour fetch, l'option par défaut "pas de catégorie" s'affichera
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        
		new DAOFactory();
		
		String nomArticle, description, rue, ville, codePostal;
		LocalDateTime dateDebutEnchere, dateFinEnchere;
		Integer prixInitial, prixVente, idCategorie;
		Categorie categorie;
		Utilisateur utilisateur;
		Retrait lieuRetrait;
		EtatVenteArticle etatVenteArticle;
		ArticleVendu article = new ArticleVendu();

		idCategorie = Integer.parseInt(request.getParameter("categorieArticle"));
		
		try {
		HttpSession session = request.getSession(false);
	    utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	    nomArticle = request.getParameter("nomArticle");
		dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebEnchere"),formatter).atTime(12, 0); 
		dateFinEnchere = LocalDate.parse(request.getParameter("dateFinEnchere"), formatter).atTime(12, 0);
		prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		prixVente = prixInitial;
		categorie = DAOFactory.getCategorieDAO().selectById(idCategorie);
		rue = request.getParameter("rueRetrait");
		ville = request.getParameter("villeRetrait");
		codePostal = request.getParameter("codePostalRetrait");
		description = request.getParameter("descriptionArticle");
		lieuRetrait = new Retrait(rue, ville, codePostal);
		etatVenteArticle = EtatVenteArticle.NON_DEBUTEE;
		
		
		article = new ArticleVendu(nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, 
				utilisateur, categorie, lieuRetrait, etatVenteArticle);
		article = DAOFactory.getArticleDAO().insert(article);
			
		//créé automatiquement une  instance dans la table retrait à partir de l'objet article retourné après insert
		Retrait pickUpPlace = new Retrait(article,rue,codePostal, ville);
		try {
			DAOFactory.getRetraitDAO().insert(pickUpPlace);
		}catch (BusinessException e){
			e.printStackTrace();
			DAOFactory.getArticleDAO().delete(article);
			request.setAttribute("article", article);
			request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);
		}
		
		ArticleManager am = new ArticleManager();
        CategorieManager cm = new CategorieManager();
        try {
            request.setAttribute("articles",am.selectAll());
            request.setAttribute("categories", cm.selectAllCategories());
        } catch (BusinessException e) {
            request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
        }
        request.setAttribute("info","Mise en vente de votre article réussie !");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
        rd.forward(request,response);

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("article", article);
			request.setAttribute("erreur", LecteurMessage.getMessageErreur(e.getCodeErreur()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
			rd.forward(request, response);
		}
	}

}
