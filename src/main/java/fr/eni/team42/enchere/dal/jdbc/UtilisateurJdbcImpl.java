package fr.eni.team42.enchere.dal.jdbc;

import fr.eni.team42.enchere.bo.Utilisateur;
import fr.eni.team42.enchere.dal.UtilisateurDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilisateurJdbcImpl implements UtilisateurDAO {
    private final String INSERT = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, " +
            "telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, " +
            "code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?";
    private final String DELETE = "DELETE FROM utilisateurs WHERE no_utilisateur=?";
    private final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur=?";
    private final String SELECT_BY_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo=?";
    private final String SELECT_BY_EMAIL = "SELECT * FROM utilisateurs WHERE email=?";

    @Override
    public Utilisateur selectById(Integer id) throws Exception {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Utilisateur(rs.getInt(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getBoolean(12));
            }

        } catch (Exception e) {
            throw new Exception("Echec de la sélection de l'utilisateur");
        }
        return null;
    }

    @Override
    public Utilisateur selectByPseudo(String pseudo) throws Exception {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
            pstmt.setString(1, pseudo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Utilisateur(rs.getInt(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getBoolean(12));
            }

        } catch (Exception e) {
            throw new Exception("Echec de la sélection de l'utilisateur");
        }
        return null;
    }

    @Override
    public Utilisateur selectByEmail(String email) throws Exception {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new Utilisateur(rs.getInt(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getBoolean(12));
            }

        } catch (Exception e) {
            throw new Exception("Echec de la sélection de l'utilisateur");
        }
        return null;
    }

    @Override
    public void insert(Utilisateur utilisateur) throws Exception {
        if(utilisateur==null)
            throw new Exception("Utilisateur null");

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, utilisateur.getPseudo());
            pstmt.setString(2, utilisateur.getNom());
            pstmt.setString(3, utilisateur.getPrenom());
            pstmt.setString(4, utilisateur.getEmail());
            pstmt.setString(5, utilisateur.getTelephone());
            pstmt.setString(6, utilisateur.getRue());
            pstmt.setString(7, utilisateur.getCodePostal());
            pstmt.setString(8, utilisateur.getVille());
            pstmt.setString(9, utilisateur.getMdp());
            pstmt.setInt(10, 0);
            pstmt.setBoolean(11, utilisateur.isAdmin());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                utilisateur.setIdUtilisateur(rs.getInt(1));
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Une erreur s'est produite lors de la création de l'utilisateur");
        }
    }

    @Override
    public void update(Utilisateur utilisateur) throws Exception {
        if(utilisateur == null)
            throw new Exception("Utilisateur null");

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
            pstmt.setString(1, utilisateur.getPseudo());
            pstmt.setString(2, utilisateur.getNom());
            pstmt.setString(3, utilisateur.getPrenom());
            pstmt.setString(4, utilisateur.getEmail());
            pstmt.setString(5, utilisateur.getTelephone());
            pstmt.setString(6, utilisateur.getRue());
            pstmt.setString(7, utilisateur.getCodePostal());
            pstmt.setString(8, utilisateur.getVille());
            pstmt.setString(9, utilisateur.getMdp());
            pstmt.setInt(10, 0);
            pstmt.setBoolean(11, utilisateur.isAdmin());
            pstmt.setInt(12, utilisateur.getIdUtilisateur());
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Une erreur s'est produite lors de la mise à jour de l'utilisateur");
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) throws Exception {
        if(utilisateur == null)
            throw new Exception("Utilisateur null");

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE);
            pstmt.setInt(1, utilisateur.getIdUtilisateur());
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Une erreur s'est produite lors de la suppression de l'utilisateur");
        }
    }

}
