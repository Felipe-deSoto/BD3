package Practico3.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Practico3.DatosTabla;
import Practico3.Examen;
import Practico3.Resultado;

public class AccesoBD {
	public static List<Examen> listarExamenes (Connection con) throws SQLException {
		Statement 		statement 	= con.createStatement();
		ResultSet 		response 	= statement.executeQuery(Consultas.listarExamenes());
		List<Examen> 	lista 		= new ArrayList<Examen>();
		while(response.next()) {
            String codigo 		= response.getString("codigo");
            String materia 		= response.getString("materia");
            String periodo 		= response.getString("periodo");
            Examen nuevoExamen 	= new Examen(codigo, materia, periodo);
            lista.add(nuevoExamen);
		}
		response.close();
		statement.close();
		return lista;
	}
	
	public static void ingresarResultado (Connection con, Resultado resu) throws SQLException {
		PreparedStatement statement = con.prepareStatement(Consultas.insertarResultado());
		statement.setInt(1, resu.cedula);
		statement.setString(2, resu.codigo);
		statement.setInt(3, resu.calificacion);
		statement.executeUpdate();
		statement.close();
	}
	
	public static List<DatosTabla> listarResultados (Connection con, int cedula) throws SQLException {
		List<DatosTabla> 	lista 		= new ArrayList<DatosTabla>();
		PreparedStatement 	statement	= con.prepareStatement(Consultas.listarResultados());
		statement.setInt(1, cedula);
		ResultSet 			response 	= statement.executeQuery();
		while(response.next()) {
			String 		responseCodigo 			= response.getString("codigo");
			int 		responseCalificacion	= response.getInt("calificacion");
			String 		responseMateria 			= response.getString("materia");
			DatosTabla 	resultado 				= new DatosTabla(responseCodigo, responseCalificacion, responseMateria);
            lista.add(resultado);
		}
		response.close();
		statement.close();
		return lista;
	}
}
