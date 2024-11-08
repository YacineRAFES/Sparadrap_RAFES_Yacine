package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Ordonnances;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrdonnancesDAO extends DAO<Ordonnances> {

        @Override
        public int create(Ordonnances obj) {
            int newId = 0;
            StringBuilder insertSQL = new StringBuilder("INSERT INTO Ordonnances (ORDO_date, MEDE_ID, CLI_ID) VALUES (?, ?, ?)");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                        PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setDate(1, obj.getDate());
                pstmt.setInt(2, obj.getMedecin().getId());
                pstmt.setInt(3, obj.getClient().getId());
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

        @Override
        public boolean delete(Ordonnances obj) {
            return false;
        }

        @Override
        public boolean update(Ordonnances obj) {
            return false;
        }

        @Override
        public Ordonnances find(int id) {
            return null;
        }

    /**
     * @return
     */
    @Override
    public List<Ordonnances> findAll() {
        List<Ordonnances> ordonnances = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Ordonnances");
        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ordonnances ordonnance = new Ordonnances();
                ordonnance.setId(rs.getInt("ORDO_ID"));
                ordonnance.setDate(rs.getDate("ORDO_date"));
                ordonnances.add(ordonnance);
            }
        }catch (SQLException | SaisieException e){
            throw new RuntimeException(e);
        }
        return ordonnances;
    }
}
