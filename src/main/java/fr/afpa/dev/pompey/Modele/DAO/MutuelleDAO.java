package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Mutuelle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MutuelleDAO extends DAO<Mutuelle> {
    @Override
    public int create(Mutuelle obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO mutuelle (MUT_nom, MUT_TxPriseEnCharge, ADRES_ID, COOR_ID)");
        insertSQL.append(" VALUES (?, ?)");
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.setInt(2, obj.getTauxDePriseEnCharge());
            pstmt.setInt(3, obj.getAdresses().getId());
            pstmt.setInt(4, obj.getCoordonnees().getId());
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

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Mutuelle obj) {
        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM mutuelle WHERE MUT_ID = ?");

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

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean update(Mutuelle obj) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE mutuelle SET MUT_nom = ?, MUT_TxPriseEnCharge = ?, ADRES_ID = ?, COOR_ID = ? WHERE MUT_ID = ?");
        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNom());
            pstmt.setInt(2, obj.getTauxDePriseEnCharge());
            pstmt.setInt(3, obj.getAdresses().getId());
            pstmt.setInt(4, obj.getCoordonnees().getId());
            pstmt.setInt(5, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mutuelle find(int id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Mutuelle> findAll() {
        return List.of();
    }
}
