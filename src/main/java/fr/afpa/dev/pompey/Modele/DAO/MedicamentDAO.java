package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Medicament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO extends DAO<Medicament> {

    @Override
    public int create(Medicament obj) {
        int newId = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder insertSQL = new StringBuilder("INSERT INTO medicament (MED_nom, MED_miseEnService, MED_quantite, MED_prix, CAT_ID) VALUES (?, ?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.setDate(2, obj.getMiseEnService());
            pstmt.setInt(3, obj.getQuantite());
            pstmt.setDouble(4, obj.getPrix());
            pstmt.setInt(5, obj.getCategorie().getId());
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
        StringBuilder updateSQL = new StringBuilder("UPDATE medicament SET MED_nom = ?, MED_miseEnService = ?, MED_quantite = ?, MED_prix = ?, CAT_ID = ? WHERE MED_ID = ?");
        try{
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNom());
            pstmt.setDate(2, obj.getMiseEnService());
            pstmt.setInt(3, obj.getQuantite());
            pstmt.setDouble(4, obj.getPrix());
            pstmt.setInt(5, obj.getCategorie().getId());
            pstmt.setInt(6, obj.getId());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Medicament find(int id) {
        Medicament medicament = new Medicament();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM medicament WHERE MED_ID = ?");
        try{
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                medicament.setId(rs.getInt("MED_ID"));
                medicament.setNom(rs.getString("MED_nom"));
                medicament.setMiseEnService(rs.getDate("MED_miseEnService"));
                medicament.setQuantite(rs.getInt("MED_quantite"));
                medicament.setPrix(rs.getDouble("MED_prix"));
                medicament.setCategorie(new CategorieDAO().find(rs.getInt("CAT_ID")));
            }
        }catch (SQLException | SaisieException e){
            e.printStackTrace();
        }
        return medicament;
    }

    /**
     * @return
     */
    @Override
    public List<Medicament> findAll() {
        List<Medicament> medicamentList = new ArrayList<>();
        StringBuilder selectAll = new StringBuilder("SELECT * FROM medicament");
        try{
            PreparedStatement pstmt = connect.prepareStatement(selectAll.toString());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Medicament medicament = new Medicament();
                medicament.setId(rs.getInt("MED_ID"));
                medicament.setNom(rs.getString("MED_nom"));
                medicament.setMiseEnService(rs.getDate("MED_miseEnService"));
                medicament.setQuantite(rs.getInt("MED_quantite"));
                medicament.setPrix(rs.getDouble("MED_prix"));
                medicament.setCategorie(new CategorieDAO().find(rs.getInt("CAT_ID")));
                medicamentList.add(medicament);
            }
        }catch (SQLException | SaisieException e){
            e.printStackTrace();
        }
        return medicamentList;
    }
}
