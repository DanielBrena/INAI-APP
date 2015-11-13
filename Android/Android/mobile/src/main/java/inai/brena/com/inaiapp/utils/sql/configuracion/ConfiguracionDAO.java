package inai.brena.com.inaiapp.utils.sql.configuracion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;

/**
 * Created by DanielBrena on 29/10/15.
 */
public class ConfiguracionDAO extends SQLiteHelper implements ConfiguracionImpl {



    public ConfiguracionDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    @Override
    public int insert(Configuracion configuracion) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Configuracion.ID, configuracion.getId());
        contentValue.put(Configuracion.NOMBRE, configuracion.getNombre());
        contentValue.put(Configuracion.VALOR, configuracion.getValor());
        int resultado = (int) super.getDatabase().insert(Configuracion.TABLA, null, contentValue);
        // this.close();
        return resultado;
    }

    @Override
    public int delete(String id) {
        int resultado = super.getDatabase().delete(Configuracion.TABLA, Configuracion.ID + " = ?", new String[]{
                id
        });
        //this.close();
        return resultado;
    }

    @Override
    public int deleteAll() {
        int resultado = super.getDatabase().delete(Configuracion.TABLA, null, null);
        //this.close();
        return resultado;
    }

    @Override
    public int update(Configuracion configuracion) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Configuracion.ID, configuracion.getId());
        contentValue.put(Configuracion.NOMBRE, configuracion.getNombre());
        contentValue.put(Configuracion.VALOR, configuracion.getValor());
        int resultado = super.getDatabase().update(Configuracion.TABLA, contentValue, Configuracion.ID + " = ?", new String[]{
                configuracion.getId()
        });
        //this.close();
        return resultado;
    }

    @Override
    public List<Configuracion> selectAll() {
        Cursor cursor = super.getDatabase().query(Configuracion.TABLA, null, null, null, null, null, null);
        List<Configuracion> datoList = new ArrayList<>();
        Configuracion configuracion;
        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                configuracion = new Configuracion();
                configuracion.setId(cursor.getString(0));
                configuracion.setNombre(cursor.getString(1));
                configuracion.setValor(cursor.getString(2));
                datoList.add(configuracion);
            }
        }
        cursor.close();
        // this.close();
        return datoList;
    }

    @Override
    public Configuracion selectById(String id) {
        Cursor cursor = super.getDatabase().query(true, Configuracion.TABLA, null, Configuracion.ID + " = ?", new String[]{
                id
        }, null, null, null, null);

        Configuracion configuracion = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            configuracion = new Configuracion();
            configuracion.setId(cursor.getString(0));
            configuracion.setNombre(cursor.getString(1));
            configuracion.setValor(cursor.getString(2));
        }
        cursor.close();
        // this.close();
        return configuracion;
    }

    @Override
    public Configuracion selectByName(String nombre) {
        Cursor cursor = super.getDatabase().query(true, Configuracion.TABLA, null, Configuracion.NOMBRE + " = ?", new String[]{
                nombre
        }, null, null, null, null);

        Configuracion configuracion = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            configuracion = new Configuracion();
            configuracion.setId(cursor.getString(0));
            configuracion.setNombre(cursor.getString(1));
            configuracion.setValor(cursor.getString(2));
        }
        cursor.close();
        // this.close();
        return configuracion;
    }
}
