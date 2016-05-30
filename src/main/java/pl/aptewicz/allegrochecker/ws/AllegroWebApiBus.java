package pl.aptewicz.allegrochecker.ws;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.stereotype.Component;

import pl.allegro.webapi.ServicePort;

@Component
public class AllegroWebApiBus {

	private static final String ALLEGRO_WEB_API_NAMESPACE_URI = "https://webapi.allegro.pl/service.php";
	private static final String SERVICE_PORT_NAME = "servicePort";
	private static final String SERVICE_NAME = "serviceService";
	private static final String ALLEGRO_WEB_API_URL = "https://webapi.allegro.pl/service.php?wsdl";

	@Bean
	public JaxWsPortProxyFactoryBean pocService() throws MalformedURLException {
		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
		proxy.setWsdlDocumentUrl(new URL(ALLEGRO_WEB_API_URL));
		proxy.setServiceName(SERVICE_NAME);
		proxy.setPortName(SERVICE_PORT_NAME);
		proxy.setServiceInterface(ServicePort.class);
		proxy.setNamespaceUri(ALLEGRO_WEB_API_NAMESPACE_URI);
		return proxy;
	}
}
