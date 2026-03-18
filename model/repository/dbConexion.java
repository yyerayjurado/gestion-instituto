package model.repository;

public class dbConexion {

    public static java.sql.Connection conectar() {
        java.sql.Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioninstituto", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();

        }  
        return con;
    }
}