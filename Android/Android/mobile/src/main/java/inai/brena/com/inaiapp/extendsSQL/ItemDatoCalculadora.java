package inai.brena.com.inaiapp.extendsSQL;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class ItemDatoCalculadora extends Dato {
    private boolean isChecked;

    public ItemDatoCalculadora(){
        super();
    }
    public ItemDatoCalculadora(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public ItemDatoCalculadora(String id, String nombre, String descripcion, CategoriaDatos categoriaDatos, boolean isCheck) {
        super(id, nombre, descripcion, categoriaDatos);
        this.isChecked = isCheck;
    }

    public ItemDatoCalculadora(String nombre, String descripcion, CategoriaDatos categoriaDatos, boolean isChecked) {
        super(nombre, descripcion, categoriaDatos);
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
