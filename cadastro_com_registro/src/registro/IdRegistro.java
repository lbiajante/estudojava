package registro;


public final class IdRegistro {	
	private final String id;
	UtilRegistro util = new UtilRegistro();
	
	public IdRegistro(int id) {	
		this.id = String.format("%06d", id).trim();			
	}		
	
	public String getId() {
		return id;
	}		
	
}