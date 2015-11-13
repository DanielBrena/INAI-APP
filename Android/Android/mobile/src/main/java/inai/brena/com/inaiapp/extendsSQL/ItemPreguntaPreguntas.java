package inai.brena.com.inaiapp.extendsSQL;

import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipios;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;

/**
 * Created by DanielBrena on 26/10/15.
 */
public class ItemPreguntaPreguntas extends Pregunta {
    private boolean isChecked;

    public ItemPreguntaPreguntas(String id, String pregunta, CategoriaPrincipios categoriaPrincipios, boolean isChecked) {
        super(id, pregunta, categoriaPrincipios);
        this.isChecked = isChecked;
    }
    public ItemPreguntaPreguntas(String id, String pregunta, CategoriaPrincipios categoriaPrincipios) {
        super(id, pregunta, categoriaPrincipios);
        this.isChecked = isChecked;
    }

    public ItemPreguntaPreguntas(String pregunta, CategoriaPrincipios categoriaPrincipios, boolean isChecked) {
        super(pregunta, categoriaPrincipios);
        this.isChecked = isChecked;
    }

    public ItemPreguntaPreguntas(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
