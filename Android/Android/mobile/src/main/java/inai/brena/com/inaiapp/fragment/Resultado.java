package inai.brena.com.inaiapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import org.ispeech.SpeechSynthesis;
import org.ispeech.SpeechSynthesisEvent;
import org.ispeech.error.BusyException;
import org.ispeech.error.InvalidApiKeyException;
import org.ispeech.error.NoNetworkException;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import inai.brena.com.inaiapp.MainActivity;
import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.serializable.PreguntaSerializable;
import inai.brena.com.inaiapp.utils.sql.GenerarCalculo;
import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;
import inai.brena.com.inaiapp.utils.sql.configuracion.ConfiguracionDAO;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDato;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_pregunta.EstimacionPregunta;
import inai.brena.com.inaiapp.utils.sql.estimacion_pregunta.EstimacionPreguntaDAO;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;

/**
 * A simple {@link Fragment} subclass.
 */
public class Resultado extends Fragment {
    private static final String TAG = "s";
    private Context context;
    private DatoSerializable datoSerializable;
    private PreguntaSerializable preguntaSerializable;
    private TextView resultado;
    //TTS object
    private TextToSpeech tts;
    private double r = 0;
    //status check code

    ImageView estadar_i ,especial_i,sensible_i;

    TextView estandar_t ,especial_t,sensible_t;

    TextView estandar_s ,especial_s,sensible_s;

    TextView transparencia ,confianza,control,valoracion;



    SpeechSynthesis synthesis;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.prepareTTSEngine();
        synthesis.setStreamType(AudioManager.STREAM_MUSIC);
    }

    public Resultado() {
        // Required empty public constructor
    }
    public static Resultado newInstance(DatoSerializable arguments1, PreguntaSerializable arguments2){
        Resultado f = new Resultado();
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", arguments1);
        bundle.putSerializable("preguntas", arguments2);
        if(arguments1 != null && arguments2 != null){
            f.setArguments(bundle);
        }
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = container.getContext();
        View rootView =  inflater.inflate(R.layout.fragment_resultado, container, false);
        datoSerializable = (DatoSerializable) getArguments().getSerializable("datos");
        //Toast.makeText(context,String.valueOf(datoSerializable.getDatoList().size()), Toast.LENGTH_SHORT).show();
        preguntaSerializable =  (PreguntaSerializable) getArguments().getSerializable("preguntas");

        this.cargar_estadisticas(rootView);
        this.resultado = (TextView)rootView.findViewById(R.id.estimacion_resultado_id);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.cargar_resultado();

       // this.cargar_voz();




        ConvertTextToSpeech(String.valueOf(r));
    }

    private void cargar_estadisticas(View view){

         estadar_i = (ImageView)view.findViewById(R.id.imageViewEstandar);
         especial_i = (ImageView)view.findViewById(R.id.imageViewEspecial);
         sensible_i = (ImageView)view.findViewById(R.id.imageViewSensible);

         estandar_t = (TextView) view.findViewById(R.id.textViewEstandarTitulo);
         especial_t = (TextView) view.findViewById(R.id.textViewEspecialTitulo);
         sensible_t = (TextView) view.findViewById(R.id.textViewSensibleTitulo);

         estandar_s = (TextView) view.findViewById(R.id.textViewEstandarSubTitulo);
         especial_s = (TextView) view.findViewById(R.id.textViewEspecialSubTitulo);
         sensible_s = (TextView) view.findViewById(R.id.textViewSensibleSubTitulo);

        transparencia = (TextView) view.findViewById(R.id.tabla_transparencia_respuesta);
        confianza = (TextView) view.findViewById(R.id.tabla_confianza_respuesta);
        control = (TextView) view.findViewById(R.id.tabla_control_respuesta);
        valoracion = (TextView) view.findViewById(R.id.tabla_valoracion_respuesta);


    }


    private void prepareTTSEngine() {
        try {
            synthesis = SpeechSynthesis.getInstance(getActivity());
            synthesis.setSpeechSynthesisEvent(new SpeechSynthesisEvent() {

                public void onPlaySuccessful() {
                    Log.i(TAG, "onPlaySuccessful");
                }

                public void onPlayStopped() {
                    Log.i(TAG, "onPlayStopped");
                }

                public void onPlayFailed(Exception e) {
                    Log.e(TAG, "onPlayFailed");


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Error[TTSActivity]: " + e.toString())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                public void onPlayStart() {
                    Log.i(TAG, "onPlayStart");
                }

                @Override
                public void onPlayCanceled() {
                    Log.i(TAG, "onPlayCanceled");
                }
            });


        } catch (InvalidApiKeyException e) {
            Log.e(TAG, "Invalid API key\n" + e.getStackTrace());
            Toast.makeText(this.context, "ERROR: Invalid API key", Toast.LENGTH_LONG).show();
        }
    }

    private void cargar_resultado(){
        final ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this.context);
        try {
            configuracionDAO.open();
            DecimalFormat decim = new DecimalFormat("0.00");

            GenerarCalculo generarCalculo = new GenerarCalculo(context,datoSerializable.getDatoList(),preguntaSerializable.getItemPreguntaPreguntasList(), configuracionDAO.selectById("1"));
            this.r = generarCalculo.resultado();
            this.r = Double.parseDouble(decim.format(this.r));
            this.resultado.setText("$ " + this.r + "0");
            this.resultado.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);

            this.estadar_i.setImageDrawable(this.generarImageViewNiveles("#4CAF50", "E"));
            this.especial_i.setImageDrawable(this.generarImageViewNiveles("#FFC107", "E"));
            this.sensible_i.setImageDrawable(this.generarImageViewNiveles("#F44336", "S"));

            this.estandar_t.setText(String.valueOf(generarCalculo.calculo_datos_estandar()));
            this.especial_t.setText(String.valueOf(generarCalculo.calculo_datos_especial()));
            this.sensible_t.setText(String.valueOf(generarCalculo.calculo_datos_sensible()));


            this.transparencia.setText((this.preguntaSerializable.getItemPreguntaPreguntasList().get(0).isChecked() ? "Si" : "No"));
            this.confianza.setText((this.preguntaSerializable.getItemPreguntaPreguntasList().get(1).isChecked() ? "Si" : "No"));
            this.control.setText((this.preguntaSerializable.getItemPreguntaPreguntasList().get(2).isChecked() ? "Si" : "No"));
            this.valoracion.setText((this.preguntaSerializable.getItemPreguntaPreguntasList().get(3).isChecked() ? "Si" : "No"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TextDrawable generarImageViewNiveles(String color,String letra){
        TextDrawable drawable= TextDrawable.builder()
                .beginConfig()
                .withBorder(2)
                .toUpperCase()
                .textColor(Color.parseColor(color))
                .useFont(Typeface.DEFAULT)
                .fontSize(30)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(letra, Color.WHITE);
        return drawable;
    }

    private void cargar_voz(){
        tts = new TextToSpeech(this.context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(new Locale("es","ES"));
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "Lenguaje no soportado");
                    }
                    else{
                        ConvertTextToSpeech(String.valueOf(r));
                    }
                }
                else
                    Log.e("error", "Falló inicializacion!");
            }
        });
    }

    private void ConvertTextToSpeech(String s) {


        try {

            synthesis.setVoiceType("usspanishfemale");
            synthesis.speak("Tú estimación económica es de: " + s + " pesos");

        } catch (BusyException e) {
            Log.e(TAG, "SDK is busy");
            e.printStackTrace();
            Toast.makeText(this.context, "ERROR: SDK is busy", Toast.LENGTH_LONG).show();
        } catch (NoNetworkException e) {
            Log.e(TAG, "Network is not available\n" + e.getStackTrace());
            Toast.makeText(this.context, "ERROR: Network is not available", Toast.LENGTH_LONG).show();
        }
        /*HashMap<String, String> hash = new HashMap<String,String>();
        hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                String.valueOf(AudioManager.STREAM_NOTIFICATION));
        tts.speak("Tú estimación económica es de: " +s+ " pesos",TextToSpeech.QUEUE_ADD,hash);
*/
    }
    private void guardarPreguntas(Estimacion estimacion){

        EstimacionPreguntaDAO estimacionPreguntaDAO = new EstimacionPreguntaDAO(this.context);
        List<EstimacionPregunta> estimacionPreguntaList = new ArrayList<>();
        try {
            estimacionPreguntaDAO.open();
            for(ItemPreguntaPreguntas itemPreguntaPreguntas: this.preguntaSerializable.getItemPreguntaPreguntasList()){
                estimacionPreguntaList.add(new EstimacionPregunta(estimacion,itemPreguntaPreguntas,String.valueOf(itemPreguntaPreguntas.isChecked())));
            }
            estimacionPreguntaDAO.insertAll(estimacionPreguntaList);
            estimacionPreguntaDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatos(Estimacion estimacion) {
        EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(this.context);

        List<EstimacionDato> estimacionDatoList = new ArrayList<>();

        try {

            estimacionDatoDAO.open();

            for (Dato dato: this.datoSerializable.getDatoList()){
                estimacionDatoList.add(new EstimacionDato(estimacion,dato));
            }
            estimacionDatoDAO.insertAll(estimacionDatoList);
            estimacionDatoDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Estimacion guardarEstimacion(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        EstimacionDAO estimacionDAO = new EstimacionDAO(this.context);
        Estimacion estimacion = null;
        try {
            estimacionDAO.open();
            estimacion = new Estimacion(df.format(c.getTime())+" Estimación ",df.format(c.getTime()),"0");
            int id = estimacionDAO.insert(estimacion);
            estimacion.setId(String.valueOf(id));
            estimacionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estimacion;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_resultado, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_action_siguiente_3_2:
                Estimacion estimacion = this.guardarEstimacion();
                this.guardarDatos(estimacion);
                this.guardarPreguntas(estimacion);
                this.principal();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void principal(){
        Fragment newFragment = new Calculadora();
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, newFragment);
        transaction.disallowAddToBackStack();

        transaction.commit();
    }
}
