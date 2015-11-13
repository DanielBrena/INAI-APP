package inai.brena.com.inaiapp.utils.sql.dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatosDAO;
import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class DatoDAO extends SQLiteHelper implements DatoImpl {

    public DatoDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *
     * @param dato
     * @return
     */
    @Override
    public int insert(Dato dato) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Dato.ID, dato.getId());
        contentValue.put(Dato.NOMBRE, dato.getNombre());
        contentValue.put(Dato.DESCRIPCION, dato.getDescripcion());
        contentValue.put(Dato.CATEGORIA_DATOS, dato.getCategoriaDatos().getId());
        int resultado = (int) super.getDatabase().insert(Dato.TABLA, null, contentValue);
       // this.close();
        return resultado;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        int resultado = super.getDatabase().delete(Dato.TABLA, Dato.ID + " = ?", new String[]{
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
        int resultado = super.getDatabase().delete(Dato.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param dato
     * @return
     */
    @Override
    public int update(Dato dato) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Dato.NOMBRE, dato.getNombre());
        contentValue.put(Dato.DESCRIPCION, dato.getDescripcion());
        contentValue.put(Dato.CATEGORIA_DATOS, dato.getCategoriaDatos().getId());
        int resultado = super.getDatabase().update(Dato.TABLA, contentValue, Dato.ID + " = ?", new String[]{
                dato.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Dato> selectAll() {
        Cursor cursor = super.getDatabase().query(Dato.TABLA, null, null, null, null, null, null);
        List<Dato> datoList = new ArrayList<>();
        Dato dato;
        if (cursor.getCount() > 0) {

            try {
                CategoriaDatosDAO categoriaDatosDAO = new CategoriaDatosDAO(super.getContext());
                categoriaDatosDAO.open();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    dato = new Dato();
                    dato.setId(cursor.getString(0));
                    dato.setNombre(cursor.getString(1));
                    dato.setDescripcion(cursor.getString(2));
                    dato.setCategoriaDatos(categoriaDatosDAO.selectById(cursor.getString(3)));
                    datoList.add(dato);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
       // this.close();
        return datoList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Dato selectById(String id) {
        Cursor cursor = super.getDatabase().query(true,Dato.TABLA, null, Dato.ID + " = ?", new String[]{
                id
        }, null, null, null,null);

        Dato dato = null;
        if (cursor.getCount() > 0) {
            try {
                CategoriaDatosDAO categoriaDatosDAO = new CategoriaDatosDAO(super.getContext());
                categoriaDatosDAO.open();
                cursor.moveToFirst();
                dato = new Dato();
                dato.setId(cursor.getString(0));
                dato.setNombre(cursor.getString(1));
                dato.setDescripcion(cursor.getString(2));
                dato.setCategoriaDatos(categoriaDatosDAO.selectById(cursor.getString(3)));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
       // this.close();
        return dato;
    }

    @Override
    public void close() {
        super.close();
    }
}
