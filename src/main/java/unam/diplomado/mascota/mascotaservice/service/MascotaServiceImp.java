package unam.diplomado.mascota.mascotaservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unam.diplomado.mascota.mascotaservice.domain.Mascota;
import unam.diplomado.mascota.mascotaservice.repository.MascotaRepository;

@Service
public class MascotaServiceImp implements MascotaService{

	@Autowired
	private MascotaRepository mascotaRepository;
	
	@Override
	public Mascota actualizarMascota(String id, Mascota mascota) {
		Optional<Mascota> mascotaExistente = mascotaRepository.findById(id);
		if (mascotaExistente.isPresent()) {
			Mascota mascotaActualizar = mascotaExistente.get();
			mascotaActualizar.setNombre(mascota.getNombre());
			mascotaRepository.save(mascotaActualizar);
			return mascotaActualizar;
		}
		return null;
	}
}
