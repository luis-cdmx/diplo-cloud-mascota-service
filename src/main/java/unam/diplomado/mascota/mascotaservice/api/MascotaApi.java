package unam.diplomado.mascota.mascotaservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import unam.diplomado.mascota.mascotaservice.domain.Mascota;

@RequestMapping(path="mascotas", produces="application/json")
@Tag(name="mascotata", description="API del Recurso Mascotata")
@CrossOrigin(origins="*")
public interface MascotaApi {

	@Operation(summary = "Obtiene Todos los Mascotas")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", 
		description = "Mascotas Encontrados", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=Mascota.class)) })
	  })
	@GetMapping
	Iterable<Mascota> obtenerMascotas();
	
	@Operation(summary = "Crear Mascota")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", 
		description = "Mascota Creado Exitosamente", 
	    content = { 
	    	@Content(
	    		mediaType="application/json", 
	    		array=@ArraySchema(schema = @Schema(implementation=Mascota.class)
	    		)) 
	    	})
	  })
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	Mascota crearMascota(@RequestBody Mascota mascota);
	
	@Operation(summary = "Busca Mascota por Id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", 
		description = "Mascota Encontrado", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=Mascota.class)) }),
	  @ApiResponse(responseCode = "404",
		description = "Mascota No Encontrado")
	  })
	@GetMapping("{id}")
	ResponseEntity<Mascota> mascotaPorId(@PathVariable("id") String id);
	
	@Operation(summary = "Actualiza Mascota por Id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", 
		description = "Mascota Actualizado", 
	    content = { 
	    	@Content(mediaType="application/json", 
	    	schema = @Schema(implementation=Mascota.class)) }),
	  @ApiResponse(responseCode = "404",
		description = "Mascota No Encontrado y No Actualizado")
	  })
	@PutMapping(path="{id}", consumes="application/json")
	ResponseEntity<Mascota> actualizarMascota(
			@PathVariable("id") String id, @RequestBody Mascota mascota);

	@Operation(summary = "Elimina Mascota por Id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "204")
	  })
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void eliminarMascota(@PathVariable("id") String id);
	
}
