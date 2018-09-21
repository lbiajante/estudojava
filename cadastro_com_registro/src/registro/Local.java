package registro;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Local {
	
	@Id
	private String id;	
	private String lugar;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	

}
