import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String nombre = " ";
		String apellidos = " ";
		String dni = " ";
		String fechaNacimiento = " ";
		char sexo = ' ';
		String provinciaResidencia = " ";
		String telefono = " ";
		Scanner teclado = new Scanner(System.in);
		boolean registrado = false; //Booleano para comprobar si el usuario esta registrado en la base de datos del censo
		boolean claveValida = false; //Booleano para comprobar si la clave que introduce el usuario y la que se le ha enviado son iguales
		String clave = " "; //Clave que se genera para el usuario y se le manda via SMS
		String clave1 = " "; //Clave que introduce el usuario
		int claveErronea = 0; //Contador que se usa cuando se introduce una clave erronea
		int partido = 0; //Int para seleccionar el partido pol�tico al que se desea votar
		char confirmacion = ' '; //char para confirmar el voto
		
		System.out.println("Buenos d�as. Bienvenido al programa electoral del Gobierno de Espa�a.");
		
		System.out.println("Primero, introduzca sus datos, tal y como el programa lo indique: ");
		
		System.out.println("Introduzca su nombre: ");
		
		nombre = teclado.nextLine();
		
		System.out.println("Introduzca sus dos apellidos, separados por espacios: ");
		
		apellidos = teclado.nextLine();
		
		System.out.println("Introduzca su Documento Nacional de Identidad, recuerde que la letra ha de estar escrita en may�scula:");
		
		dni = teclado.nextLine();
		
		System.out.println("Introduzca su fecha de nacimiento, empezando por el a�o, despu�s el mes, y por �ltimo el d�a, separado por guiones. Ejemplo: 1945-06-23: ");
		
		fechaNacimiento = teclado.nextLine();
		
		System.out.println("Introduzca su provincia de residencia, recuerde que ha de empezar por una letra may�scula: ");
		
		provinciaResidencia = teclado.nextLine();
		
		System.out.println("Por �ltimo, introduzca su tel�fono m�vil, incluyendo el prefijo, este ser� el mismo al cual se env�e la clave para acceder al programa, as� que asegurese de que est� a su alcance y disponible.");
		
		telefono = teclado.nextLine();
		
		registrado = Registro.verificarInformacion(nombre, apellidos, dni, fechaNacimiento, sexo, provinciaResidencia, telefono);
		
		if(registrado) { //Se comprueba si el usuario existe en el censo
			
			clave = Registro.generarClave();
			
			Registro.enviarClave(clave, telefono);
			
			while(claveValida = false && claveErronea < 3) {
				System.out.println("A continuaci�n, introduzca la clave que ha recibido en su tel�fono m�vil: ");
				clave1 = teclado.nextLine();
			
				claveValida = Registro.comprobarClave(clave, clave1);
			
			if(claveValida) { //Se comprueba que la clave que se ha introducido es la misma que se le ha enviado por SMS
			
			do { //Bucle en caso de que el usuario se arrepienta del partido que ha escogido
				
				System.out.println("A continuaci�n se le mostrar� una lista con los partidos pol�ticos disponibles: ");
				
				System.out.println("1. Esquerra Republicana de Catalunya (ERC)");
				
				System.out.println("2. Partido Popular (PP)");
				
				System.out.println("3. Partido Socialista Obrero Espa�ol (PSOE)");
				
				System.out.println("4. Vox.");
				
				System.out.println("5. Unidas Podemos");
				
				System.out.println("6. Ciudadanos (C's)");
				
				do { //Bucle para asegurar que el usuario escoge un partido que est� en la lista
				
				System.out.println("Seleccione un partido de la lista: ");
				
				partido = teclado.nextInt();
				
				if(partido < 1 || partido > 6) {
					
					System.out.println("El partido que has introducido no se encuentra en la lista.");
					
				}
				
				}while(partido < 1 || partido > 6);
				
				do { //Bycle para asegurar que el usuario escribe S o N
				
				System.out.println("�Desea confirmar su voto? Esta ser� la �ltima vez que podr� retractarse. S/N: ");
				
				confirmacion = teclado.next().charAt(0);
				
				confirmacion = Character.toUpperCase(confirmacion);
				
				if(confirmacion != 'S' || confirmacion != 'N') {
					
					System.out.println("No es una entrada v�lida. Ha de ser S/N.");
					
				}
				}while(confirmacion != 'S' || confirmacion != 'N');
				
			}while(confirmacion == 'N');
				
			
			
			switch(partido) { //Switch para generar el objeto voto en funcion al partido que hayan esocogido previamente
			
			case 1:
				
				Voto voto1 = new Voto("ERC", fechaNacimiento, sexo, provinciaResidencia);
				
				voto1.a�adirVoto();
				
				break;
				
			case 2: 
				
				Voto voto2 = new Voto("PP", fechaNacimiento, sexo, provinciaResidencia);
				
				voto2.a�adirVoto();
				
				break;
				
			case 3:
			
				Voto voto3 = new Voto("PP", fechaNacimiento, sexo, provinciaResidencia);
				
				voto3.a�adirVoto();
				
				break;
				
			case 4:
				
				Voto voto4 = new Voto("Vox", fechaNacimiento, sexo, provinciaResidencia);
				
				voto4.a�adirVoto();
				
				break;
				
			case 5: 
				
				Voto voto5 = new Voto("Unidas Podemos", fechaNacimiento, sexo, provinciaResidencia);
				
				voto5.a�adirVoto();
				
				break;
				
			case 6: 
				
				Voto voto6 = new Voto("Ciudadanos", fechaNacimiento, sexo, provinciaResidencia);
				
				voto6.a�adirVoto();
				
				break;
				
			}
				
			}else {
				
				System.out.println("La clave que has introducido no es v�lida.");
				
				claveErronea++;
				
			}
			} //Bucle while
			
			if(claveErronea == 3) {
				
				System.out.println("Has introducido la clave err�neamente demasiadas veces.");
				
			}
		}else { //En caso de que el usuario no se encuentre en el censo
			
			System.out.println("Lo sentimos, su informaci�n no se encuentra almacenada en la base de datos del Censo Electoral, llame al tel�fono del Censo Electoral pertinente para m�s informaci�n.");
			
			
			
		}
		
		
	}

}
