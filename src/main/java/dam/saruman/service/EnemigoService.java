package dam.saruman.service;

import dam.saruman.entity.Enemigo;
import dam.saruman.repository.EnemigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnemigoService {
    @Autowired
    private EnemigoRepository enemigoRepository;

    public List<Enemigo> obtenerEnemigos() {
        List<Enemigo> enemigos = enemigoRepository.findAll();
        if (enemigos.isEmpty()) {
            System.out.println("No enemigos");
        } else {
            System.out.println("Sí hay enemigos");
            enemigos.forEach(enemigo -> System.out.println(enemigo.getNombre()));
        }
        return enemigos;
    }

    public Optional<Enemigo> obtenerEnemigoById(Long id) {
        return enemigoRepository.findById(id);
    }

    public Enemigo guardarEnemigo(Enemigo enemigo) {
        return enemigoRepository.save(enemigo);
    }

    public Enemigo actualizarEnemigo(Long id, Enemigo enemigoDetalles) {
        Optional<Enemigo> enemigo = enemigoRepository.findById(id);
        if (enemigo.isPresent()) {
            Enemigo enemigoExistente = enemigo.get();
            if (enemigoDetalles.getNombre() != null) {
                enemigoExistente.setNombre(enemigoDetalles.getNombre());
            }
            if (enemigoDetalles.getGenero() != null) {
                enemigoExistente.setGenero(enemigoDetalles.getGenero());
            }
            if (enemigoDetalles.getPais() != null) {
                enemigoExistente.setPais(enemigoDetalles.getPais());
            }
            if (enemigoDetalles.getAfiliacion() != null) {
                enemigoExistente.setAfiliacion(enemigoDetalles.getAfiliacion());
            }
            if (enemigoDetalles.isActivo() != null) {
                enemigoExistente.setActivo(enemigoDetalles.isActivo());
            }
            return enemigoRepository.save(enemigoExistente);
        }
        return null;
    }

    public boolean eliminarEnemigo(Long id) {
        if (enemigoRepository.existsById(id)) {
            enemigoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
