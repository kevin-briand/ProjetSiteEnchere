package fr.eni.team42.enchere.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class FilterActiveSession implements Filter {

    //Création d'une liste de servlets path qui sont autorisées hors connexion
    //que l'utilisateur peut consulter sans être connecté
    private static final List<String> SERVLET_AUTORISEES_HORS_CONNEXION = new ArrayList<>();

    public void init(FilterConfig config) {
        SERVLET_AUTORISEES_HORS_CONNEXION.add("/index.jsp");
        SERVLET_AUTORISEES_HORS_CONNEXION.add("/Connexion");
        SERVLET_AUTORISEES_HORS_CONNEXION.add("/Inscription");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //Condition cherchant si la requête vise une servlet autorisée pour la laisser passer
        if (SERVLET_AUTORISEES_HORS_CONNEXION.contains(httpServletRequest.getServletPath())) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            //sinon on regarde s'il existe une session active
        } else {
            HttpSession session = httpServletRequest.getSession(false);
            Integer sessionActive = (Integer) session.getAttribute("utilisateurConnecte");

            //S'il n'y a pas de session active, on redirige l'utilisateur vers index.jsp
            if (sessionActive == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(httpServletRequest, httpServletResponse);
             //sinon on laisse passer la requête
            } else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
}
