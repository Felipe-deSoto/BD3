package Practico3.accesoBD;

public class Consultas {
	public static String listarExamenes () {
		return "SELECT * from examenes;";
	}
	
	public static String insertarResultado () {
		return "INSERT INTO resultados VALUES (?, ?, ?)";
	}
}