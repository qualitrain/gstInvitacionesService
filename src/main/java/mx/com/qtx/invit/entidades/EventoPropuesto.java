package mx.com.qtx.invit.entidades;

import java.util.Date;
import java.util.List;

import javax.json.bind.annotation.JsonbDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoPropuesto {
	private int numEvento;
	private int numPersonaPropietario;
	private String nombre;
	private String objetivo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm z" )
	@JsonbDateFormat("yyyy-MM-dd HH:mm z")
	private Date fechaProg;
	
	private int duracionProgMin;
	private int estado;

	private List<ParticipanteEvento> participantes;

	public EventoPropuesto() {
		super();
	}

	public int getNumEvento() {
		return numEvento;
	}

	public void setNumEvento(int numEvento) {
		this.numEvento = numEvento;
	}

	public int getNumPersonaPropietario() {
		return numPersonaPropietario;
	}

	public void setNumPersonaPropietario(int numPersonaPropietario) {
		this.numPersonaPropietario = numPersonaPropietario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Date getFechaProg() {
		return fechaProg;
	}

	public void setFechaProg(Date fechaProg) {
		this.fechaProg = fechaProg;
	}

	public int getDuracionProgMin() {
		return duracionProgMin;
	}

	public void setDuracionProgMin(int duracionProgMin) {
		this.duracionProgMin = duracionProgMin;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<ParticipanteEvento> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<ParticipanteEvento> participantes) {
		this.participantes = participantes;
	}

	@Override
	public String toString() {
		return "EventoPropuesto [numEvento=" + numEvento + ", numPersonaPropietario=" + numPersonaPropietario
				+ ", nombre=" + nombre + ", objetivo=" + objetivo + ", fechaProg=" + fechaProg + ", duracionProgMin="
				+ duracionProgMin + ", estado=" + estado + ", participantes=" + participantes + "]";
	}
}
