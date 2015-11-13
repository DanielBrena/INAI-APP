package inai.brena.com.inaiapp.utils.sql.categoria_principios;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class CategoriaPrincipios {
    private String id;
    private String nombre;
    private String descripcion;

    public static final String TABLA = "categoria_principios";
    public static final String ID = "cp_id";
    public static final String NOMBRE = "cp_nombre";
    public static final String DESCRIPCION = "cp_descripcion";

    public CategoriaPrincipios(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public CategoriaPrincipios() {
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
}
