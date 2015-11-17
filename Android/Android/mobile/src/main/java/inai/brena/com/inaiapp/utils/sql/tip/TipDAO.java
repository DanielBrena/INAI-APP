package inai.brena.com.inaiapp.utils.sql.tip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.SQLiteHelper;

/**
 * Created by DanielBrena on 16/11/15.
 */
public class TipDAO extends SQLiteHelper implements  TipImpl {

    public TipDAO(Context context) {
        super(context);
    }

    @Override
    public SQLiteHelper open() throws SQLException {
        return super.open();
    }
    @Override
    public Tip selectById(String id) {
        Cursor cursor = super.getDatabase().query(true, Tip.TABLA, null, Tip.ID + " = ?", new String[]{
                id
        }, null, null, null,null);
        Tip tip = null;
        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            tip = new Tip();
            tip.setId(cursor.getString(0));
            tip.setTitulo(cursor.getString(1));
            tip.setMensaje(cursor.getString(2));

        }
        cursor.close();
        //this.close();
        return tip;
    }

    @Override
    public List<Tip> selectAll() {
        Cursor cursor = super.getDatabase().query(Tip.TABLA, null, null, null, null, null, null);
        List<Tip> tipList = new ArrayList<>();
        Tip tip = null;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                tip = new Tip();
                tip.setId(cursor.getString(0));
                tip.setTitulo(cursor.getString(1));
                tip.setMensaje(cursor.getString(2));

                tipList.add(tip);
            }
        }
        cursor.close();
        //this.close();
        return tipList;
    }

    @Override
    public int insert(Tip tip) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Tip.ID, tip.getId());
        contentValue.put(Tip.TITULO, tip.getTitulo());
        contentValue.put(Tip.MENSAJE, tip.getMensaje());
        int resultado = (int) super.getDatabase().insert(Tip.TABLA, null, contentValue);
        //this.close();
        return resultado;
        //this.close();
    }

    @Override
    public void close() {
        super.close();
    }
}
