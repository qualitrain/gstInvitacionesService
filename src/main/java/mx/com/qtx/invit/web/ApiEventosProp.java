package mx.com.qtx.invit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.qtx.IGestorEventosProgramados;
import mx.com.qtx.invit.entidades.EventoPropuesto;

import java.util.List;

@RestController
public class ApiEventosProp {
	
	@Autowired
	private IGestorEventosProgramados gestorEventos;
	
	@GetMapping(path = "/eventos",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EventoPropuesto> getEventosPropuestos(){
		return gestorEventos.getEventos();		
	}

}
