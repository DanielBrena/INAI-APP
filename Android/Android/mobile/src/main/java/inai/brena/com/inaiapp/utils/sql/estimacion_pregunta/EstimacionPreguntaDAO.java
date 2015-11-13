package inai.brena.com.inaiapp.utils.sql.estimacion_pregunta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.pregunta.PreguntaDAO;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class EstimacionPreguntaDAO extends SQLiteHelper implements EstimacionPreguntaImpl {


    public EstimacionPreguntaDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *
     * @param estimacionPregunta
     * @return
     */
    @Override
    public int insert(EstimacionPregunta estimacionPregunta) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(EstimacionPregunta.ID, estimacionPregunta.getId());
        contentValue.put(EstimacionPregunta.ESTIMACION, estimacionPregunta.getEstimacion().getId());
        contentValue.put(EstimacionPregunta.PREGUNTA, estimacionPregunta.getPregunta().getId());
        contentValue.put(EstimacionPregunta.RESPUESTA, estimacionPregunta.getRespuesta());
        int resultado = (int) super.getDatabase().insert(EstimacionPregunta.TABLA, null, contentValue);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param estimacionPreguntaList
     * @return
     */
    @Override
    public int insertAll(List<EstimacionPregunta> estimacionPreguntaList) {
        for(EstimacionPregunta estimacionPregunta :  estimacionPreguntaList){
            this.insert(estimacionPregunta);
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
        int resultado = super.getDatabase().delete(EstimacionPregunta.TABLA, EstimacionPregunta.ID + " = ?", new String[]{
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
        int resultado = super.getDatabase().delete(EstimacionPregunta.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param estimacionPregunta
     * @return
     */
    @Override
    public int update(EstimacionPregunta estimacionPregunta) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(EstimacionPregunta.ESTIMACION, estimacionPregunta.getEstimacion().getId());
        contentValue.put(EstimacionPregunta.PREGUNTA, estimacionPregunta.getPregunta().getId());
        contentValue.put(EstimacionPregunta.RESPUESTA, estimacionPregunta.getRespuesta());
        int resultado = super.getDatabase().update(EstimacionPregunta.TABLA, contentValue, EstimacionPregunta.ID + " = ?", new String[]{
                estimacionPregunta.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EstimacionPregunta> selectAll() {
        Cursor cursor = super.getDatabase().query(EstimacionPregunta.TABLA, null, null, null, null, null, null);
        List<EstimacionPregunta> estimacionPreguntaList = new ArrayList<>();
        EstimacionPregunta estimacionPregunta = null;
        if (cursor.getCount() > 0) {
            try {
                PreguntaDAO preguntaDAO = new PreguntaDAO(super.getContext());
                preguntaDAO.open();
                EstimacionDAO estimacionDAO = new EstimacionDAO(super.getContext());
                estimacionDAO.open();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    estimacionPregunta = new EstimacionPregunta();
                    estimacionPregunta.setId(cursor.getString(0));
                    estimacionPregunta.setEstimacion(estimacionDAO.selectById(cursor.getString(1)));
                    estimacionPregunta.setPregunta(preguntaDAO.selectById(cursor.getString(2)));
                    estimacionPregunta.setRespuesta(cursor.getString(3));
                    estimacionPreguntaList.add(estimacionPregunta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        //this.close();
        return estimacionPreguntaList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public EstimacionPregunta selectById(String id) {
        Cursor cursor = super.getDatabase().query(true,EstimacionPregunta.TABLA, null, Dato.ID + " = ?", new String[]{
                id
        }, null, null, null,null);
        EstimacionPregunta estimacionPregunta = null;
        if (cursor.getCount() > 0) {
            try {
                PreguntaDAO preguntaDAO = new PreguntaDAO(super.getContext());
                preguntaDAO.open();
                EstimacionDAO estimacionDAO = new EstimacionDAO(super.getContext());
                estimacionDAO.open();
                cursor.moveToFirst();
                estimacionPregunta = new EstimacionPregunta();
                estimacionPregunta.setId(cursor.getString(0));
                estimacionPregunta.setEstimacion(estimacionDAO.selectById(cursor.getString(1)));
                estimacionPregunta.setPregunta(preguntaDAO.selectById(cursor.getString(2)));
                estimacionPregunta.setRespuesta(cursor.getString(3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        //this.close();
        return estimacionPregunta;
    }

    @Override
    public void close() {
        super.close();
    }
}
