package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Categorie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO extends DAO<Categorie> {

    @Override
    public int create(Categorie obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder("INSERT INTO Categorie (CAT_nom) VALUES (?)");

        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newId;
    }

    @Override
    public boolean delete(Categorie obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM Categorie WHERE CAT_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Categorie obj) {
        StringBuilder updateSQL = new StringBuilder("UPDATE Categorie SET CAT_nom = ? WHERE CAT_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNom());
            pstmt.setInt(2, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Categorie find(int id) {
        Categorie categorie = new Categorie();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Categorie WHERE CAT_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                categorie.setId(rs.getInt("CAT_ID"));
                categorie.setNom(rs.getString("CAT_nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SaisieException e) {
            throw new RuntimeException(e);
        }
        return categorie;
    }

    @Override
    public List<Categorie> findAll() {
        List<Categorie> categories = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Categorie");

        try{
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("CAT_ID"));
                categorie.setNom(rs.getString("CAT_nom"));
                categories.add(categorie);
            }
        } catch (SQLException | SaisieException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
