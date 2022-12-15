package fr.eni.team42.enchere.dal.jdbc;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DALExceptionCode;
import fr.eni.team42.enchere.dal.DAOFactory;
import fr.eni.team42.enchere.dal.EnchereDAO;

public class EnchereJdbcImpl implements EnchereDAO {

	private final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) " +
            "VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
    private final String DELETE = "DELETE FROM ENCHERES WHERE no_utilisateur=? AND no_article=?";
    private final String SELECT_BY_ARTICLE_ID = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY montant_enchere DESC";
    private final String SELECT_BY_USER_ID_AND_ARTICLE_ID = "SELECT * FROM ENCHERES WHERE no_utilisateur=? AND no_article=?";
    private final String SELECT_BY_USER = "SELECT * FROM ENCHERES WHERE no_utilisateur=?";

    
	@Override
	public List<Enchere> selectById(int idArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE_ID);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            List<Enchere> encheres =  new ArrayList<>();
            ArticleVendu article = DAOFactory.getArticleDAO().selectById(idArticle);
            while(rs.next()) {
            	Utilisateur u = (Utilisateur) rs.getObject(1);
                Enchere enchere = new Enchere (u, article,rs.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getInt(4));
                encheres.add(enchere);
            }
            return encheres;
        } catch (Exception e) {
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }

	}

	@Override
	public Enchere selectById(int idArticle, int idUser) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID_AND_ARTICLE_ID);
            pstmt.setInt(1, idUser);
            pstmt.setInt(2, idArticle);
            ResultSet rs = pstmt.executeQuery();
            Utilisateur user = DAOFactory.getUtilisateurDAO().selectById(idUser);
            ArticleVendu article = DAOFactory.getArticleDAO().selectById(idArticle);
            if(rs.next()) {
                return new Enchere(user,article,rs.getTimestamp(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getInt(4));
            }
            return null;
        } catch (Exception e) {
        	
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
	}

	@Override
	public List<Enchere> selectByUser(int idUser) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER);
			pstmt.setInt(1, idUser);
			ResultSet rs = pstmt.executeQuery();
			Utilisateur user = DAOFactory.getUtilisateurDAO().selectById(idUser);
			List<Enchere> listE = new ArrayList<>();
			while(rs.next()) {
				listE.add(new Enchere(user, null, rs.getTimestamp(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getInt(4)));
			}
			return listE;
		} catch (Exception e) {
			throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
		}
	}

	@Override
	public Enchere insert(Enchere enchere) throws BusinessException {
		 if(enchere==null)
	            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
		 
        try(Connection cnx = ConnectionProvider.getConnection()) {
        	PreparedStatement pstmt = cnx.prepareStatement(INSERT);
        	pstmt.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
            pstmt.setInt(2, enchere.getArticleVendu().getIdArticle());
            pstmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
            pstmt.setInt(4, enchere.getMontantEnchere());
            pstmt.executeUpdate();
            return enchere;
           
        }catch(SQLException e) {
            throw new BusinessException(DALExceptionCode.INSERT_ERREUR);
        }
	}

	@Override
	public void update(Enchere enchere) throws BusinessException {
		 if(enchere==null)
	            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
		 
		 try(Connection cnx = ConnectionProvider.getConnection()) {
	            PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
	            pstmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
	            pstmt.setInt(2, enchere.getMontantEnchere());
	            pstmt.setInt(3, enchere.getUtilisateur().getIdUtilisateur());
	            pstmt.setInt(4, enchere.getArticleVendu().getIdArticle());
	            pstmt.executeUpdate();
		 }catch(SQLException e) {
			 throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
		 }
	}

	@Override
	public void delete(Enchere enchere) throws BusinessException {
		 if(enchere==null)
	            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
		 
		 try(Connection cnx = ConnectionProvider.getConnection()) {
	            PreparedStatement pstmt = cnx.prepareStatement(DELETE);
	            pstmt.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
	            pstmt.setInt(2, enchere.getArticleVendu().getIdArticle());
		 }catch(SQLException e) {
			 throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
		 }
	}

	@Override
	public Enchere selectById(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
