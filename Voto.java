/*
 * Clase Voto.
 * 
 * Propiedades basicas:
 * 
 * Partido: String, consultable. 
 * Fecha: String, consultable. Representa la fecha de nacimiento de la persona que vota, no el dia en el que se emite el voto.
 * Sexo: char, consultable. 
 * Provincia: String consultable
 * 
 * Propiedades derivadas:
 * 
 * No hay
 * 
 * Propiedades compartidas: 
 * 
 * No hay
 * 
 * M�todos: 
 * 
 * a�adirVoto();
 * 
 */


import java.sql.*;
public class Voto {

	private String partido;
	private String fecha;
	private char sexo;
	private String provincia;
	
	
	public Voto(String partido, String fecha, char sexo, String provincia) {
		
		this.partido = partido;
		
		this.fecha = fecha;
	
		this.sexo = sexo; 
		
		this.provincia = provincia;
		
	}
	
	public String getPartido() {
		return partido;
	}
	
	public String getFecha() {
		return fecha;
	}


	public char getSexo() {
		return sexo;
	}

	public String getProvincia() {
		return provincia;
	}

	
	
/*
 * Signatura: public void a�adirVoto()
 * 
 * Funcionalidad: A�ade un voto al partido en la base de datos
 * 
 * Precondiciones: Ninguna
 * 
 * Postcondiciones: El partido elegido deber� tener un voto m�s en la base de datos.
 * 
 * Entrada: Ninguna
 * 
 * Salida: Ninguna
 * 
 * Entrada/Salida: Ninguna
 * 
 */
	public void a�adirVoto() {
		
		try {
		
		PreparedStatement pst;
		String sourceURL = "jdbc:sqlserver://localhost:1433;database=VotoOnline";
		String usuario = "admin";
		String password = "EleccionesGobierno75";		
		Connection conexion = DriverManager.getConnection(sourceURL, usuario, password);
		
		pst = conexion.prepareStatement("UPDATE partido SET votosTotales = votosTotales +1 WHERE nombre LIKE ?");
		pst.setString(1, this.partido);
		pst.executeQuery();
		
		}catch(SQLException sqle) {
			
			System.out.println(sqle);
			
		}
	
	}

}

