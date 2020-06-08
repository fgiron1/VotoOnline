import java.sql.*;

public class Registro {


	public static boolean verificarInformacion(String nombre, String apellidos, String dni, String fechaNacimiento, char sexo, String provinciaResidencia, String telefono) {
	
		boolean registrado = false;
		String sexo0 = String.valueOf(sexo);
		String nombre1 = " ";
		String apellidos1 = " ";
		String dni1 = " ";
		String fechaNacimiento1 = " ";
		String sexo1 = " ";
		String provinciaResidencia1 = " ";
		String telefono1 = " ";
	
		
		try {
			
			PreparedStatement pst;
			
			String sourceURL = "jdbc:sqlserver://localhost:1433;database=VotoOnline";
			String usuario = "admin";
			String password = "EleccionesGobierno75";
			
			
			
			
			Connection conexion = DriverManager.getConnection(sourceURL, usuario, password);
			
			pst = conexion.prepareStatement("SELECT nombre, apellidos, dni, fechaNacimiento, sexo, provinciaResidencia, telefono FROM Votante WHERE dni = ?");
			pst.setString(1, dni);
			
			ResultSet votantes = pst.executeQuery();
			
			if(votantes.next()) {
			
			nombre1 = votantes.getString("nombre");
			apellidos1 = votantes.getString("apellidos");
			fechaNacimiento1 = votantes.getString("fechaNacimiento");
			sexo1 = votantes.getString("sexo");
			provinciaResidencia1 = votantes.getString("provinciaResidencia");
			telefono1 = votantes.getString("telefono");
	
			
			
		/*	
			System.out.println(votantes.getString("nombre"));
			System.out.println(nombre1);
			System.out.println(votantes.getString("apellidos"));
			System.out.println(apellidos1);
			System.out.println(votantes.getString("fechaNacimiento"));
			System.out.println(fechaNacimiento1);
			System.out.println(votantes.getString("sexo"));
			System.out.println(sexo);
			System.out.println(votantes.getString("provinciaResidencia"));
			System.out.println(provinciaResidencia1);
			System.out.println(votantes.getString("telefono"));
			System.out.println(telefono);
		*/	
			}
	
			
			if(nombre1.equals(nombre) && apellidos1.equals(apellidos) && fechaNacimiento1.equals(fechaNacimiento) && sexo1.equals(sexo0) && provinciaResidencia1.equals(provinciaResidencia) && telefono1.equals(telefono)) {
				
				registrado = true;
				
			}
			
			votantes.close();
			pst.close();
			conexion.close();
			
			
		}catch (SQLException sqle) {
			
			System.err.println(sqle);
		}
		
		
		
	
		return registrado;
	}
	
	public static void main (String[]args) {
		
		
		boolean prueba = false;
		
		prueba = verificarInformacion("Juan Manuel", "Gibert Vicente", "49406836F", "1981-05-20", 'H', "Barcelona", "724166396");
		
		System.out.println(prueba);
		
	}
	
	
	
}
