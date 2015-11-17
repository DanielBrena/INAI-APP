package inai.brena.com.inaiapp.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentIntegrator;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentResult;
import inai.brena.com.inaiapp.utils.sql.GenerarCalculo;
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
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;
import inai.brena.com.inaiapp.utils.sql.tip.Tip;
import inai.brena.com.inaiapp.utils.sql.tip.TipDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class Principal extends Fragment {
    Context context;
    TextView fecha,resultado;
    //private AlertDialog alertDialogTip;

    public Principal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }






    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /*ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this.context);
        try {
            configuracionDAO.open();
            Configuracion configuracion = configuracionDAO.selectById("2");

            if( Boolean.parseBoolean(configuracion.getValor())){
                TipDAO tipDAO = new TipDAO(this.context);
                tipDAO.open();
                List<Tip> tipList = tipDAO.selectAll();
                int n = randInt(0,tipList.size());
                this.alertDialogTip = crearDialogAlerta(tipList.get(n).getMensaje());
                this.alertDialogTip.show();


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        this.mostrarFecha();

        this.mostrarTotal();


    }

    private void mostrarFecha(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha.setText(df.format(c.getTime()));

    }

    private void mostrarTotal(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM");
        EstimacionDAO estimacionDAO = new EstimacionDAO(this.context);
        try {
            estimacionDAO.open();
            List<Estimacion> estimacionList = estimacionDAO.selectAllByMes(df.format(c.getTime()));


            EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(this.context);
            estimacionDatoDAO.open();
            EstimacionPreguntaDAO estimacionPreguntaDAO = new EstimacionPreguntaDAO(this.context);
            estimacionPreguntaDAO.open();
            ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this.context);
            configuracionDAO.open();

            Double resultado = 0.0;
            for(Estimacion estimacion : estimacionList){
                List<Dato> datoList = new ArrayList<>();
                List<ItemPreguntaPreguntas> preguntaList = new ArrayList<>();

                List<EstimacionDato> estimacionDatoList = estimacionDatoDAO.selectByIdEstimacion(estimacion.getId());
                for(EstimacionDato estimacionDato : estimacionDatoList){
                    datoList.add(estimacionDato.getDato());
                }

                List<EstimacionPregunta> estimacionPreguntaList = estimacionPreguntaDAO.selectByIdEstimacion(estimacion.getId());
                for (EstimacionPregunta estimacionPregunta: estimacionPreguntaList){
                    Pregunta p = estimacionPregunta.getPregunta();
                    preguntaList.add(new ItemPreguntaPreguntas(p.getId(),p.getPregunta(),p.getCategoriaPrincipios(),Boolean.parseBoolean(estimacionPregunta.getRespuesta())));
                }

                GenerarCalculo generarCalculo = new GenerarCalculo(this.context,datoList,preguntaList, configuracionDAO.selectById("1"));
                resultado += generarCalculo.resultado();

            }
            this.resultado.setText("$ "+String.valueOf(resultado) +"0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*private  int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) ) + min;

        return randomNum;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
        this.fecha = (TextView) rootView.findViewById(R.id.principal_fecha_id);
        this.resultado = (TextView)rootView.findViewById(R.id.principa_total_id);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_principal, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_action_escanear:
                IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
                //start scanning
                scanIntegrator.initiateScan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private AlertDialog crearDialogAlerta(String mensaje){
        final String mensaje_final = mensaje;
        final AlertDialog.Builder builder= new AlertDialog.Builder(this.context);
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        TextView title = new TextView(this.context);
        title.setText("Tips de seguridad");
        title.setBackgroundColor(getResources().getColor(R.color.color_2));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(getResources().getColor(R.color.color_1));
        title.setTextSize(20);

        builder.setCustomTitle(title);
        View v = inflater.inflate(R.layout.dialog_tip, null);
        ImageButton imageButton = (ImageButton)v.findViewById(R.id.tip_btn);
        ImageView imageView = (ImageView)v.findViewById(R.id.tip_cerrar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAlerta();
            }
        });
        TextView textView = (TextView)v.findViewById(R.id.tip_id);
        textView.setText(mensaje);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Tip de seguridad de datos ");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mensaje_final);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });
        builder.setView(v);
        return builder.create();
    }

    private void dismissAlerta(){
        this.alertDialogTip.dismiss();
    }*/




}
