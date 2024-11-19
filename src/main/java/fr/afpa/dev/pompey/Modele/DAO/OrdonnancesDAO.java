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
            StringBuilder deleteSQL = new StringBuilder("DELETE FROM Ordonnances WHERE ORDO_ID = ?");
            try {
                PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
                pstmt.setInt(1, obj.getId());
                pstmt.executeUpdate();
                pstmt.close();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean update(Ordonnances obj) {
            StringBuilder updateSQL = new StringBuilder("UPDATE Ordonnances SET ORDO_date = ?, MEDE_ID = ?, CLI_ID = ? WHERE ORDO_ID = ?");
            try {
                PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
                pstmt.setDate(1, obj.getDate());
                pstmt.setInt(2, obj.getMedecin().getId());
                pstmt.setInt(3, obj.getClient().getId());
                pstmt.setInt(4, obj.getId());
                pstmt.executeUpdate();
                pstmt.close();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Ordonnances find(int id) {
            Ordonnances ordonnance = new Ordonnances();
            StringBuilder selectSQL = new StringBuilder("SELECT * FROM Ordonnances WHERE ORDO_ID = ?");
            try {
                PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    ordonnance.setId(rs.getInt("ORDO_ID"));
                    ordonnance.setDate(rs.getDate("ORDO_date"));
                    ordonnance.setMedecin(new MedecinDAO().find(rs.getInt("MEDE_ID")));
                    ordonnance.setClient(new ClientDAO().find(rs.getInt("CLI_ID")));
                }
                return ordonnance;
            } catch (SQLException | SaisieException e) {
                throw new RuntimeException(e);
            }
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

    public List<Ordonnances> findAllByIdMed(int id){
        List<Ordonnances> ordonnances = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM Ordonnances WHERE MEDE_ID = ?");
        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
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
