package Practico3.accesoBD;

public class Consultas {
	public String listarExamenes () {
		return "SELECT * from examenes;";
	}
	
	public String insertarResultado () {
		return "INSERT INTO examenes VALUES (?, ?, ?)";
	}
}