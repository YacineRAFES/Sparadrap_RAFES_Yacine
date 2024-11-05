package fr.afpa.dev.pompey.Modele.db;

import fr.afpa.dev.pompey.Modele.Client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class connexiondb {

    public static void main(String[] args) {
        new connexiondb().propertiesConnection();
    }

    private void propertiesConnection() {

//        final String PATHCONF = "src/ressources/conf.properties";
//
//        Properties prop = new Properties();
//
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream(PATHCONF)) {
//            prop.load(is);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//
//        }
//
//        try{
//            Class.forName(prop.getProperty("jdbc.driver.class"));
//
//            String url = prop.getProperty("jdbc.url");
//            String user = prop.getProperty("jdbc.login");
//            String password = prop.getProperty("jdbc.password");
//
//            Connection connection = DriverManager.getConnection(url, user, password);
//
//            System.out.println("Connexion à la base de données réussie !");
//
//            connection.close();
//        }catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        String BDD = "sparadrap";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données réussie !");
            selectFromPersonne(connection);
            connection.close();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void selectFromPersonne(Connection conn){
        String selectSQL = "SELECT * FROM personne";

        try{
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()){
                System.out.println(resultSet.getString("PER_NOM"));
                System.out.println(resultSet.getString("PER_PRENOM"));
                System.out.println(resultSet.getString("PER_EMAIL"));
                System.out.println(resultSet.getString("PER_TEL"));
                System.out.println(resultSet.getString("PER_ADR"));
                System.out.println(resultSet.getString("PER_CP"));
                System.out.println(resultSet.getString("PER_VILLE"));
                System.out.println(resultSet.getString("PER_DATENAISSANCE"));
                System.out.println("-----------------------------");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private List<Client> findAll(Connection conn){

        List<Client> clients = new ArrayList<>();
        String selectSQL = "SELECT * FROM client";

        try{
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("CLI_ID"));
                client.setNom(resultSet.getString("CLI_NOM"));
                client.setPrenom(resultSet.getString("CLI_PRENOM"));
                client.setNumeroSecuClient(resultSet.getString("CLI_numero_de_securite_sociale"));
                client.setMutuelle(resultSet.getInt("MUT_ID"));

                personnes.add(client);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void Client findById(Connection conn, int id){

        String selectById = "SELECT * FROM client WHERE PER_ID = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(selectById);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                client.setId(resultSet.getInt("PER_ID"));
                client.setNom(resultSet.getString("PER_NOM"));
                client.setPrenom(resultSet.getString("PER_PRENOM"));
                client.setEmail(resultSet.getString("PER_EMAIL"));
                client.setTel(resultSet.getString("PER_TEL"));
                client.setAdr(resultSet.getString("PER_ADR"));
                client.setCp(resultSet.getString("PER_CP"));
                client.setVille(resultSet.getString("PER_VILLE"));
                client.setDateNaissance(resultSet.getString("PER_DATENAISSANCE"));
            }
            return client;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}
