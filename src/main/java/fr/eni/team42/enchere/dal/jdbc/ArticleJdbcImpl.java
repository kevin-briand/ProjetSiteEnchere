package fr.eni.team42.enchere.dal.jdbc;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.*;
import fr.eni.team42.enchere.dal.ArticleDAO;
import fr.eni.team42.enchere.dal.DALExceptionCode;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ArticleJdbcImpl implements ArticleDAO {

    private final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?, etat_vente=? " +
            "WHERE no_article=?";
    private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";

    private final String ARTICLE_ENCHERE_MAX = "SELECT * FROM ENCHERES E " +
            "LEFT JOIN ARTICLES_VENDUS A on E.no_article = A.no_article " +
            "LEFT JOIN RETRAITS R on A.no_article = R.no_article " +
            "LEFT JOIN CATEGORIES C on C.no_categorie = A.no_categorie " +
            "LEFT JOIN UTILISATEURS U on A.no_utilisateur = U.no_utilisateur " +
            "LEFT JOIN UTILISATEURS acheteur on E.no_utilisateur = acheteur.no_utilisateur " +
            "WHERE A.no_article = ? " +
            "ORDER BY E.montant_enchere DESC " +
            "LIMIT 1";

    private final String ARTICLE_PAR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS A " +
            "LEFT JOIN CATEGORIES C on C.no_categorie = A.no_categorie " +
            "LEFT JOIN UTILISATEURS U on A.no_utilisateur = U.no_utilisateur " +
            "WHERE C.libelle = ?";

    private final String ARTICLE_PAR_NOM = "SELECT * FROM ARTICLES_VENDUS A " +
            "LEFT JOIN CATEGORIES C on C.no_categorie = A.no_categorie " +
            "LEFT JOIN UTILISATEURS U on A.no_utilisateur = U.no_utilisateur " +
            "WHERE A.nom_article LIKE ?";

    private final String ARTICLE_PAR_NOM_ET_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS A " +
            "LEFT JOIN CATEGORIES C on C.no_categorie = A.no_categorie " +
            "LEFT JOIN UTILISATEURS U on A.no_utilisateur = U.no_utilisateur " +
            "WHERE C.libelle = ? AND A.nom_article LIKE ?";

    private final String SELECT_ALL = "SELECT A.no_article, A.nom_article, A.date_fin_encheres, " +
            "U.pseudo, U.no_utilisateur, prix_vente, etat_vente " +
            "FROM articles_vendus A INNER JOIN UTILISATEURS U on A.no_utilisateur = U.no_utilisateur;";

    public List<ArticleVendu> selectAll() throws BusinessException {
        List<ArticleVendu> listAV = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Utilisateur
                Utilisateur u = new Utilisateur();
                u.setPseudo(rs.getString("U.pseudo"));
                u.setIdUtilisateur(rs.getInt("U.no_utilisateur"));
                //Article
                listAV.add(new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("nom_article"),
                        rs.getTimestamp("date_fin_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getInt("prix_vente"),
                        u, EtatVenteArticle.valueOf(rs.getString("etat_vente"))));
                }
                return listAV;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public ArticleVendu selectById(Integer id) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(ARTICLE_ENCHERE_MAX);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // on crée l'article à partir de la première ligne retournée par la bdd
                ArticleVendu articleVendu = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getTimestamp("date_debut_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getTimestamp("date_fin_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        new Utilisateur(rs.getInt("U.no_utilisateur"),
                                rs.getString("U.pseudo"),
                                rs.getString("U.rue"),
                                rs.getString("U.code_postal"),
                                rs.getString("U.ville")),
                        new Categorie(rs.getInt("C.no_categorie"),
                                rs.getString("libelle")),
                        // si un des trois champs est null, on considère que l'adresse de retrait n'est pas renseignée
                        rs.getString("R.rue") != null && rs.getString("R.code_postal") != null && rs.getString("R.ville") != null
                                ? new Retrait(rs.getString("R.rue"), rs.getString("R.code_postal"), rs.getString("R.ville")) : null,
                        EtatVenteArticle.valueOf(rs.getString("etat_vente")));
                if (articleVendu.getLieuRetrait() != null) {
                    articleVendu.getLieuRetrait().setArticle(articleVendu);
                }
                if (rs.getTimestamp("date_enchere") != null) {
                    articleVendu.setEnchere(
                            new Enchere(
                                    new Utilisateur(rs.getInt("acheteur.no_utilisateur"), rs.getString("acheteur.pseudo")),
                                    articleVendu,
                                    rs.getTimestamp("date_enchere").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                                    rs.getInt("montant_enchere")
                            )
                    );
                }
                return articleVendu;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public List<ArticleVendu> selectByCategorie(String libelle) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(ARTICLE_PAR_CATEGORIE);
            ps.setString(1, libelle);
            ResultSet rs = ps.executeQuery();
            List<ArticleVendu> articles = new ArrayList<>();
            while (rs.next()) {
                // on crée l'article à partir de la première ligne retournée par la bdd
                ArticleVendu articleVendu = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("nom_article"),
                        rs.getTimestamp("date_fin_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getInt("prix_vente"),
                        new Utilisateur(rs.getInt("U.no_utilisateur"), rs.getString("pseudo")),
                        EtatVenteArticle.valueOf(rs.getString("etat_vente")));
                articles.add(articleVendu);
            }
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public List<ArticleVendu> selectByNomArticle(String nomArticle) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(ARTICLE_PAR_NOM);
            ps.setString(1, '%' + nomArticle + '%');
            ResultSet rs = ps.executeQuery();
            List<ArticleVendu> articles = new ArrayList<>();
            while (rs.next()) {
                // on crée l'article à partir de la première ligne retournée par la bdd
                ArticleVendu articleVendu = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("nom_article"),
                        rs.getTimestamp("date_fin_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getInt("prix_vente"),
                        new Utilisateur(rs.getInt("U.no_utilisateur"), rs.getString("pseudo")),
                        EtatVenteArticle.valueOf(rs.getString("etat_vente")));
                articles.add(articleVendu);
            }
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public List<ArticleVendu> selectByNomArticleEtCategorie(String nomArticle, String libelle) throws BusinessException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(ARTICLE_PAR_NOM_ET_CATEGORIE);
            ps.setString(1, libelle);
            ps.setString(2, '%' + nomArticle + '%');
            ResultSet rs = ps.executeQuery();
            List<ArticleVendu> articles = new ArrayList<>();
            while (rs.next()) {
                // on crée l'article à partir de la première ligne retournée par la bdd
                ArticleVendu articleVendu = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("nom_article"),
                        rs.getTimestamp("date_fin_encheres").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        rs.getInt("prix_vente"),
                        new Utilisateur(rs.getInt("U.no_utilisateur"), rs.getString("pseudo")),
                        EtatVenteArticle.valueOf(rs.getString("etat_vente")));
                articles.add(articleVendu);
            }
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public void insert(ArticleVendu article) throws BusinessException {
        if (article == null) {
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
        }

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, article.getNomArticle());
            ps.setString(2, article.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEnchere()));
            ps.setTimestamp(4, Timestamp.valueOf(article.getDateFinEnchere()));
            ps.setInt(5, article.getPrixInitial());
            ps.setInt(6, article.getPrixVente());
            ps.setInt(7, article.getUtilisateur().getIdUtilisateur());
            ps.setInt(8, article.getCategorie().getIdCategorie());
            ps.setString(9, article.getEtatVenteArticle().name());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                article.setIdArticle(rs.getInt(1));
            } else {
                throw new BusinessException(DALExceptionCode.INSERT_ERREUR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.INSERT_ERREUR);
        }
    }

    @Override
    public void update(ArticleVendu article) throws BusinessException {
        if (article == null) {
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
        }

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(UPDATE);
            ps.setString(1, article.getNomArticle());
            ps.setString(2, article.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEnchere()));
            ps.setTimestamp(4, Timestamp.valueOf(article.getDateFinEnchere()));
            ps.setInt(5, article.getPrixInitial());
            ps.setInt(6, article.getPrixVente());
            ps.setInt(7, article.getUtilisateur().getIdUtilisateur());
            ps.setInt(8, article.getCategorie().getIdCategorie());
            ps.setString(9, article.getEtatVenteArticle().name());
            ps.setInt(10, article.getIdArticle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public void delete(ArticleVendu article) throws BusinessException {
        if (article == null) {
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
        }

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(DELETE);
            ps.setInt(1, article.getIdArticle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }
}
