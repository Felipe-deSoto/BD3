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
			con.createStatement().executeQuery("CREATE DATABASE Bedelia;"
					+ "USE Bedelia;");
			
			con.createStatement().executeQuery("CREATE TABLE Examenes("
					+ "codigo VARCHAR(45),"
					+ "materia VARCHAR(45),"
					+ "periodo VARCHAR(45)"
					+ ");");
			
			con.createStatement().executeQuery("CREATE TABLE Resultados("
					+ "codigo INT,"
					+ "codigo VARCHAR(45),"
					+ "calificacion INT,"
					+ ");");
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
		}
	
	}

}
