package inai.brena.com.inaiapp.utils.sql.configuracion;

/**
 * Created by DanielBrena on 29/10/15.
 */
public class Configuracion {
    private String id;
    private String nombre;
    private String valor;

    public static final String TABLA = "configuracion";
    public static final String ID = "con_id";
    public static final String NOMBRE = "con_nombre";
    public static final String VALOR = "con_valor";

    public Configuracion(String id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }


    public Configuracion( String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public Configuracion() {
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
