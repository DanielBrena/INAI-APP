package inai.brena.com.inaiapp.utils.sql.tip;

/**
 * Created by DanielBrena on 16/11/15.
 */
public class Tip {
    private String id;
    private String titulo;
    private String mensaje;

    public static final String TABLA = "tip";
    public static final String ID = "tip_id";
    public static final String TITULO = "tip_titulo";
    public static final String MENSAJE = "tip_mensaje";

    public Tip() {
    }

    public Tip(String id, String titulo, String mensaje) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
