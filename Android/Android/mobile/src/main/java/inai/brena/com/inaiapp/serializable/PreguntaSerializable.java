package inai.brena.com.inaiapp.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;

/**
 * Created by DanielBrena on 29/10/15.
 */
public class PreguntaSerializable implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ItemPreguntaPreguntas> itemPreguntaPreguntasList;

    public PreguntaSerializable() {
        this.itemPreguntaPreguntasList = new ArrayList<>();
    }


    public PreguntaSerializable(List<ItemPreguntaPreguntas> itemPreguntaPreguntasList) {
        this.itemPreguntaPreguntasList = itemPreguntaPreguntasList;

    }

    public List<ItemPreguntaPreguntas> getItemPreguntaPreguntasList() {
        return itemPreguntaPreguntasList;
    }

    public void setItemPreguntaPreguntasList(List<ItemPreguntaPreguntas> itemPreguntaPreguntasList) {
        this.itemPreguntaPreguntasList = itemPreguntaPreguntasList;
    }
}
