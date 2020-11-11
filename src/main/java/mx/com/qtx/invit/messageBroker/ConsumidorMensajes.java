package mx.com.qtx.invit.messageBroker;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeoutException;

import javax.json.Json;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public abstract class ConsumidorMensajes {
	private String nombreCola;
	private String hostRabbitMQ;
	private boolean colaAnonima;
	
	private String nombreExchange;
	
	private Connection conexion;
	private Channel canal;
	protected String idConsumidor;

	private static Logger bitacora = LoggerFactory.getLogger(ConsumidorMensajes.class); 

	public ConsumidorMensajes(String host, String nomCola, String nomExchange) {
		super();
		this.colaAnonima = false;
		this.nombreCola = nomCola;
		this.hostRabbitMQ = host;
		this.nombreExchange = nomExchange;
	}
	public String getHostRabbitMQ() {
		return hostRabbitMQ;
	}
	public void setHostRabbitMQ(String hostRabbitMQ) {
		this.hostRabbitMQ = hostRabbitMQ;
	}
	
	public void suscribirseAexchangeConfig() {
		this.suscribirseA(this.nombreExchange);
	}
	
	public void suscribirseA(String nomExchange) {
		this.nombreExchange = nomExchange;
		ConnectionFactory fabricaConexiones = new ConnectionFactory();
		fabricaConexiones.setHost(this.hostRabbitMQ);
		try {
			this.conexion = fabricaConexiones.newConnection();
			this.canal = conexion.createChannel();
			this.canal.exchangeDeclare(this.nombreExchange, "fanout");
			if(this.colaAnonima)
	            this.nombreCola = canal.queueDeclare() // Actively declare a server-named exclusive, autodelete, non-durable queue.
	                                   .getQueue(); //Cola an�nima
			else
				this.canal.queueDeclare(this.nombreCola, false, false, true, null);
//	        		      .queueDeclare(queue, durable, exclusive, autoDelete, arguments)		
			
	        canal.queueBind(this.nombreCola, this.nombreExchange, "");		
//                queueBind(String queue, String exchange, String routingKey) 
	    } 
		catch (IOException | TimeoutException e) {
			bitacora.error(e.getMessage() + " " + e.getClass().getName() );
			if(e.getCause() == null)
				return;
			bitacora.error("causada por " + e.getCause().getMessage());
		}		
	}
	public void consumirMensajes() {
		DeliverCallback procesadorMensajes = getProcesadorMensajes();
		CancelCallback procesadorCancelacion = getProcesadorCancelacion(this.canal);
		try {
			// basicConsume(String queue, boolean autoAck, Map<String,�Object> arguments, Consumer callback)
			this.idConsumidor = canal.basicConsume(this.nombreCola, true, procesadorMensajes, procesadorCancelacion );
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	private DeliverCallback getProcesadorMensajes() {
		DeliverCallback callback = (consumerTag, objMensaje) -> {
			String contenido = new String(objMensaje.getBody(),"UTF-8");
		    procesarMensajeJson(contenido);
		};
		return callback;
	}
	abstract public void procesarMensajeJson(String contenido);
//	{
//		
//		JsonReader readerJson = Json.createReader(new StringReader(contenido));
//		String nomCte = readerJson.readObject().getString("nombre");
//		
//		System.out.println("   Dando de alta en cr�dito a :[" + nomCte + "]");
//	}
	private CancelCallback getProcesadorCancelacion(Channel canal) {
		return consumerTag-> {
			System.out.println("el consumidor " + this.idConsumidor + " ha sido cancelado");
			try {
				if(canal.isOpen()) {
					Connection con = canal.getConnection();
					canal.close();
					if(con.isOpen())
						con.close();
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		};
	}
}
