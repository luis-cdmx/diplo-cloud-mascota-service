package unam.diplomado.mascota.mascotaservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
			version = "v1",
			title = "Mascota Microservice Endpoints", 
			description = "Definición de los Endpoints de "
					+ "Mascota Service para la app", 
			contact = @Contact(
				name = "UNAM", 
				url = "https://www.unam.mx/", 
			email = "elocasodelosidolos@gmail.com")))
public class OpenApiConfig {

}
