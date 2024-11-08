package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.DAOException;
import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.AchatDirect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchatDirectDAO extends DAO<AchatDirect> {

    @Override
    public int create(AchatDirect obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder("INSERT INTO achatdirect (ACH_date, CLI_ID) VALUES (?, ?)");

        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setDate(1, obj.getDate());
            pstmt.setInt(2, obj.getClient().getId());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    newId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public boolean delete(AchatDirect obj) {
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM AchatDirect WHERE ACH_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AchatDirect obj) {
        StringBuilder updateSQL = new StringBuilder("UPDATE AchatDirect SET CLI_ID = ? WHERE ACH_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setInt(2, obj.getClient().getId());
            pstmt.setInt(3, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AchatDirect find(int id) {
        AchatDirect achatDirect = new AchatDirect();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM AchatDirect WHERE ACH_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    achatDirect = new AchatDirect();
                    achatDirect.setId(rs.getInt("ACH_ID"));
                    achatDirect.setDate(rs.getDate("ACH_date"));
                    achatDirect.setClient(new ClientDAO().find(rs.getInt("CLI_ID")));
                }
            } catch (SaisieException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achatDirect;
    }

    @Override
    public List<AchatDirect> findAll() {
        List<AchatDirect> achatDirectList = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder("SELECT * FROM AchatDirect");

        try {
            Statement stmt = connect.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL.toString());
            while (resultSet.next()) {
                AchatDirect achatDirect = new AchatDirect();
                achatDirect.setId(resultSet.getInt("ACH_ID"));
                achatDirect.setDate(resultSet.getDate("ACH_date"));
                achatDirect.setClient(new ClientDAO().find(resultSet.getInt("CLI_ID")));
                achatDirectList.add(achatDirect);
            }
        } catch (DAOException | SQLException | SaisieException e) {
            throw new RuntimeException(e);
        }
        return achatDirectList;
    }
}