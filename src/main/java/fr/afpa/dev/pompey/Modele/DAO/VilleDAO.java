package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Ville;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VilleDAO extends DAO<Ville> {

    @Override
    public int create(Ville obj) {
        int newId = 0;

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO ville (VIL_nomVille, VIL_codePostal, REG_ID)");
        insertSQL.append(" VALUES (?, ?, ?)");
        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getCp());
            pstmt.setInt(3, obj.getRegion().getId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public boolean delete(Ville obj) {
        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM ville WHERE VIL_ID = ?");

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
    public boolean update(Ville obj) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE ville SET VIL_nomVille = ?, VIL_codePostal = ?, REG_ID = ? WHERE VIL_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getCp());
            pstmt.setInt(3, obj.getRegion().getId());
            pstmt.setInt(4, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Ville find(int id) {
        Ville ville = new Ville(id);
        StringBuilder updateSQL = new StringBuilder("SELECT * FROM ville WHERE VIL_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ville.setNom(rs.getString("VIL_nomVille"));
                    ville.setCp(rs.getString("VIL_codePostal"));
                    ville.setRegion(new RegionDAO().find(rs.getInt("REG_ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ville;
    }

    @Override
    public List<Ville> findAll() {
        List<Ville> villes = new ArrayList<>();
        StringBuilder selectAll = new StringBuilder("SELECT * FROM ville");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectAll.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Ville ville = new Ville();
                    ville.setId(rs.getInt("VIL_ID"));
                    ville.setNom(rs.getString("VIL_nomVille"));
                    ville.setCp(rs.getString("VIL_codePostal"));
                    ville.setRegion(new RegionDAO().find(rs.getInt("REG_ID")));
                    villes.add(ville);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public Ville findByName(String name) {
        Ville ville = new Ville();
        StringBuilder selectByName = new StringBuilder("SELECT * FROM ville WHERE VIL_nomVille = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectByName.toString());
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ville.setId(rs.getInt("VIL_ID"));
                    ville.setNom(rs.getString("VIL_nomVille"));
                    ville.setCp(rs.getString("VIL_codePostal"));
                    ville.setRegion(new RegionDAO().find(rs.getInt("REG_ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ville;
    }
}
