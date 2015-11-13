package inai.brena.com.inaiapp.utils.sql.estimacion;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class Estimacion {
    private String id;
    private String nombre;
    private String fecha;
    private String plantilla;


    public static final  String TABLA = "estimacion";
    public static final  String ID = "est_id";
    public static final  String NOMBRE = "est_nombre";
    public static final  String FECHA = "est_fecha";
    public static final  String PLANTILLA = "est_plantilla";


    public Estimacion(String id, String nombre, String fecha, String plantilla) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.plantilla = plantilla;
    }

    public Estimacion( String nombre, String fecha, String plantilla) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.plantilla = plantilla;
    }


    public Estimacion() {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }


}
