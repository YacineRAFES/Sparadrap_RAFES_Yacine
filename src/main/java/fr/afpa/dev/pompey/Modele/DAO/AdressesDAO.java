package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.DAOException;
import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Ville;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdressesDAO extends DAO<Adresses> {
    @Override
    public int create(Adresses obj) {
        int newId = 0;

        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO adresses (ADRES_rue, VIL_ID)");
        insertSQL.append(" VALUES (?, ?)");

        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getRue());
            pstmt.setInt(2, obj.getVille().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newId;
    }

    @Override
    public boolean delete(Adresses obj) {
        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM adresses WHERE ADRES_ID = ?");

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

    @Override
    public boolean update(Adresses obj) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE adresses SET ADRES_rue = ?, VILL_ID = ? WHERE ADRES_ID = ?");
        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getRue());
            pstmt.setInt(2, obj.getVille().getId());
            pstmt.setInt(3, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adresses find(int id) {
        Adresses adresses = new Adresses(id);

        StringBuilder selectById = new StringBuilder("SELECT * FROM adresses WHERE ADRES_ID = ?");

        try {
            PreparedStatement pstmt = connect.prepareStatement(selectById.toString());
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                adresses.setRue(resultSet.getString("ADRES_rue"));
                adresses.setVille(new Ville(resultSet.getInt("VIL_ID")));
            }

            return adresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Adresses> findAll() {
        List<Adresses> adressesList = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM adresses");

        try {
            Statement stmt = connect.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL.toString());
            while (resultSet.next()) {
                Adresses adresses = new Adresses();
                adresses.setId(resultSet.getInt("ADRES_ID"));
                adresses.setRue(resultSet.getString("ADRES_rue"));
                adresses.setVille(new Ville(resultSet.getInt("VIL_ID")));
                adressesList.add(adresses);
            }
        } catch (DAOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return adressesList;
    }
}