/*
 * Clase Registro
 * 
 * Propiedades basicas: 
 * 
 * No hay
 * 
 * Propiedades derivadas: 
 * 
 * No hay
 * 
 * Propiedades compartidas: 
 * 
 * No hay
 * 
 * Métodos:
 * 
 * verificarInformacion(String nombre, String apellidos, String dni, String fechaNacimiento, char sexo, String provinciaResidencia, String telefono);
 * 
 * generarClave()
 * 
 * enviarClave(String clave, String telefono)
 * 
 * comprobarClave(clave1, clave2)
 * 
 */

import java.sql.*;
import java.util.Random;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Registro {

	public static final String ACCOUNT_SID = "AC0a2b66d64fa4886536418bcca57b9870";
	public static final String AUTH_TOKEN = "388aa57bbe43cc622ffcebb01bfa7d49";

	
/*
 * Signatura: public static boolean verificarInformacion(String nombre, String apellidos, String dni, String fechaNacimiento, char sexo, String provinciaResidencia, String telefono)
 * 
 * Funcionalidad: Comprueba que los datos introducidos por el usuario se encuentren en la base de datos del censo electoral y comprueba que sean correctos
 * 
 * Precondiciones: Los datos introducidos han de ser del mismo formato que en la base de datos.
 * 
 * Postcondicones: Ninguna
 * 
 * Entrada: Datos del usuario
 * 
 * Salida: Booleano para comprobar si esta en el censo o no
 * 
 * Entrada/Salida: Ninguno
 * 	
 */
	
	public static boolean verificarInformacion(String nombre, String apellidos, String dni, String fechaNacimiento, char sexo, String provinciaResidencia, String telefono) {
	
		boolean registrado = false;
		String sexo0 = String.valueOf(sexo);
		String nombre1 = " ";
		String apellidos1 = " ";
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
	
/*
 * Signatura: public static String generarClave()
 * 
 * Funcionalidad: Genera una clave de 4 dígitos alfanumérica
 * 
 * Precondiciones: Ninguna
 * 
 * Postcondiciones: Ninguna
 * 
 * Entrada: Ninguna
 * 
 * Salida: String con la clave
 * 
 * Entrada/Salida: Ninguna
 * 
 */
	
	public static String generarClave() {
		
		final String letras = "abcdefghijklmnopqrstuvwxyz";
		final char[] alfanumericos = (letras + letras.toUpperCase() + "0123456789").toCharArray();
		
		StringBuilder clave = new StringBuilder();
		
		for(int i = 0; i < 4; i++) {
			
			clave.append(alfanumericos[new Random().nextInt(alfanumericos.length)]);
			
		}

		return clave.toString();
	}
	
	
/*
 * Signatura: public static void enviarClave(String clave, String telefono)
 * 
 * Funcionalidad: Envia una clave a un telefono móvil via SMS
 * 
 * Precondiciones: El telefono tiene que contener el prefijo.
 * 
 * Postcondiciones: Ninguna
 * 
 * Entrada: clave a enviar, y telefono donde enviarlo
 * 
 * Salidas: Ninguna
 * 
 * Entrada/Salida: Ninguna
 *  
 */
	
	public static void enviarClave(String clave, String telefono) {
		
		Message message = Message.creator(new PhoneNumber(telefono),
				new PhoneNumber("+12513222860"),
				"Esta es tu clave para acceder al programa electoral: "+ clave).create();
		
		System.out.println(message.getSid());
		
	}
	
/*
 * Signatura: public static boolean comprobarClave(String clave1, String clave2)
 * 
 * Funcionalidad: Comprueba que la clave que se le mando al usuario y  la que el mismo introduce son iguales
 * 
 * Precondiciones: Ninguna
 * 
 * Postcondiciones: Ninguna
 * 
 * Entrada: Dos claves
 * 
 * Salida: Booleano para comprobar si son iguales o no
 * 
 * Entrada/Salida: Ninguno
 * 	
 */

	public static boolean comprobarClave(String clave1, String clave2) {
		
		boolean valida = false;
		
		if(clave1.equals(clave2)){
			
			valida = true;
			
		}
		
		return valida;
	}
	
}
