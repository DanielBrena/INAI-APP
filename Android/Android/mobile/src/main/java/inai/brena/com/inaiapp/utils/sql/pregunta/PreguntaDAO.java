package inai.brena.com.inaiapp.utils.sql.pregunta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipiosDAO;
import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class PreguntaDAO extends SQLiteHelper implements PreguntaImpl {


    public PreguntaDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     *
     * @param pregunta
     * @return
     */
    @Override
    public int insert(Pregunta pregunta) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Pregunta.ID, pregunta.getId());
        contentValue.put(Pregunta.PREGUNTA, pregunta.getPregunta());
        contentValue.put(Pregunta.CATEGORIA_PRINCIPIOS, pregunta.getCategoriaPrincipios().getId());
        int resultado = (int) super.getDatabase().insert(Pregunta.TABLA, null, contentValue);
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
        int resultado = super.getDatabase().delete(Pregunta.TABLA, Pregunta.ID + " = ?", new String[]{
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
        int resultado = super.getDatabase().delete(Pregunta.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     *
     * @param pregunta
     * @return
     */
    @Override
    public int update(Pregunta pregunta) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Pregunta.PREGUNTA, pregunta.getPregunta());
        contentValue.put(Pregunta.CATEGORIA_PRINCIPIOS, pregunta.getCategoriaPrincipios().getId());
        int resultado = super.getDatabase().update(Pregunta.TABLA, contentValue, Pregunta.ID + " = ?", new String[]{
                pregunta.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Pregunta> selectAll() {
        Cursor cursor = super.getDatabase().query(Pregunta.TABLA, null, null, null, null, null, null);
        List<Pregunta> preguntaList = new ArrayList<>();
        Pregunta pregunta = null;
        if (cursor.getCount() > 0) {
            try {
                CategoriaPrincipiosDAO categoriaPrincipios = new CategoriaPrincipiosDAO(super.getContext());
                categoriaPrincipios.open();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    pregunta = new Pregunta();
                    pregunta.setId(cursor.getString(0));
                    pregunta.setPregunta(cursor.getString(1));
                    pregunta.setCategoriaPrincipios(categoriaPrincipios.selectById(cursor.getString(2)));
                    preguntaList.add(pregunta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        //this.close();
        return preguntaList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Pregunta selectById(String id) {
        Cursor cursor = super.getDatabase().query(true, Pregunta.TABLA, null, Pregunta.ID + " = ?", new String[]{
                id
        }, null, null, null,null);
        Pregunta pregunta = null;
        if (cursor.getCount() > 0) {
            try {
                CategoriaPrincipiosDAO categoriaPrincipios = new CategoriaPrincipiosDAO(super.getContext());
                categoriaPrincipios.open();
                cursor.moveToFirst();
                pregunta = new Pregunta();
                pregunta.setId(cursor.getString(0));
                pregunta.setPregunta(cursor.getString(1));
                pregunta.setCategoriaPrincipios(categoriaPrincipios.selectById(cursor.getString(2)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        //this.close();
        return pregunta;
    }

    @Override
    public void close() {
        super.close();
    }
}
