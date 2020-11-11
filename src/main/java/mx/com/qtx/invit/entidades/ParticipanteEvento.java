package mx.com.qtx.invit.entidades;

public class ParticipanteEvento {
	private int numParticipante;
	private int numEmpleado;
	private String nombre;
	private String correoElectronico;
	private String telefono;

	public ParticipanteEvento() {
		super();
	}

	public int getNumParticipante() {
		return numParticipante;
	}

	public void setNumParticipante(int numParticipante) {
		this.numParticipante = numParticipante;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "ParticipanteEvento [numParticipante=" + numParticipante + ", numEmpleado=" + numEmpleado + ", nombre="
				+ nombre + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + "]";
	}

}
