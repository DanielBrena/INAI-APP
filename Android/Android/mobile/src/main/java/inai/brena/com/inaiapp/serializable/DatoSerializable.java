package inai.brena.com.inaiapp.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.dato.Dato;

/**
 * Created by DanielBrena on 29/10/15.
 */
public class DatoSerializable implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Dato> datoList;

    public DatoSerializable() {
        this.datoList = new ArrayList<>();
    }


    public DatoSerializable(List<Dato> datoList) {
        this.datoList = datoList;

    }

    public List<Dato> getDatoList() {
        return datoList;
    }

    public void setDatoList(List<Dato> datoList) {
        this.datoList = datoList;
    }
}
