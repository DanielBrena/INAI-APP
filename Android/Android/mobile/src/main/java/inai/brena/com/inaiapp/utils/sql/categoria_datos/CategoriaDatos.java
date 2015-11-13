package inai.brena.com.inaiapp.utils.sql.categoria_datos;

/**
 * Created by DanielBrena on 23/10/15.
 */
public class CategoriaDatos {
    private String id;
    private String nombre;
    private String descripcion;
    private String color;
    private String valor;

    public static final String TABLA  = "categoria_datos";
    public static final String ID  = "cd_id";
    public static final String NOMBRE  = "cd_nombre";
    public static final String DESCRIPCION  = "cd_descripcion";
    public static final String COLOR = "dat_color";
    public static final String VALOR  = "cd_valor";

    public CategoriaDatos(String id, String nombre, String descripcion, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public CategoriaDatos(String id, String nombre, String descripcion, String color, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = color;
        this.valor = valor;
    }

    public CategoriaDatos(String nombre, String descripcion, String valor) {
        this.descripcion = descripcion;
        this.valor = valor;
        this.nombre = nombre;
    }

    public CategoriaDatos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
