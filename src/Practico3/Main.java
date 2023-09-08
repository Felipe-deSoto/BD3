package Practico3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
	
	public static Connection CrearConexion() throws ClassNotFoundException, SQLException {
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			String url = "jdbc:mysql://localhost:3306/";
			return DriverManager.getConnection(url, "root", "");
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//Prueba
		Connection con = CrearConexion();
		try {
			con.createStatement().executeUpdate("CREATE DATABASE Bedelia;");
			con.createStatement().executeUpdate("USE Bedelia;");
			
				con.createStatement().executeUpdate("CREATE TABLE Examenes("
						+ "codigo VARCHAR(45),"
						+ "materia VARCHAR(45),"
						+ "periodo VARCHAR(45)"
						+ ");");
			
			con.createStatement().executeUpdate("CREATE TABLE Resultados("
					+ "cedula INT,"
					+ "codigo VARCHAR(45),"
					+ "calificacion INT"
					+ ");");
		
			
			// MAR DIS 1
			con.createStatement().executeUpdate("INSERT INTO Examenes "
					+ "VALUES ('MD2020Dic', "
					+ "'Matemática discreta', '"
					+ "Diciembre 2020'"
					+ ");");


			// PROG 1
			con.createStatement().executeUpdate("INSERT INTO Examenes "
					+ "VALUES ("
					+ "'P12020Dic',"
					+ "'Programación 1',"
					+ "'Diciembre 2020'"
					+ ");");

			// BD 1
			con.createStatement().executeUpdate("INSERT INTO Examenes "
					+ "VALUES ("
					+ "'BD2020Dic',"
					+ "'Bases de datos',"
					+ "'Diciembre 2020'"
					+ ");");

			// MAT DIS 2
			con.createStatement().executeUpdate("INSERT INTO Examenes "
					+ "VALUES ("
					+ "'MD2021Feb',"
					+ "'Matemática discreta II',"
					+ "'Febrero 2021'"
					+ ");");

			
			// SISO 
			con.createStatement().executeUpdate("INSERT INTO Examenes "
					+ "VALUES ("
					+ "'SO2021Feb',"
					+ "'Sistemas Operativos',"
					+ "'Febrero 2021'"
					+ ");");
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
		}
	
	}

}
