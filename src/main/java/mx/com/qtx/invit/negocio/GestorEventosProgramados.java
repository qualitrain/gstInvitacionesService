package mx.com.qtx.invit.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import mx.com.qtx.IGestorEventosProgramados;
import mx.com.qtx.invit.entidades.EventoPropuesto;
import mx.com.qtx.invit.entidades.ParticipanteEvento;

@Service
public class GestorEventosProgramados implements IGestorEventosProgramados{
	private Map<Integer,EventoPropuesto> eventosPropuestos;

	public GestorEventosProgramados() {
		this.eventosPropuestos = new HashMap<>();
		agregarEventosDummy();
		
		
	}

	private void agregarEventosDummy() {
		EventoPropuesto evento = new EventoPropuesto();
		evento.setNumEvento(1);
		evento.setNombre("Junta planeación");
		evento.setObjetivo("Planear avances del trimestre siguiente");
		evento.setFechaProg(new Date());
		evento.setEstado(0);
		evento.setNumPersonaPropietario(2100);
		
		this.eventosPropuestos.put(evento.getNumEvento(), evento);
		
		List<ParticipanteEvento> participantes = new ArrayList<>();
		evento.setParticipantes(participantes);
		
		ParticipanteEvento pe = new ParticipanteEvento();
		pe.setNumEmpleado(3001);
		pe.setNombre("Juan Rodríguez Vázquez");
		pe.setNumParticipante(1);
		pe.setCorreoElectronico("jrodriguez@laempresa.org.mx");
		pe.setTelefono("77-23-12-66-69");
		participantes.add(pe);
		
		pe = new ParticipanteEvento();
		pe.setNumEmpleado(3005);
		pe.setNombre("María Eugenia Juárez Villa");
		pe.setNumParticipante(2);
		pe.setCorreoElectronico("mjuarez@laempresa.org.mx");
		pe.setTelefono("77-11-34-71-61");
		participantes.add(pe);
		
		pe = new ParticipanteEvento();
		pe.setNumEmpleado(3035);
		pe.setNombre("Domingo Mora Lira");
		pe.setNumParticipante(3);
		pe.setCorreoElectronico("dmora@laempresa.org.mx");
		pe.setTelefono("43-87-22-13-12");
		participantes.add(pe);
	}

	public List<EventoPropuesto> getEventos(){
		 return this.eventosPropuestos
				    .values()
				    .stream()
				    .sorted( (o1,o2) ->  o1.getNumEvento() < o2.getNumEvento()  ? -1 : 1 )
				    .collect(Collectors.toList());
	}
	
	public void agregarEvento(EventoPropuesto evt) {
		this.eventosPropuestos.put(evt.getNumEvento(), evt);
	}
	
}
