package inai.brena.com.inaiapp.utils.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipios;
import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDato;
import inai.brena.com.inaiapp.utils.sql.estimacion_pregunta.EstimacionPregunta;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;

/**
 * Created by DanielBrena on 23/10/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private Context context;

    public SQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;

    }

    public SQLiteHelper open() throws SQLException {
        this.dbHelper = new SQLiteHelper(context);
        this.database = dbHelper.getWritableDatabase();
        return this;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String categoria_datos = "create table " + CategoriaDatos.TABLA + " ( "
                                + CategoriaDatos.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + CategoriaDatos.NOMBRE + " VARCHAR, "
                                + CategoriaDatos.DESCRIPCION + " TEXT, "
                                + CategoriaDatos.COLOR + " VARCHAR, "
                                + CategoriaDatos.VALOR + " TEXT);";


        String categoria_principios = "create table " + CategoriaPrincipios.TABLA + " ( "
                + CategoriaPrincipios.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CategoriaPrincipios.NOMBRE + " VARCHAR, "
                + CategoriaPrincipios.DESCRIPCION + " TEXT);";

        String pregunta = "create table " + Pregunta.TABLA + " ( "
                + Pregunta.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Pregunta.PREGUNTA + " VARCHAR, "
                + Pregunta.CATEGORIA_PRINCIPIOS + " INTEGER, "
                + " FOREIGN KEY ("+Pregunta.CATEGORIA_PRINCIPIOS+") REFERENCES "+CategoriaPrincipios.TABLA+"("+CategoriaPrincipios.ID+"));";


        String dato = "create table " + Dato.TABLA + " ( "
                + Dato.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Dato.NOMBRE + " VARCHAR, "
                + Dato.DESCRIPCION + " TEXT, "
                + Dato.CATEGORIA_DATOS + " INTEGER,"
                + " FOREIGN KEY ("+Dato.CATEGORIA_DATOS+") REFERENCES "+CategoriaDatos.TABLA+"("+CategoriaDatos.ID+"));";

        String estimacion = "create table " + Estimacion.TABLA + " ( "
                + Estimacion.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Estimacion.NOMBRE + " VARCHAR, "
                + Estimacion.FECHA + " VARCHAR, "
                + Estimacion.PLANTILLA + " INTEGER);";

        String estimacion_dato = "create table " + EstimacionDato.TABLA + " ( "
                + EstimacionDato.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EstimacionDato.ESTIMACION + " INTEGER, "
                + EstimacionDato.DATO + " INTEGER, "
                + " FOREIGN KEY ("+EstimacionDato.ESTIMACION+") REFERENCES "+Estimacion.TABLA+"("+Estimacion.ID+"),"
                + " FOREIGN KEY ("+EstimacionDato.DATO+") REFERENCES "+ Dato.TABLA+"("+Dato.ID+"));";

        String estimacion_pregunta = "create table " + EstimacionPregunta.TABLA + " ( "
                + EstimacionPregunta.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EstimacionPregunta.ESTIMACION + " INTEGER, "
                + EstimacionPregunta.PREGUNTA + " INTEGER, "
                + EstimacionPregunta.RESPUESTA + " VARCHAR, "
                + " FOREIGN KEY ("+EstimacionPregunta.ESTIMACION+") REFERENCES "+Estimacion.TABLA+"("+Estimacion.ID+"),"
                + " FOREIGN KEY ("+EstimacionPregunta.PREGUNTA+") REFERENCES "+ Pregunta.TABLA+"("+Pregunta.ID+"));";

        String configuracion = "create table " + Configuracion.TABLA + " ( "
                + Configuracion.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Configuracion.NOMBRE + " VARCHAR, "
                + Configuracion.VALOR + " VARCHAR  )";



        sqLiteDatabase.execSQL(categoria_datos);
        sqLiteDatabase.execSQL(categoria_principios);
        sqLiteDatabase.execSQL(pregunta);
        sqLiteDatabase.execSQL(dato);
        sqLiteDatabase.execSQL(estimacion);
        sqLiteDatabase.execSQL(estimacion_dato);
        sqLiteDatabase.execSQL(estimacion_pregunta);
        sqLiteDatabase.execSQL(configuracion);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public SQLiteHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(SQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Context getContext() {
        return context;
    }

    public void close(){
        this.database.close();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoriaDatos.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoriaPrincipios.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Pregunta.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Dato.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Estimacion.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EstimacionDato.TABLA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EstimacionPregunta.TABLA);
        onCreate(sqLiteDatabase);

    }
}
