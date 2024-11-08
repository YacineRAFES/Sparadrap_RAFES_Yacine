package fr.afpa.dev.pompey.Modele.DAO;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.Adresses;
import fr.afpa.dev.pompey.Modele.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends DAO<Client> {

    @Override
    public int create(Client obj) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int newId = 0;
        String formattedDate = simpleDateFormat.format(obj.getDateNaissance());
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO client (CLI_nom, CLI_prenom, CLI_dateNaissance, CLI_numero_de_securite_social, COOR_ID, ADRES_ID, MUT_ID, MEDE_ID) ");
        insertSQL.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        try{
            PreparedStatement pstmt = connect.prepareStatement(insertSQL.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getPrenom());
            pstmt.setString(3, simpleDateFormat.format(obj.getDateNaissance()));
            pstmt.setString(4, obj.getNumeroSecuClient());
            pstmt.setInt(5, obj.getCoordonnees().getId());
            pstmt.setInt(6, obj.getAdresses().getId());
            pstmt.setInt(7, obj.getMutuelle().getId());
            pstmt.setInt(8, obj.getMedecin().getId());


            pstmt.setString(3, formattedDate);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newId = rs.getInt(1);
            }

            return newId;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Client obj) {

        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM client WHERE CLI_ID = ?");

        try{
            PreparedStatement pstmt = connect.prepareStatement(deleteSQL.toString());

            pstmt.setInt(1, obj.getId());

            pstmt.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Client obj) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE client SET CLI_nom = ?, CLI_prenom = ?, CLI_dateNaissance = ?, CLI_numero_de_securite_social = ?, COOR_ID = ?, ADRES_ID = ?, MUT_ID = ?, MEDE_ID = ? ");
        updateSQL.append("WHERE CLI_ID = ?");

        try{
            PreparedStatement pstmt = connect.prepareStatement(updateSQL.toString());

            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getPrenom());
            pstmt.setString(3, simpleDateFormat.format(obj.getDateNaissance()));
            pstmt.setString(4, obj.getNumeroSecuClient());
            pstmt.setInt(5, obj.getCoordonnees().getId());
            pstmt.setInt(6, obj.getAdresses().getId());
            pstmt.setInt(7, obj.getMutuelle().getId());
            pstmt.setInt(8, obj.getMedecin().getId());
            pstmt.setInt(9, obj.getId());

            pstmt.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client find(int id) {

        Client client = new Client(id);

        StringBuilder selectById = new StringBuilder();
        selectById.append("SELECT * FROM client WHERE CLI_ID = ?");

        try{
            PreparedStatement pstmt = connect.prepareStatement(selectById.toString());
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                client.setId(resultSet.getInt("CLI_ID"));
                client.setNom(resultSet.getString("CLI_nom"));
                client.setPrenom(resultSet.getString("CLI_prenom"));
                client.setDateNaissance(resultSet.getString("CLI_dateNaissance"));
                client.setNumeroSecuClient(resultSet.getString("CLI_numero_de_securite_social"));
                client.setCoordonnees(new CoordonneesDAO().find(resultSet.getInt("COOR_ID")));
                client.setAdresses(new AdressesDAO().find(resultSet.getInt("ADRES_ID")));
                client.setMutuelle(new MutuelleDAO().find(resultSet.getInt("MUT_ID")));
                client.setMedecin(new MedecinDAO().find(resultSet.getInt("MEDE_ID")));
            }

            return client;
        }catch(SQLException e){
            throw new RuntimeException(e);
        } catch (SaisieException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();
        StringBuilder selectSQL = new StringBuilder();
        selectSQL.append("SELECT * FROM client");

        try{
            Statement stmt = connect.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL.toString());

            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("CLI_ID"));
                client.setNom(resultSet.getString("CLI_nom"));
                client.setPrenom(resultSet.getString("CLI_prenom"));
                client.setDateNaissance(resultSet.getString("CLI_dateNaissance"));
                client.setNumeroSecuClient(resultSet.getString("CLI_numero_de_securite_social"));
                client.setCoordonnees(new CoordonneesDAO().find(resultSet.getInt("COOR_ID")));
                client.setAdresses(new AdressesDAO().find(resultSet.getInt("ADRES_ID")));
                client.setMutuelle(new MutuelleDAO().find(resultSet.getInt("MUT_ID")));
                client.setMedecin(new MedecinDAO().find(resultSet.getInt("MEDE_ID")));

                clients.add(client);
            }
        }catch(SQLException | SaisieException e){
            throw new RuntimeException(e);
        }

        return clients;
    }
}
