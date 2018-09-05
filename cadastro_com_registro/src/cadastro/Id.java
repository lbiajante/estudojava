package cadastro;

import java.util.ArrayList;

public final class Id {	
	private final String id;
	Util util = new Util();
	
	public Id(int id) {	
		this.id = String.format("%06d", id).trim();			
	}		
	
	public String getId() {
		return id;
	}		
	
}