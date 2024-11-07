package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Client;
import fr.afpa.dev.pompey.Modele.Medecin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedecinDAO extends DAO<Medecin> {

    @Override
    public int create(Medecin obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder("INSERT INTO Medecin (MEDE_numAgreement, MEDE_specialite, MEDE_nom, MEDE_prenom, ADRES_ID, COOR_ID) VALUES (?, ?, ?, ?, ?, ?)");

        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNumAgreement());
            pstmt.setString(2, obj.getSpecialite());
            pstmt.setString(3, obj.getNom());
            pstmt.setString(4, obj.getPrenom());
            pstmt.setInt(5, obj.getAdresses().getId());
            pstmt.setInt(6, obj.getCoordonnees().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newId;
    }

    @Override
    public boolean delete(Medecin obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM Medecin WHERE MEDE_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Medecin obj) {
        StringBuilder updateSQL = new StringBuilder("UPDATE Medecin SET MEDE_numAgreement = ?, MEDE_specialite = ?, MEDE_nom = ?, MEDE_prenom = ?, ADRES_ID = ?, COOR_ID = ? WHERE MEDE_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNumAgreement());
            pstmt.setString(2, obj.getSpecialite());
            pstmt.setString(3, obj.getNom());
            pstmt.setString(4, obj.getPrenom());
            pstmt.setInt(5, obj.getAdresses().getId());
            pstmt.setInt(6, obj.getCoordonnees().getId());
            pstmt.setInt(7, obj.getId());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Medecin find(int id) {
        Medecin medecin = new Medecin(id);

        StringBuilder selectById = new StringBuilder("SELECT * FROM Medecin WHERE MEDE_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectById.toString());
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                medecin = new Medecin();
                medecin.setId(rs.getInt("MEDE_ID"));
                medecin.setNumAgreement(rs.getString("MEDE_numAgreement"));
                medecin.setSpecialite(rs.getString("MEDE_specialite"));
                medecin.setNom(rs.getString("MEDE_nom"));
                medecin.setPrenom(rs.getString("MEDE_prenom"));
                medecin.setAdresses(new AdressesDAO().find(rs.getInt("ADRES_ID")));
                medecin.setCoordonnees(new CoordonneesDAO().find(rs.getInt("COOR_ID")));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException | SaisieException e) {
            e.printStackTrace();
        }
        return medecin;
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> medecinList = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Medecin");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Medecin medecin = new Medecin();
                medecin.setId(rs.getInt("MEDE_ID"));
                medecin.setNumAgreement(rs.getString("MEDE_numAgreement"));
                medecin.setSpecialite(rs.getString("MEDE_specialite"));
                medecin.setNom(rs.getString("MEDE_nom"));
                medecin.setPrenom(rs.getString("MEDE_prenom"));
                medecin.setAdresses(new AdressesDAO().find(rs.getInt("ADRES_ID")));
                medecin.setCoordonnees(new CoordonneesDAO().find(rs.getInt("COOR_ID")));
                medecinList.add(medecin);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException | SaisieException e) {
            e.printStackTrace();
        }
        return medecinList;
    }
}
