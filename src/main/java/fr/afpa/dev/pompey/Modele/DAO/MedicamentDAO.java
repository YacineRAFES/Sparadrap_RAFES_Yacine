package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Medicament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MedicamentDAO extends DAO<Medicament> {

    @Override
    public int create(Medicament obj) {
        int newId = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder insertSQL = new StringBuilder("INSERT INTO medicament (MED_nom, MED_prix, MED_date_peremption, CAT_ID) VALUES (?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.setDouble(2, obj.getPrix());
            pstmt.setDate(3, obj.getMiseEnService());
            pstmt.setInt(4, obj.getCategorie().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newId = rs.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return newId;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Medicament obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM medicament WHERE MED_ID = ?");
        try{
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean update(Medicament obj) {
        return false;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Medicament find(int id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Medicament> findAll() {
        return List.of();
    }
}
