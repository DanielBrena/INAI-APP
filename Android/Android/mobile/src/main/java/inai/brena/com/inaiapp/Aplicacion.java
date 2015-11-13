package inai.brena.com.inaiapp;

import android.util.Log;

import java.sql.SQLException;

import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatosDAO;
import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipios;
import inai.brena.com.inaiapp.utils.sql.categoria_principios.CategoriaPrincipiosDAO;
import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;
import inai.brena.com.inaiapp.utils.sql.configuracion.ConfiguracionDAO;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDato;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_pregunta.EstimacionPregunta;
import inai.brena.com.inaiapp.utils.sql.estimacion_pregunta.EstimacionPreguntaDAO;
import inai.brena.com.inaiapp.utils.sql.preference.MyPreference;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;
import inai.brena.com.inaiapp.utils.sql.pregunta.PreguntaDAO;


/**
 * Created by DanielBrena on 26/09/15.
 */
public class Aplicacion extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();


        if(!MyPreference.exist(getApplicationContext(), "datos_precargados")){

            this.carga_configuracion();
            this.carga_categoria();
            this.cargar_datos();
            this.carga_categoria2();
            this.carga_preguntas();
            MyPreference.add(getApplicationContext(), "datos_precargados", "si");

        }




    }
    private void carga_configuracion(){
        ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(getApplicationContext());
        try {
            configuracionDAO.open();
            configuracionDAO.insert(new Configuracion("1","unidad_monetaria","150"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void carga_categoria2(){

        try {
            CategoriaPrincipiosDAO categoriaPrincipiosDAO = new CategoriaPrincipiosDAO(getApplicationContext());
            categoriaPrincipiosDAO.open();
            categoriaPrincipiosDAO.insert(new CategoriaPrincipios("1", "Transparencia", "La percepción del titular respecto a la claridad del tratamiento que el responsable le da a sus datos."));
             categoriaPrincipiosDAO.open();
            categoriaPrincipiosDAO.insert(new CategoriaPrincipios("2", "Confianza", "La confianza que el titular experimenta al proporcionar su información personal al responsable, a cambio de recibir un producto o servicio."));
            categoriaPrincipiosDAO.open();
            categoriaPrincipiosDAO.insert(new CategoriaPrincipios("3", "Control", " La capacidad del titular para ejercer de manera efectiva sus derechos de Acceso, Rectificación, Cancelación y Oposición de los datos que proporciona al responsable."));
            categoriaPrincipiosDAO.open();
            categoriaPrincipiosDAO.insert(new CategoriaPrincipios("4","Valoración","La percepción del titular respecto a si sus datos serán explotados para un fin distinto al original de cuando fueron recabados."));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carga_preguntas(){
        PreguntaDAO preguntaDAO = new PreguntaDAO(getApplicationContext());
        CategoriaPrincipiosDAO categoriaPrincipiosDAO = new CategoriaPrincipiosDAO(getApplicationContext());

        try {
            preguntaDAO.open();
            categoriaPrincipiosDAO.open();
            preguntaDAO.insert(new Pregunta("1", "¿Considera que el responsable es claro respecto al tratamiento que dará a sus datos personales?", categoriaPrincipiosDAO.selectById("1")));
            preguntaDAO.open();
            categoriaPrincipiosDAO.open();
            preguntaDAO.insert(new Pregunta("2", "¿Al proporcionar su información personal para recibir un producto o servicio, el responsable le inspira confianza?", categoriaPrincipiosDAO.selectById("2")));
            preguntaDAO.open();
            categoriaPrincipiosDAO.open();
            preguntaDAO.insert(new Pregunta("3", "¿Siente que el responsable le proporciona mecanismos suficientes para acceder, rectificar, cancelar u oponerse al tratamiento de la información personal que le proporcionó? ", categoriaPrincipiosDAO.selectById("3")));
            preguntaDAO.open();
            categoriaPrincipiosDAO.open();
            preguntaDAO.insert(new Pregunta("4","¿Percibe que los datos personales que proporcionó tienen un valor adicional para el responsable, de modo que puedan ser explotados posteriormente? ",categoriaPrincipiosDAO.selectById("4")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carga_categoria(){

        try {
            CategoriaDatosDAO categoriaDatos = new CategoriaDatosDAO(getApplicationContext());
            categoriaDatos.open();
            categoriaDatos.insert(new CategoriaDatos("1", "Nivel estandar", "Esta categoría considera información de identificación, contacto datos laborales y académicos de una persona física identificada o identificable.","#4CAF50", "1"));
            categoriaDatos.open();
            categoriaDatos.insert(new CategoriaDatos("2", "Nivel sensible", "Esta categoría contempla los datos que permiten conocer la ubicación física de la persona, tales como la dirección física e información relativa al tránsito de las personas dentro y fuera del país.","#F44336", "2"));
            categoriaDatos.open();
            categoriaDatos.insert(new CategoriaDatos("3", "Nivel especial", "Esta categoría corresponde a los datos cuya propia naturaleza, o bien debido a un cambio excepcional en el contexto de las operaciones usuales de la organización, pueden causar daño directo al patrimonio o seguridad de los titulares.","#FFC107", "3"));
//            categoriaDatos.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void cargar_datos(){

        try {
            DatoDAO datos = new DatoDAO(getApplicationContext());
            datos.open();
            CategoriaDatosDAO categoriaDatosDAO = new CategoriaDatosDAO(getApplicationContext());
            categoriaDatosDAO.open();
            datos.insert(new Dato("1", "Nombre", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("2","Teléfono", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("3","Edad", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("4","Sexo", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("5","RFC", "Registro Federal del Contribuyente", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("6","CURP", "Clave Única del Registro de Poblacion", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("7","Estado Civil", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("8","Dirección de Correo Electrónico", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("9","Lugar y Fecha de Nacimiento", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("10","Nacionalidad", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("11","Puesto de Trabajo", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("12","Lugar de Trabajo", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("13","Experiencia Laboral", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("14","Datos de Contacto Laboral", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("15","Idioma o Lengua", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("16","Escolaridad", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("17","Trayectoria Educativa", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("18","Título", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("19","Certificados", "", categoriaDatosDAO.selectById("1")));
            datos.insert(new Dato("20","Cédula Profesional", "", categoriaDatosDAO.selectById("1")));


            datos.insert(new Dato("21","Saldo Bancario","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("22","Estado o Número de Cuenta","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("23","Cuentas de Inversion","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("24","Bienes Muebles e Inmuebles","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("25","Informacion Fiscal","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("26","Historial Crediticio","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("27","Ingresos","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("28","Egresos","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("29","Buró de Credito","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("30","Seguros","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("31","Afores","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("32","Fianzas","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("33","Tarjeta de Credito o Débito","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("34","Contraseñas","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("35","Información Biométrica","Huellas dactilares, iris, voz, entre otros.",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("36","Firma autógrafa","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("37","Firma eletrónica","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("38","Antecedentes Penales","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("39","Amparos","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("40","Demandas","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("41","Contratos","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("42","Litigios","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("43","Origen Racial o Étnico","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("44","Estado de Salud","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("45","Información Genética","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("46","Creencias Religiosas","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("47","Creencias Filosóficas y Morales","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("48","Afiliación Sindical","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("49","Opiniones politicas","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("50","Preferencia Sexual","",categoriaDatosDAO.selectById("2")));
            datos.insert(new Dato("51","Hábitos Sexuales","",categoriaDatosDAO.selectById("2")));


            datos.insert(new Dato("52","Fecha Vencimiento de Tarjeta Bancaria","",categoriaDatosDAO.selectById("3")));
            datos.insert(new Dato("53","Código de Seguridad de Tarjeta Bancaria","",categoriaDatosDAO.selectById("3")));
            datos.insert(new Dato("54","Datos de Banda Magnética","",categoriaDatosDAO.selectById("3")));
            datos.insert(new Dato("55","PIN","Número de Identificación Personal",categoriaDatosDAO.selectById("3")));
            
            //datos.close();
            //categoriaDatosDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void eliminar(){
        try {
            //CategoriaDatosDAO categoriaDatos = categoriaDatosDAO;
            //categoriaDatos.open();
           //categoriaDatos.deleteAll();
           /* CategoriaPrincipiosDAO categoriaPrincipios = new CategoriaPrincipiosDAO(getApplicationContext());
            categoriaPrincipios.open();
            categoriaPrincipios.deleteAll();*/
            DatoDAO datoDAO = new DatoDAO(getApplicationContext());
            datoDAO.open();
            datoDAO.deleteAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void mostrar(){

        try {
           /* PreguntaDAO preguntaDAO = new PreguntaDAO(getApplicationContext());
            preguntaDAO.open();

            for (Pregunta c : preguntaDAO.selectAll()){
                Log.d("s",c.getId() + " " +c.getPregunta());
            }*/

            /*CategoriaDatosDAO categoriaDatosDAO = new CategoriaDatosDAO(getApplicationContext());
            categoriaDatosDAO.open();
            for(CategoriaDatos d : categoriaDatosDAO.selectAll()){
                Log.d("s",d.getId() + " " +d.getValor());
            }*/

            Log.d("s","Estimacion Dato");
            EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(getApplicationContext());
            estimacionDatoDAO.open();
            for(EstimacionDato d : estimacionDatoDAO.selectAll()){
                Log.d("s",d.getId() + " "+ d.getEstimacion().getNombre() + " " +d.getDato().getNombre());
            }
            estimacionDatoDAO.close();

            Log.d("s","Estimacion");
            EstimacionDAO estimacionDAO = new EstimacionDAO(getApplicationContext());
            estimacionDAO.open();
            for(Estimacion d : estimacionDAO.selectAll()){
                Log.d("s",d.getId() + " " +d.getNombre());
            }
            estimacionDAO.close();

            Log.d("s","Estimacion Preguntas");
            EstimacionPreguntaDAO estimacionPreguntaDAO = new EstimacionPreguntaDAO(getApplicationContext());
            estimacionPreguntaDAO.open();
            for(EstimacionPregunta d : estimacionPreguntaDAO.selectAll()){
                Log.d("s",d.getId() + " " +d.getRespuesta());
            }
            estimacionPreguntaDAO.close();

           /*DatoDAO datoDAO = new DatoDAO(getApplicationContext());
            datoDAO.open();
            for(Dato d : datoDAO.selectAll()){
                Log.d("s",d.getId() + " " +d.getNombre() + " " + d.getCategoriaDatos().getNombre());
            }*/

            /*ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(getApplicationContext());
            configuracionDAO.open();
            for(Configuracion c : configuracionDAO.selectAll()){
                Log.d("c",c.getNombre());
            }*/


            //Dato dato = datoDAO.selectById("1");
            //Log.d("s",dato.getNombre());
            //Log.d("s",String.valueOf(categoriaDatos.selectAll().size()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
