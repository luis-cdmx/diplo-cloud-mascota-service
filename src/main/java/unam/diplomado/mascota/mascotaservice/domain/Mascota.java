package unam.diplomado.mascota.mascotaservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Document(collection="mascotas")
public class Mascota   {

  @Id
  private String id;
  private String nombre;

	public Mascota(String nombre) {
		super();
		this.nombre = nombre;
	}
  
}

