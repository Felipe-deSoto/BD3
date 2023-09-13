package Practico3.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Practico3.Examen;
import Practico3.Resultado;

public class AccesoBD {
	public static List<Examen> listarExamenes (Connection con) throws SQLException {
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(Consultas.listarExamenes());
			List<Examen> lista = new ArrayList<Examen>();
			while(rs.next()) {
	            String codigo = rs.getString("codigo");
	            String materia = rs.getString("materia");
	            String periodo = rs.getString("periodo");
	            
	            Examen nuevoExamen = new Examen(codigo, materia, periodo);
	            lista.add(nuevoExamen);
			}
			rs.close();
			s.close();
			return lista;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void ingresarResultado (Connection con, Resultado resu)  throws SQLException {
		try {
			PreparedStatement pt = con.prepareStatement(Consultas.insertarResultado());
			pt.setInt(1, resu.cedula);
			pt.setString(2, resu.codigo);
			pt.setInt(3, resu.calificacion);
			pt.executeUpdate();
			pt.close();
		} catch (SQLException e) {
			throw e;
		}
	}
}
