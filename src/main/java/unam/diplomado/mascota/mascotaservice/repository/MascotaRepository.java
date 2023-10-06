package unam.diplomado.mascota.mascotaservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import unam.diplomado.mascota.mascotaservice.domain.Mascota;

public interface MascotaRepository 
	extends MongoRepository<Mascota, String>{

}
