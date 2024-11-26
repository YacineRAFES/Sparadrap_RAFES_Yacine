package fr.afpa.dev.pompey.Modele.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class Singleton {

    private static final Properties props = new Properties();
    private static Connection connection;
    final String PATHCONF = "conf.properties";

    private Singleton(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(PATHCONF)) {

            props.load(is);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.login");
            String password = props.getProperty("jdbc.password");

            Class.forName(props.getProperty("jdbc.driver.class"));



            connection = DriverManager.getConnection(url,user,password);

        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



    public static Connection getInstanceDB(){
        if(getConnection() == null){
            new Singleton();
        }
        return getConnection();
    }

    public static void closeConnection(){
        try{
            getConnection().close();
            System.out.println("Connexion à la base de données fermée !");
        }catch(SQLException sqle){
            throw new RuntimeException(sqle);
        }
    }

    private static Connection getConnection() {
        return connection;
    }


   // public static void main(String[] args) {
    //    Singleton.getInstanceDB();
  //  }
}
