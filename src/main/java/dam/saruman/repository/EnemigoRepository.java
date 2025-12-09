package dam.saruman.repository;

/*
Esto va a ser para operaciones CRUD
*/

import dam.saruman.entity.Enemigo;
import dam.saruman.entity.Enemigo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EnemigoRepository extends MongoRepository<Enemigo, String> {
    List<Enemigo> findByNombre(String nombre);
}
