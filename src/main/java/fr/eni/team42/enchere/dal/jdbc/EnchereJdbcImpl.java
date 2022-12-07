package fr.eni.team42.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.ArticleVendu;
import fr.eni.team42.enchere.bo.Enchere;
import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.DALExceptionCode;
import fr.eni.team42.enchere.dal.EnchereDAO;

public class EnchereJdbcImpl implements EnchereDAO {

	private final String INSERT = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere)" +
            "VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE encheres SET date_enchere=?, montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
    private final String DELETE = "DELETE FROM encheres WHERE no_utilisateur=? AND no_article=?";
    private final String SELECT_BY_ID = "SELECT * FROM encheres WHERE no_utilisateur=? AND no_article=?";

	@Override
	public Enchere selectById(Utilisateur utilisateur, ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, utilisateur.getIdUtilisateur());
            pstmt.setInt(2, article.getIdArticle());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Enchere(utilisateur,article,rs.getDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), rs.getInt(4));
            } else {
                throw new BusinessException(DALExceptionCode.UTILISATEUR_INCONNU);
            }

        } catch (Exception e) {
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }

	}

	@Override
	public void insert(Enchere enchere) throws BusinessException {
		 if(enchere==null)
	            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
		 
        try(Connection cnx = ConnectionProvider.getConnection()) {
        	PreparedStatement pstmt = cnx.prepareStatement(INSERT);
        	pstmt.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
            pstmt.setInt(2, enchere.getArticleVendu().getIdArticle());
            pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere().toLocalDate()));
            pstmt.setInt(4, enchere.getMontantEnchere());
            pstmt.executeUpdate();
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
	            pstmt.setDate(1, Date.valueOf(enchere.getDateEnchere().toLocalDate()));
	            pstmt.setInt(2, enchere.getMontantEnchere());
	            pstmt.setInt(3, enchere.getUtilisateur().getIdUtilisateur());
	            pstmt.setInt(4, enchere.getArticleVendu().getIdArticle());
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
		return null;
	}

}
