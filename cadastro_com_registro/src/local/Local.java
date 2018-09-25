package local;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Local implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Local: " + lugar;
	}

	private String lugar;

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
