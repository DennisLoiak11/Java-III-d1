import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.crypto.Data;

public class Main {
	static Connection conn = null;

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/Java-III-d1?useSSL=false";
		String username = "postgres";
		String password = "Trivianum_11";
		
		try {
			System.out.println("Connessione in corso...");

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected ✔");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		insertCliente(9, "Andrea", "Pirlo", 1976, "Lombardia");
		

	}

    // ** 1 **
    String sqlSelect = "SELECT * FROM Clienti";
    {
    try {
        Statement stmt = conn.createStatement();
        ResultSet Clienti = stmt.executeQuery(sqlSelect);
        while (Clienti.next()) {
            System.out.println("Nome: " + Clienti.getString("Nome") + " Cognome: " + Clienti.getString("Cognome"));
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    
    }


	
	public static void insertCliente(int NumCliente, String nome, String cognome, int data_nascita, String RegioneResidenza) {
		String sqlInsert = "INSERT INTO Clienti (NumCliente, nome, cognome, data_nascita, RegioneResidenza) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setInt(1, NumCliente);
			stmt.setString(2, nome);
			stmt.setString(3, cognome);
			stmt.setInt(4, data_nascita);
			stmt.setString(5, RegioneResidenza);
			stmt.execute();
			System.out.println("Cliente Registrato!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
