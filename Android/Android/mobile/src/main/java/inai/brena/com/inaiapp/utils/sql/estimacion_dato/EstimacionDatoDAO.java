package inai.brena.com.inaiapp.utils.sql.estimacion_dato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;

/**
 * Created by DanielBrena on 24/10/15.
 */
public class EstimacionDatoDAO extends SQLiteHelper implements EstimacionDatoImpl {

    public EstimacionDatoDAO(Context context) {
        super(context);
    }


    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *
     * @param estimacionDato
     * @return
     */
    @Override
    public int insert(EstimacionDato estimacionDato) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(EstimacionDato.ID, estimacionDato.getId());
        contentValue.put(EstimacionDato.ESTIMACION, estimacionDato.getEstimacion().getId());
        contentValue.put(EstimacionDato.DATO, estimacionDato.getDato().getId());
        int resultado = (int) super.getDatabase().insert(EstimacionDato.TABLA, null, contentValue);
        //this.close();
        return resultado;
    }

    @Override
    public int insertAll(List<EstimacionDato> estimacionDatoList) {
        for(EstimacionDato estimacionDato: estimacionDatoList){
            this.insert(estimacionDato);
        }
        this.close();
        return 1;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        int resultado = super.getDatabase().delete(EstimacionDato.TABLA, EstimacionDato.ID + " = ?", new String[]{
                id
        });
        //this.close();
        return resultado;
    }

    @Override
    public int deleteAll() {
        int resultado = super.getDatabase().delete(EstimacionDato.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param estimacionDato
     * @return
     */
    @Override
    public int update(EstimacionDato estimacionDato) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(EstimacionDato.ESTIMACION, estimacionDato.getEstimacion().getId());
        contentValue.put(EstimacionDato.DATO, estimacionDato.getDato().getId());
        int resultado = super.getDatabase().update(EstimacionDato.TABLA, contentValue, EstimacionDato.ID + " = ?", new String[]{
                estimacionDato.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EstimacionDato> selectAll() {
        Cursor cursor = super.getDatabase().query(EstimacionDato.TABLA, null, null, null, null, null, null);
        List<EstimacionDato> estimacionDatoList = new ArrayList<>();
        EstimacionDato estimacionDato = null;
        if (cursor.getCount() > 0) {
            try {
                EstimacionDAO estimacionDAO = new EstimacionDAO(super.getContext());
                estimacionDAO.open();
                DatoDAO datoDAO = new DatoDAO(super.getContext());
                datoDAO.open();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    estimacionDato = new EstimacionDato();
                    estimacionDato.setId(cursor.getString(0));
                    estimacionDato.setEstimacion(estimacionDAO.selectById(cursor.getString(1)));
                    estimacionDato.setDato(datoDAO.selectById(cursor.getString(2)));
                    estimacionDatoList.add(estimacionDato);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        cursor.close();
        //this.close();
        return estimacionDatoList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public EstimacionDato selectById(String id) {
        Cursor cursor = super.getDatabase().query(EstimacionDato.TABLA, null, EstimacionDato.ID + " = ?", new String[]{
                id
        }, null, null, null);

        EstimacionDato estimacionDato = null;
        if (cursor.getCount() > 0) {
            try {
                EstimacionDAO estimacionDAO = new EstimacionDAO(super.getContext());
                estimacionDAO.open();
                DatoDAO datoDAO = new DatoDAO(super.getContext());
                datoDAO.open();
                cursor.moveToFirst();
                estimacionDato = new EstimacionDato();
                estimacionDato.setId(cursor.getString(0));
                estimacionDato.setEstimacion(estimacionDAO.selectById(cursor.getString(1)));
                estimacionDato.setDato(datoDAO.selectById(cursor.getString(2)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        //this.close();
        return estimacionDato;
    }

    @Override
    public List<EstimacionDato> selectByIdEstimacion(String id) {
        Cursor cursor = super.getDatabase().query(EstimacionDato.TABLA, null, EstimacionDato.ESTIMACION + " = ?", new String[]{
                id
        }, null, null, null);
        List<EstimacionDato> estimacionDatoList = new ArrayList<>();
        EstimacionDato estimacionDato = null;
        if (cursor.getCount() > 0) {
            try {
                EstimacionDAO estimacionDAO = new EstimacionDAO(super.getContext());
                estimacionDAO.open();
                DatoDAO datoDAO = new DatoDAO(super.getContext());
                datoDAO.open();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    estimacionDato = new EstimacionDato();
                    estimacionDato.setId(cursor.getString(0));
                    estimacionDato.setEstimacion(estimacionDAO.selectById(cursor.getString(1)));
                    estimacionDato.setDato(datoDAO.selectById(cursor.getString(2)));
                    estimacionDatoList.add(estimacionDato);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        cursor.close();
        //this.close();
        return estimacionDatoList;
    }


    @Override
    public void close() {
        super.close();
    }
}
