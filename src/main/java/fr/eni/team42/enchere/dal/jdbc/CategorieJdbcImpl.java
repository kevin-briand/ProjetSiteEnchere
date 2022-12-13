package fr.eni.team42.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.team42.enchere.BusinessException;
import fr.eni.team42.enchere.bo.Categorie;
import fr.eni.team42.enchere.dal.CategorieDAO;
import fr.eni.team42.enchere.dal.DALExceptionCode;

public class CategorieJdbcImpl implements CategorieDAO {
	
	private final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";

	
	
	@Override
	public List<Categorie> selectAllCategories() throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet rs = pstmt.executeQuery();
            List<Categorie> categories = new ArrayList<>();
            while (rs.next()) {
                Categorie categorie = new Categorie(
                        rs.getInt(1),
                        rs.getString(2));
                categories.add(categorie);
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }

	}
	
	@Override
	public Categorie selectById(Integer id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Categorie(
                        rs.getInt(1),
                        rs.getString(2));
            }else {
            	throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(DALExceptionCode.GENERAL_ERREUR);
        }

	}

	@Override
	public void insert(Categorie obj) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categorie obj) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categorie obj) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


}
