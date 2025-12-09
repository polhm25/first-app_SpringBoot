package dam.saruman.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "enemigos")
public class Enemigo {
    @Id
    private String id;

    @Field
    private String nombre;

    @Field
    private String genero;

    @Field
    private String pais;

    @Field
    private String afiliacion;

    @Field
    private Boolean activo;

    public Enemigo() {
    }

    public Enemigo(String id, String nombre, String genero, String pais, String afiliacion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.pais = pais;
        this.afiliacion = afiliacion;
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
