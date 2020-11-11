package mx.com.qtx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import mx.com.qtx.invit.messageBroker.ConsumidorEventoNuevo;

@SpringBootApplication
public class GstInvitacionesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GstInvitacionesServiceApplication.class, args);
	}
	@Bean
	public IConsumidorMsgEvtosNuevos getConsumidorMensajes(Environment env, IGestorEventosProgramados gestorEventos) {
		IConsumidorMsgEvtosNuevos consumMessageBroker = new ConsumidorEventoNuevo(
				env.getProperty("qtx.gstInvitaciones.messageBroker.host", "localhost"),
				env.getProperty("qtx.gstInvitaciones.messageBroker.nomColaEvtos", "colaEventosDefault"),
				env.getProperty("qtx.gstInvitaciones.messageBroker.exchangeEvtos", "exchangeDefault"),
				gestorEventos
				);
		consumMessageBroker.suscribirseAexchangeConfig();
		consumMessageBroker.consumirMensajes();
		return consumMessageBroker;
		
	}

}
