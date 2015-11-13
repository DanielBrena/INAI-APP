package inai.brena.com.inaiapp.utils.sql;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import com.amulyakhare.textdrawable.TextDrawable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;
import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatosDAO;
import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;

/**
 * Created by DanielBrena on 29/10/15.
 */
public class GenerarCalculo {
    private List<Dato> datos;
    private List<ItemPreguntaPreguntas> preguntas;
    private Configuracion configuracion;
    private Context context;

    private CategoriaDatos categoria_estandar;
    private CategoriaDatos categoria_sensible;
    private CategoriaDatos categoria_especial;

    public GenerarCalculo(Context context, List<Dato> datos,List<ItemPreguntaPreguntas> preguntas, Configuracion configuracion) {
        this.preguntas = preguntas;
        this.datos = datos;
        this.configuracion = configuracion;
        this.context = context;

       this.initCategorias();
    }

    public GenerarCalculo(Context context) {
        this.context = context;
        this.datos = new ArrayList<>();
        this.preguntas = new ArrayList<>();
        this.configuracion = new Configuracion();
        this.initCategorias();
    }

    public double resultado(){
        double resultado = 0.0;
        resultado = ( (double)this.resultado_nominador() / (double)this.resultado_denominador()) * this.calculo_ep() * Integer.parseInt(this.configuracion.getValor()) ;
        Log.d("s1","configuracion " + String.valueOf(this.configuracion.getValor()));
        return resultado;
    }

    private int resultado_nominador(){
        int resultado = 0;
        resultado = this.calculo_datos_estandar()   *   Integer.parseInt(this.categoria_estandar.getValor())  +
                this.calculo_datos_sensible()   *   Integer.parseInt(this.categoria_sensible.getValor())  +
                this.calculo_datos_especial()   *   Integer.parseInt(this.categoria_especial.getValor());
        Log.d("s1","nominador " + String.valueOf(resultado));
        return resultado;
    }

    private int resultado_denominador(){
        int resultado = 0;
        resultado = this.calculo_datos_estandar()   +
                    this.calculo_datos_sensible()   +
                    this.calculo_datos_especial();
        Log.d("s1","denominador " + String.valueOf(resultado));
        return resultado;
    }



    public int calculo_datos_estandar(){
        int resultado = 0;
        for(Dato dato:  this.datos){
            if(dato.getCategoriaDatos().getId().equals(this.categoria_estandar.getId())){
                resultado += 1;
            }
        }
        //Log.d("s1",String.valueOf(resultado));
        return resultado;
    }



    public int calculo_datos_sensible(){
        int resultado = 0;
        for(Dato dato:  this.datos){
            if(dato.getCategoriaDatos().getId().equals(this.categoria_sensible.getId())){
                resultado += 1;
            }
        }
        return resultado;
    }

    public int calculo_datos_especial(){
        int resultado = 0;
        for(Dato dato:  this.datos){
            if(dato.getCategoriaDatos().getId().equals(this.categoria_especial.getId())){
                resultado += 1;
            }
        }
        return resultado;
    }

    private int calculo_ep(){
        int resultado = 1;
        for(ItemPreguntaPreguntas pregunta: this.preguntas){
            Log.d("s1","ep value " + String.valueOf(pregunta.isChecked()));
            if(!pregunta.isChecked()){
                resultado += 1;
            }
        }
        Log.d("s1","ep " + String.valueOf(resultado));
        return resultado;

    }

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }

    private void initCategorias(){
        CategoriaDatosDAO categoriaDatos = new CategoriaDatosDAO(this.context);
        try {
            categoriaDatos.open();
            this.categoria_estandar = categoriaDatos.selectById("1");
            this.categoria_sensible = categoriaDatos.selectById("2");
            this.categoria_especial = categoriaDatos.selectById("3");
            categoriaDatos.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
