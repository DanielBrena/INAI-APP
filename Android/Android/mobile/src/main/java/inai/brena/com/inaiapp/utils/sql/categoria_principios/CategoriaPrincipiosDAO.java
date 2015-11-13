package inai.brena.com.inaiapp.utils.sql.categoria_principios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class CategoriaPrincipiosDAO extends SQLiteHelper implements CategoriaPrincipiosImpl {

    public CategoriaPrincipiosDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *Metodo para insertar una fila en la base de datos.
     * @param categoriaPrincipios
     * @return
     */
    @Override
    public int insert(CategoriaPrincipios categoriaPrincipios) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(CategoriaPrincipios.ID, categoriaPrincipios.getId());
        contentValue.put(CategoriaPrincipios.NOMBRE, categoriaPrincipios.getNombre());
        contentValue.put(CategoriaPrincipios.DESCRIPCION, categoriaPrincipios.getDescripcion());
        int resultado = (int) super.getDatabase().insert(CategoriaPrincipios.TABLA, null, contentValue);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        int resultado = super.getDatabase().delete(CategoriaPrincipios.TABLA, CategoriaPrincipios.ID + " = ?", new String[]{
                id
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public int deleteAll() {
        int resultado = super.getDatabase().delete(CategoriaPrincipios.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param categoriaPrincipios
     * @return
     */
    @Override
    public int update(CategoriaPrincipios categoriaPrincipios) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(CategoriaPrincipios.NOMBRE, categoriaPrincipios.getNombre());
        contentValue.put(CategoriaPrincipios.DESCRIPCION, categoriaPrincipios.getDescripcion());
        int resultado = super.getDatabase().update(CategoriaPrincipios.TABLA, contentValue, CategoriaPrincipios.ID + " = ?", new String[]{
                categoriaPrincipios.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<CategoriaPrincipios> selectAll() {
        Cursor cursor = super.getDatabase().query(CategoriaPrincipios.TABLA, null, null, null, null, null, null);
        List<CategoriaPrincipios> categoriaDatosList = new ArrayList<>();
        CategoriaPrincipios categoriaPrincipios = null;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                categoriaPrincipios = new CategoriaPrincipios();
                categoriaPrincipios.setId(cursor.getString(0));
                categoriaPrincipios.setNombre(cursor.getString(1));
                categoriaPrincipios.setDescripcion(cursor.getString(2));
                categoriaDatosList.add(categoriaPrincipios);
            }
        }
        cursor.close();
        //this.close();
        return categoriaDatosList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public CategoriaPrincipios selectById(String id) {
        Cursor cursor = super.getDatabase().query(true, CategoriaPrincipios.TABLA, null, CategoriaPrincipios.ID + " = ?", new String[]{
                id
        }, null, null, null,null);
        CategoriaPrincipios categoriaPrincipios = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            categoriaPrincipios = new CategoriaPrincipios();
            categoriaPrincipios.setId(cursor.getString(0));
            categoriaPrincipios.setNombre(cursor.getString(1));
            categoriaPrincipios.setDescripcion(cursor.getString(2));
        }
        cursor.close();
        //this.close();
        return categoriaPrincipios;
    }

    @Override
    public void close() {
        super.close();
    }
}
