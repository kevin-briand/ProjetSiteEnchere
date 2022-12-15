package fr.eni.team42.enchere.dal.jdbc;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Retrait;
import fr.eni.team42.enchere.dal.DALExceptionCode;
import fr.eni.team42.enchere.dal.RetraitDAO;

import java.sql.*;

public class RetraitJdbcImpl implements RetraitDAO {

    private final String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) " +
            "VALUES(?,?,?,?)";
    private final String UPDATE = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? " +
            "WHERE no_article=?";
    private final String DELETE = "DELETE FROM RETRAITS WHERE no_article=?";

    private final String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article=?";

    @Override
    public Retrait selectById(Integer articleId) throws BusinessException {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, articleId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Retrait(rs.getString(2), rs.getString(3), rs.getString(4));
            } else {
                throw new BusinessException(DALExceptionCode.UTILISATEUR_INCONNU);
            }
        } catch (Exception e) {
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    public Retrait insert(Retrait retrait) throws BusinessException {
        if (retrait == null || retrait.getArticle() == null)
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(INSERT);
            ps.setInt(1, retrait.getArticle().getIdArticle());
            ps.setString(2, retrait.getRue());
            ps.setString(3, retrait.getCodePostal());
            ps.setString(4, retrait.getVille());
            ps.executeUpdate();
            return retrait;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.INSERT_ERREUR);
        }
    }

    @Override
    public void update(Retrait retrait) throws BusinessException {
        if (retrait == null) {
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(UPDATE);
            ps.setString(1, retrait.getRue());
            ps.setString(2, retrait.getCodePostal());
            ps.setString(3, retrait.getVille());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }

    @Override
    public void delete(Retrait retrait) throws BusinessException {
        if (retrait == null) {
            throw new BusinessException(DALExceptionCode.INSERT_OBJET_NULL);
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement ps = cnx.prepareStatement(DELETE);
            ps.setInt(1, retrait.getArticle().getIdArticle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }
    }
}
