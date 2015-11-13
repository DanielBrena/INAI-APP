package inai.brena.com.inaiapp.utils.sql.pregunta;

import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipios;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class Pregunta {
    private String id;
    private String pregunta;
    private CategoriaPrincipios categoriaPrincipios;

    public static final String TABLA = "pregunta";
    public static final String ID = "pre_id";
    public static final String PREGUNTA = "pre_pregunta";
    public static final String CATEGORIA_PRINCIPIOS = "categoria_principios_cp_id";

    public Pregunta(String id, String pregunta,CategoriaPrincipios categoriaPrincipios) {
        this.id = id;
        this.categoriaPrincipios = categoriaPrincipios;
        this.pregunta = pregunta;
    }
    public Pregunta(String pregunta,CategoriaPrincipios categoriaPrincipios) {

        this.categoriaPrincipios = categoriaPrincipios;
        this.pregunta = pregunta;
    }

    public Pregunta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public CategoriaPrincipios getCategoriaPrincipios() {
        return categoriaPrincipios;
    }

    public void setCategoriaPrincipios(CategoriaPrincipios categoriaPrincipios) {
        this.categoriaPrincipios = categoriaPrincipios;
    }
}
