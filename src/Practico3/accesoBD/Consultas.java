package Practico3.accesoBD;

public class Consultas {
	public static String listarExamenes () {
		return "SELECT * from examenes;";
	}
	
	public static String insertarResultado () {
		return "INSERT INTO resultados VALUES (?, ?, ?)";
	}

	public static String listarResultados() {
		return "SELECT r.codigo, r.calificacion, e.materia FROM bedelia.resultados r, bedelia.examenes e WHERE e.codigo = r.codigo AND r.cedula = ?;";
	}
}