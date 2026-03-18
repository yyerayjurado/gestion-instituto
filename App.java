import java.sql.Connection;
import java.sql.Statement;
import model.repository.*;

public class App {

    public static void main(String[] args) {

        try {
            Connection con = dbConexion.conectar();

            System.out.println("Conexion establecida con exito");

            Statement st = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}