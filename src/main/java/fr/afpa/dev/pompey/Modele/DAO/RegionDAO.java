package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Modele.Region;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO extends DAO<Region> {
    @Override
    public int create(Region obj) {
        int newId = 0;
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO regions (REG_nomRegion)");
        insertSQL.append(" VALUES (?)");
        try {
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
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
    public boolean delete(Region obj) {
        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM regions WHERE REG_ID = ?");
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
    public boolean update(Region obj) {
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE regions SET REG_nomRegion = ? WHERE REG_ID = ?");
        try {
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());
            pstmt.setString(1, obj.getNom());
            pstmt.setInt(2, obj.getId());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Region find(int id) {
        Region region = new Region();
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM regions WHERE REG_ID = ?");
        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                region.setId(rs.getInt("REG_ID"));
                region.setNom(rs.getString("REG_nomRegion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }
    @Override
    public List<Region> findAll() {
        List<Region> regions = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM regions");
        try {
            PreparedStatement pstmt = connect.prepareStatement(selectSQL.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Region region = new Region();
                region.setId(rs.getInt("REG_ID"));
                region.setNom(rs.getString("REG_nomRegion"));
                regions.add(region);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regions;
    }

}
