package dam.saruman.controller;

import dam.saruman.entity.Enemigo;
import dam.saruman.service.EnemigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EnemigoController {
    @Autowired
    private EnemigoService enemigoService;

    @GetMapping("/enemigo")
    public List<Enemigo> getEnemigos() {
        return enemigoService.obtenerEnemigos();
    }

    @GetMapping("/enemigo/{id}")
    public ResponseEntity<Enemigo> getEnemigoById(@PathVariable Long id) {
        Optional<Enemigo> enemigo = enemigoService.obtenerEnemigoById(id);
        return enemigo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/enemigo")
    public Enemigo addEnemigo(@RequestBody Enemigo enemigo) {
        return enemigoService.guardarEnemigo(enemigo);
    }

    @PutMapping("/enemigo/{id}")
    public ResponseEntity<Enemigo> updateEnemigo(@PathVariable Long id, @RequestBody Enemigo enemigoDetalles) {
        Enemigo enemigoActualizado = enemigoService.actualizarEnemigo(id, enemigoDetalles);
        if (enemigoActualizado != null) {
            return ResponseEntity.ok(enemigoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/enemigo/{id}")
    public ResponseEntity<Void> deleteEnemigo(@PathVariable Long id) {
        if (enemigoService.eliminarEnemigo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
