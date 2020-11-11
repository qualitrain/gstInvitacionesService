package mx.com.qtx;

import java.util.List;

import mx.com.qtx.invit.entidades.EventoPropuesto;

public interface IGestorEventosProgramados {
	List<EventoPropuesto> getEventos();
	void agregarEvento(EventoPropuesto evt);
}
