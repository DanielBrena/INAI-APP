package inai.brena.com.inaiapp.utils.sql.estimacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class EstimacionDAO extends SQLiteHelper implements EstimacionImpl {

    public EstimacionDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *
     * @param estimacion
     * @return
     */
    @Override
    public int insert(Estimacion estimacion) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Estimacion.ID, estimacion.getId());
        contentValue.put(Estimacion.NOMBRE, estimacion.getNombre());
        contentValue.put(Estimacion.FECHA, estimacion.getFecha());
        contentValue.put(Estimacion.PLANTILLA, estimacion.getPlantilla());
        int resultado = (int) super.getDatabase().insert(Estimacion.TABLA, null, contentValue);
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
        int resultado = super.getDatabase().delete(Estimacion.TABLA, Estimacion.ID + " = ?", new String[]{
                id
        });
        this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public int deleteAll() {
        int resultado = super.getDatabase().delete(Estimacion.TABLA, null, null);
        this.close();
        return resultado;
    }

    /**
     *
     * @param estimacion
     * @return
     */
    @Override
    public int update(Estimacion estimacion) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Estimacion.NOMBRE, estimacion.getNombre());
        contentValue.put(Estimacion.FECHA, estimacion.getFecha());
        contentValue.put(Estimacion.PLANTILLA, estimacion.getPlantilla());
        int resultado = super.getDatabase().update(Estimacion.TABLA, contentValue, Estimacion.ID + " = ?", new String[]{
                estimacion.getId()
        });
        this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Estimacion> selectAll() {
        Cursor cursor = super.getDatabase().query(Estimacion.TABLA, null, null, null, null, null, null);
        List<Estimacion> estimacionList = new ArrayList<>();
        Estimacion estimacion = null;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                estimacion = new Estimacion();
                estimacion.setId(cursor.getString(0));
                estimacion.setNombre(cursor.getString(1));
                estimacion.setFecha(cursor.getString(2));
                estimacion.setPlantilla(cursor.getString(3));
                estimacionList.add(estimacion);
            }
        }
        cursor.close();
        //this.close();
        return estimacionList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Estimacion> selectAllByPlantilla(String tipo) {
        Cursor cursor = super.getDatabase().query(Estimacion.TABLA, null, Estimacion.PLANTILLA + " = ?", new String[]{
                tipo
        }, null, null, null);

        List<Estimacion> estimacionList = new ArrayList<>();
        Estimacion estimacion = null;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                estimacion = new Estimacion();
                estimacion.setId(cursor.getString(0));
                estimacion.setNombre(cursor.getString(1));
                estimacion.setFecha(cursor.getString(2));
                estimacion.setPlantilla(cursor.getString(3));
                estimacionList.add(estimacion);
            }
        }
        cursor.close();
        //this.close();
        return estimacionList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Estimacion selectById(String id) {
        Cursor cursor = super.getDatabase().query(Estimacion.TABLA, null, Estimacion.ID + " = ?", new String[]{
                id
        }, null, null, null);

        Estimacion estimacion = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            estimacion = new Estimacion();
            estimacion.setId(cursor.getString(0));
            estimacion.setNombre(cursor.getString(1));
            estimacion.setFecha(cursor.getString(2));
            estimacion.setPlantilla(cursor.getString(3));
        }
        cursor.close();
        //this.close();
        return estimacion;
    }

    @Override
    public void close() {
        super.close();
    }
}
