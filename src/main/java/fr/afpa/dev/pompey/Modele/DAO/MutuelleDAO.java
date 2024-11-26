package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Mutuelle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MutuelleDAO extends DAO<Mutuelle> {
    @Override
    public int create(Mutuelle obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO mutuelle (MUT_nom, MUT_TxPriseEnCharge, ADRES_ID, COOR_ID)");
        insertSQL.append(" VALUES (?, ?, ?, ?)");
        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());

            if(obj.getTauxDePriseEnCharge() == 0) {
                pstmt.setNull(2, java.sql.Types.NULL);
            }else{
                pstmt.setInt(2, obj.getTauxDePriseEnCharge());
            }

            if (obj.getAdresses().getId() == 0) {
                pstmt.setNull(3, java.sql.Types.NULL);
            } else {
                pstmt.setInt(3, obj.getAdresses().getId());
            }

            if (obj.getCoordonnees().getId() == 0) {
                pstmt.setNull(4, java.sql.Types.NULL);
            } else {
                pstmt.setInt(4,  obj.getCoordonnees().getId());
            }

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newId = rs.getInt(1);
            }
            return newId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

        Mutuelle mutuelle = new Mutuelle(id);
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM mutuelle WHERE MUT_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                mutuelle.setNom(rs.getString("MUT_nom"));
                mutuelle.setTauxDePriseEnCharge(rs.getInt("MUT_TxPriseEnCharge"));
                mutuelle.setAdresses(new AdressesDAO().find(rs.getInt("ADRES_ID")));
                mutuelle.setCoordonnees(new CoordonneesDAO().find(rs.getInt("COOR_ID")));
            }
            return mutuelle;
        } catch (SQLException | SaisieException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return
     */
    @Override
    public List<Mutuelle> findAll() {
       List<Mutuelle> mutuelles = new ArrayList<>();
       StringBuilder selectSQL = new StringBuilder();
       selectSQL.append("SELECT * FROM mutuelle");

       try {
           Statement stmt = connect.createStatement();
           ResultSet rs = stmt.executeQuery(selectSQL.toString());

           while(rs.next()) {
               Mutuelle mutuelle = new Mutuelle();
               mutuelle.setId(rs.getInt("MUT_ID"));
               mutuelle.setNom(rs.getString("MUT_nom"));
               mutuelle.setTauxDePriseEnCharge(rs.getInt("MUT_TxPriseEnCharge"));
               mutuelle.setAdresses(new AdressesDAO().find(rs.getInt("ADRES_ID")));
               mutuelle.setCoordonnees(new CoordonneesDAO().find(rs.getInt("COOR_ID")));
               mutuelles.add(mutuelle);
           }
       } catch (SQLException | SaisieException e) {
           throw new RuntimeException(e);
       }

       return mutuelles;
    }

    public Mutuelle findByName(String name) {
        Mutuelle mutuelle = new Mutuelle();
        StringBuilder selectByName = new StringBuilder("SELECT * FROM mutuelle WHERE MUT_nom = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectByName.toString());
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mutuelle.setId(rs.getInt("MUT_ID"));
                    mutuelle.setNom(rs.getString("MUT_nom"));
                    mutuelle.setTauxDePriseEnCharge(rs.getInt("MUT_TxPriseEnCharge"));
                    mutuelle.setAdresses(new AdressesDAO().find(rs.getInt("ADRES_ID")));
                    mutuelle.setCoordonnees(new CoordonneesDAO().find(rs.getInt("COOR_ID")));
                }
            }
        } catch (SQLException | SaisieException e) {
            e.printStackTrace();
        }
        return mutuelle;
    }
}
