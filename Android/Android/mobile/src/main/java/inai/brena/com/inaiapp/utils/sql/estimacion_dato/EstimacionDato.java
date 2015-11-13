package inai.brena.com.inaiapp.utils.sql.estimacion_dato;

import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class EstimacionDato {
    private String id;
    private Estimacion estimacion;
    private Dato dato;

    public static final String TABLA = "estimacion_has_dato";
    public static final String ID = "ed_id";
    public static final String ESTIMACION = "estimacion_est_id";
    public static final String DATO = "dato_dat_id";

    public EstimacionDato() {
    }

    public EstimacionDato(String id, Estimacion estimacion, Dato dato) {
        this.id = id;
        this.estimacion = estimacion;
        this.dato = dato;
    }

    public EstimacionDato(Estimacion estimacion, Dato dato) {
        this.estimacion = estimacion;
        this.dato = dato;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estimacion getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(Estimacion estimacion) {
        this.estimacion = estimacion;
    }

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }
}
