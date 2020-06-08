
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
	
}
