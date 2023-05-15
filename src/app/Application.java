package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
	static Connection conn = null;

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/d11?useSSL=false";
		String username = "postgres";
		String password = "1234";
		try {
			System.out.println("Connecting to PG...");

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected ✅");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// insertPizza("Margherita", 5.50);

		// ************************ UPDATE ALL ************************
		String sqlUpdate = "UPDATE pizze SET price=?";
		try {
			PreparedStatement stmtUpdateAll = conn.prepareStatement(sqlUpdate);
			stmtUpdateAll.setDouble(1, 7.00);
			stmtUpdateAll.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ************************ UPDATE ONE ************************
		String sqlUpdateOne = "UPDATE pizze SET price=? WHERE id = ?";
		try {
			PreparedStatement stmtUpdateOne = conn.prepareStatement(sqlUpdateOne);
			stmtUpdateOne.setDouble(1, 17.00);
			stmtUpdateOne.setInt(2, 5);
			stmtUpdateOne.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ************************ DELETE ONE ************************
		String sqlDeleteOne = "DELETE FROM pizze WHERE id = ?";
		try {
			PreparedStatement stmtDeleteOne = conn.prepareStatement(sqlDeleteOne);
			stmtDeleteOne.setInt(1, 5);
			stmtDeleteOne.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ************************ FIND **************************
		String sqlSelect = "SELECT * FROM pizze";
		try {
			Statement stmt = conn.createStatement();
			ResultSet pizze = stmt.executeQuery(sqlSelect);
			while (pizze.next()) {
				System.out.println("ID: " + pizze.getInt("id") + ", name:" + pizze.getString("name") + ", price: "
						+ pizze.getDouble("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// disconnection
//		if (conn != null) {
//			try {
//				conn.close();
//				System.out.println("Disconnection happened");
//			} catch (SQLException e) {
//				System.out.println(e.getMessage());
//			}
//
//		}

	}

	public static void insertPizza(String nomePizza, double prezzo) {
		String sqlInsert = "INSERT INTO pizze (name,price) VALUES(?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1, nomePizza);
			stmt.setDouble(2, prezzo);
			stmt.execute();
			System.out.println("Pizza inserita!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
