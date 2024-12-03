package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Demande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemandeDAO extends DAO<Demande> {

    @Override
    public int create(Demande obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder("INSERT INTO demande (ORDO_ID, MED_ID, quantite) VALUES (?, ?, ?)");

        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getOrdonnances().getId());
            pstmt.setInt(2, obj.getMedicament().getId());
            pstmt.setInt(3, obj.getQuantite());
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
    public boolean delete(Demande obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM Demande WHERE ORDO_ID = ? OR MED_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getOrdonnances().getId());
            pstmt.setInt(2, obj.getMedicament().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Demande obj) {
        StringBuilder updateSQL = new StringBuilder("UPDATE Demande SET quantite = ? WHERE ORDO_ID = ? OR MED_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setInt(1, obj.getQuantite());
            pstmt.setInt(2, obj.getOrdonnances().getId());
            pstmt.setInt(3, obj.getMedicament().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Demande find(int id) {
        Demande demande = new Demande();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Demande WHERE DEMA_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                demande.setOrdonnances(new OrdonnancesDAO().find(rs.getInt("ORDO_ID")));
                demande.setMedicament(new MedicamentDAO().find(rs.getInt("MED_ID")));
                demande.setQuantite(rs.getInt("quantite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SaisieException e) {
            throw new RuntimeException(e);
        }
        return demande;
    }

    /**
     * @return
     */
    @Override
    public List<Demande> findAll() {
        List<Demande> demandes = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Demande");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Demande demande = new Demande();
                demande.setOrdonnances(new OrdonnancesDAO().find(rs.getInt("ORDO_ID")));
                demande.setMedicament(new MedicamentDAO().find(rs.getInt("MED_ID")));
                demande.setQuantite(rs.getInt("quantite"));
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SaisieException e) {
            new SaisieException(e.getMessage());
        }
        return demandes;
    }

    public List<Demande> findAllByOrdonnance(int id) {
        List<Demande> demandes = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Demande WHERE ORDO_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Demande demande = new Demande();
                demande.setOrdonnances(new OrdonnancesDAO().find(rs.getInt("ORDO_ID")));
                demande.setMedicament(new MedicamentDAO().find(rs.getInt("MED_ID")));
                demande.setQuantite(rs.getInt("quantite"));
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SaisieException e) {
            new SaisieException(e.getMessage());
        }
        return demandes;
    }

}