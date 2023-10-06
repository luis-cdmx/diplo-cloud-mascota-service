package unam.diplomado.mascota.mascotaservice.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import unam.diplomado.mascota.mascotaservice.domain.Mascota;
import unam.diplomado.mascota.mascotaservice.repository.MascotaRepository;
import unam.diplomado.mascota.mascotaservice.service.MascotaService;

@RestController
@RequestMapping(path="api/mascotas", produces="application/json")
@CrossOrigin(origins="*")
public class MascotaController {

	@Autowired
	private MascotaRepository mascotaRepository;
	
	@Autowired
	private MascotaService mascotaService;
	
	@GetMapping
	public List<Mascota> obtenerMascotas() {
		return mascotaRepository.findAll();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mascota crearMascota(@RequestBody Mascota mascota) {
		return mascotaRepository.save(mascota);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable("id") String id) {
		Optional<Mascota> mascota = mascotaRepository.findById(id);
		if (mascota.isPresent()) {
			return new ResponseEntity<>(mascota.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path="{id}", consumes="application/json")
	public ResponseEntity<Mascota> actualizarMascota(
			@PathVariable("id") String id, @RequestBody Mascota mascota) {
		Mascota mascotaActualizado = mascotaService.actualizarMascota(id, mascota);
		if (mascotaActualizado != null) {
			return new ResponseEntity<>(mascotaActualizado, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarMascota(@PathVariable("id") String id) {
		mascotaRepository.deleteById(id);
	}
}
