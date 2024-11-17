package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Coordonnees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordonneesDAO extends DAO<Coordonnees> {

    @Override
    public int create(Coordonnees obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder("INSERT INTO Coordonnees (email, telephone) VALUES (?, ?)");


        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getTelephone());
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
    public boolean delete(Coordonnees obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM Coordonnees WHERE COOR_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Coordonnees obj) {
        StringBuilder updateSQL = new StringBuilder("UPDATE Coordonnees SET email = ?, telephone = ? WHERE COOR_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getTelephone());
            pstmt.setInt(3, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Coordonnees find(int id) {
        Coordonnees coordonnees = null;
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Coordonnees WHERE COOR_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    coordonnees = new Coordonnees();
                    coordonnees.setId(rs.getInt("COOR_ID"));
                    coordonnees.setEmail(rs.getString("email"));
                    coordonnees.setTelephone(rs.getString("telephone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonnees;
    }

    @Override
    public List<Coordonnees> findAll() {

        List<Coordonnees> coordonneesList = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Coordonnees");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Coordonnees coordonnees = new Coordonnees();
                    coordonnees.setId(rs.getInt("COOR_ID"));
                    coordonnees.setEmail(rs.getString("email"));
                    coordonnees.setTelephone(rs.getString("telephone"));
                    coordonneesList.add(coordonnees);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonneesList;
    }
}
