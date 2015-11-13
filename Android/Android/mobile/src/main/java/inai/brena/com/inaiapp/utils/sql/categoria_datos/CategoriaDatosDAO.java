package inai.brena.com.inaiapp.utils.sql.categoria_datos;

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
public class CategoriaDatosDAO extends SQLiteHelper  implements CategoriaDatosImpl {

    public CategoriaDatosDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }

    /**
     * Metodo para insertar una fila en la base de datos.
     * @param categoriaDatos Objeto del tipo CategoriaDatos a insertar.
     * @return El id de la fila insertada, o -1 si un error existiera.
     */
    @Override
    public int insert(CategoriaDatos categoriaDatos) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(CategoriaDatos.ID, categoriaDatos.getId());
        contentValue.put(CategoriaDatos.NOMBRE, categoriaDatos.getNombre());
        contentValue.put(CategoriaDatos.DESCRIPCION, categoriaDatos.getDescripcion());
        contentValue.put(CategoriaDatos.COLOR, categoriaDatos.getColor());
        contentValue.put(CategoriaDatos.VALOR, categoriaDatos.getValor());
        int resultado = (int) super.getDatabase().insert(CategoriaDatos.TABLA, null, contentValue);
        //this.close();
        return resultado;
    }

    /**
     * Meotodo para eliminar una fila en la base de datos.
     * @param id Id de la fila a eliminar.
     * @return El numero de las filas afectadas o un cero en caso contrario.
     */
    @Override
    public int delete(String id) {
        int resultado = super.getDatabase().delete(CategoriaDatos.TABLA, CategoriaDatos.ID + " = ?", new String[]{
                id
        });
        //this.close();
        return resultado;
    }

    /**
     * Meotodo para eliminar todas las filas en la base de datos.
     * @return El numero de las filas afectadas o un cero en caso contrario.
     */
    @Override
    public int deleteAll() {
        int resultado = super.getDatabase().delete(CategoriaDatos.TABLA, null, null);
        //this.close();
        return resultado;
    }

    /**
     * Metodo que actualiza una fila en la base de datos.
     * @param categoriaDatos Objeto del tipo CategoriaDatos a actualizar.
     * @return El numero de filas afectadas.
     */
    @Override
    public int update(CategoriaDatos categoriaDatos) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(CategoriaDatos.NOMBRE, categoriaDatos.getNombre());
        contentValue.put(CategoriaDatos.DESCRIPCION, categoriaDatos.getDescripcion());
        contentValue.put(CategoriaDatos.COLOR, categoriaDatos.getColor());
        contentValue.put(CategoriaDatos.VALOR, categoriaDatos.getValor());
        int resultado = super.getDatabase().update(CategoriaDatos.TABLA, contentValue, CategoriaDatos.ID + " = ?", new String[]{
                categoriaDatos.getId()
        });
        //this.close();
        return resultado;
    }

    /**
     *Metodo que regresa una lista de todas las filas de la base de datos..
     * @return  Lista con todas las filas de la base de datos.
     */
    @Override
    public List<CategoriaDatos> selectAll() {
        Cursor cursor = super.getDatabase().query(CategoriaDatos.TABLA, null, null, null, null, null, null);
        List<CategoriaDatos> categoriaDatosList = new ArrayList<>();
        CategoriaDatos categoriaDatos = null;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                categoriaDatos = new CategoriaDatos();
                categoriaDatos.setId(cursor.getString(0));
                categoriaDatos.setNombre(cursor.getString(1));
                categoriaDatos.setDescripcion(cursor.getString(2));
                categoriaDatos.setColor(cursor.getString(3));
                categoriaDatos.setValor(cursor.getString(4));
                categoriaDatosList.add(categoriaDatos);
            }
        }
        cursor.close();
        //this.close();
        return categoriaDatosList;
    }

    /**
     *Metodo que regresa una fila por medio de su id en la base de datos.
     * @param id Id de la fila a seleccionar.
     * @return Una fila de la base de datos.
     */
    @Override
    public CategoriaDatos selectById(String id) {
            Cursor cursor = super.getDatabase().query(true,CategoriaDatos.TABLA, null, CategoriaDatos.ID + " = ?", new String[]{
                    id
            }, null, null, null,null);
            CategoriaDatos categoriaDatos = null;
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                categoriaDatos = new CategoriaDatos();
                categoriaDatos.setId(cursor.getString(0));
                categoriaDatos.setNombre(cursor.getString(1));
                categoriaDatos.setDescripcion(cursor.getString(2));
                categoriaDatos.setColor(cursor.getString(3));
                categoriaDatos.setValor(cursor.getString(4));
            }
            cursor.close();
            //this.close();
            return categoriaDatos;
    }


    @Override
    public void close() {
        super.close();
    }
}
