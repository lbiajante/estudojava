package local;

import java.io.Serializable;

//@Entity
public class Local implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "\n$ Local: #" + lugar;
	}

	private String lugar;

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
