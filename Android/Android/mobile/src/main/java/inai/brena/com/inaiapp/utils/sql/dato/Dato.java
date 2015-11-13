package inai.brena.com.inaiapp.utils.sql.dato;

import java.io.Serializable;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class Dato  {
    private String id;
    private String nombre;
    private String descripcion;
    private CategoriaDatos categoriaDatos;



    public static final String TABLA = "dato";
    public static final String ID = "dat_id";
    public static final String NOMBRE = "dat_nombre";
    public static final String DESCRIPCION = "dat_descripcion";

    public static final String CATEGORIA_DATOS = "categoria_datos_cd_id";

    public Dato() {
    }

    public Dato(String id, String nombre, String descripcion, CategoriaDatos categoriaDatos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaDatos = categoriaDatos;
    }

    public Dato(String nombre, String descripcion, CategoriaDatos categoriaDatos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaDatos = categoriaDatos;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public CategoriaDatos getCategoriaDatos() {
        return categoriaDatos;
    }

    public void setCategoriaDatos(CategoriaDatos categoriaDatos) {
        this.categoriaDatos = categoriaDatos;
    }
}
