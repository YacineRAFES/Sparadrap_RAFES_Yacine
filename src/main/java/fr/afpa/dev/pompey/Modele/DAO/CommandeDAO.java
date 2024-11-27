package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends DAO<Commande> {

    @Override
    public int create(Commande obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO Commande (MED_ID, ACH_ID, quantite)");
        insertSQL.append(" VALUES (?, ?, ?)");
        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getMedicament().getId());
            pstmt.setInt(2, obj.getAchatDirect().getId());
            pstmt.setInt(3, obj.getQuantite());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Commande obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM Commande WHERE ACH_ID = ? OR MED_ID = ?");

        try (PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString())) {
            pstmt.setInt(1, obj.getAchatDirect().getId());
            pstmt.setInt(2, obj.getMedicament().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Commande obj) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE Commande SET quantite = ? WHERE MED_ID = ? AND ACH_ID = ?");
        try (PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString())) {
            pstmt.setInt(1, obj.getQuantite());
            pstmt.setInt(2, obj.getMedicament().getId());
            pstmt.setInt(3, obj.getAchatDirect().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Commande find(int id) {
        Commande commande = null;
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM Commande WHERE MED_ID = ? AND ACH_ID = ?");
        try (PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString())) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                commande = new Commande();
                commande.setQuantite(rs.getInt("quantite"));
            }
            return commande;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Commande> findAll() {

        List<Commande> commandesList = new ArrayList<>();
        StringBuilder selectAllSQL = new StringBuilder();
        selectAllSQL.append("SELECT * FROM Commande");

        try (PreparedStatement pstmt = connect.prepareStatement(selectAllSQL.toString());
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Commande commande = new Commande();
                commande.setMedicament(new MedicamentDAO().find(rs.getInt("MED_ID")));
                commande.setAchatDirect(new AchatDirectDAO().find(rs.getInt("ACH_ID")));
                commande.setQuantite(rs.getInt("quantite"));
                commandesList.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandesList;
    }

    public List<Commande> findAllByAchatDirect(int id) {
        List<Commande> commandesList = new ArrayList<>();
        StringBuilder selectAllSQL = new StringBuilder();
        selectAllSQL.append("SELECT * FROM Commande WHERE ACH_ID = ?");
        try (PreparedStatement pstmt = connect.prepareStatement(selectAllSQL.toString())) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setMedicament(new MedicamentDAO().find(rs.getInt("MED_ID")));
                commande.setAchatDirect(new AchatDirectDAO().find(rs.getInt("ACH_ID")));
                commande.setQuantite(rs.getInt("quantite"));
                commandesList.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandesList;
    }
}
