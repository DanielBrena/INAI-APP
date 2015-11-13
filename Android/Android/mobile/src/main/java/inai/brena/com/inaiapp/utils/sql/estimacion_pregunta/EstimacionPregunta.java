package inai.brena.com.inaiapp.utils.sql.estimacion_pregunta;

import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class EstimacionPregunta {
    private String id;
    private Estimacion estimacion;
    private Pregunta pregunta;
    private String respuesta;

    public static final String TABLA = "estimacion_has_pregunta";
    public static final String ID = "ep_id";
    public static final String ESTIMACION = "estimacion_est_id";
    public static final String PREGUNTA = "pregunta_pre_id";
    public static final String RESPUESTA = "ep_respuesta";

    public EstimacionPregunta(String id, Estimacion estimacion, Pregunta pregunta, String respuesta) {
        this.id = id;
        this.estimacion = estimacion;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    public EstimacionPregunta(Estimacion estimacion, Pregunta pregunta, String respuesta) {
        this.estimacion = estimacion;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public EstimacionPregunta() {
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

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
