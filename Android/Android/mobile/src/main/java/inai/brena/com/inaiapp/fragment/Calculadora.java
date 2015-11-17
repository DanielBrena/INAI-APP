package inai.brena.com.inaiapp.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.adapter.AdapterCalculadora;
import inai.brena.com.inaiapp.extendsSQL.ItemDatoCalculadora;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.categoria_datos.CategoriaDatos;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDato;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calculadora extends Fragment {
    private Context context;
    private ListView listView;
    private EditText filterText;
    private AdapterCalculadora adapterCalculadora;
    private Dialog dialogAlerta;
    private Dialog dialogGuardarPlantilla;
    private Dialog dialogQr;
    private DatoSerializable datoSerializable;
    private ProgressDialog dialogCarga;

    String mensaje = "";

    public Calculadora() {
        // Required empty public constructor
    }

    public static Calculadora newInstance(DatoSerializable arguments) {
        Calculadora f = new Calculadora();
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", arguments);
        if (arguments != null) {
            f.setArguments(bundle);
        }
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context = container.getContext();

        View rootView =  inflater.inflate(R.layout.fragment_calculadora, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_calculadora);
        this.filterText = (EditText) rootView.findViewById(R.id.search);


        if(getArguments() != null){
            this.datoSerializable = (DatoSerializable)getArguments().getSerializable("datos");
        }




        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = filterText.getText().toString().toLowerCase(Locale.getDefault());
                adapterCalculadora.filter(text);

            }
        });
        dialogCarga = new ProgressDialog(this.context);
        dialogCarga.setTitle("Cargando...");
        dialogCarga.setMessage("Cargando las datos, espere un momento.");
        dialogCarga.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               loadDatos();
                if (dialogCarga.isShowing()) {
                    dialogCarga.dismiss();
                }
            }
        }, 600);


    }

    private  void loadDatos(){
        final List<ItemDatoCalculadora> itemDatoCalculadoraList = new ArrayList<>();


        DatoDAO datoDAO = new DatoDAO(context);
        try {
            datoDAO.open();
            if(datoDAO.selectAll().size() > 0){

                for(Dato d : datoDAO.selectAll()){
                    ItemDatoCalculadora itemDatoCalculadora = new ItemDatoCalculadora(d.getId(),d.getNombre(),d.getDescripcion(),d.getCategoriaDatos(),false);
                    itemDatoCalculadoraList.add(itemDatoCalculadora);
                }

                if(getArguments() != null){

                    adapterCalculadora =new AdapterCalculadora(this.context,itemDatoCalculadoraList,this.datoSerializable);
                }else{
                    adapterCalculadora =new AdapterCalculadora(this.context,itemDatoCalculadoraList);
                }

                listView.setAdapter(this.adapterCalculadora);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(context,
                                itemDatoCalculadoraList.get(i).getNombre(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private AlertDialog crearDialogAlerta(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this.context);
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        TextView title = new TextView(this.context);
        title.setText("Mensaje");
        title.setBackgroundColor(getResources().getColor( R.color.radio_button_color_checked));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        View v = inflater.inflate(R.layout.dialog_calculadora, null);
        Button button_entiendo = (Button)v.findViewById(R.id.dialog_calculadora_btn_id);


        builder.setView(v);
        button_entiendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissAlerta();
            }
        });

        return builder.create();
    }

    private AlertDialog crearDialogQr(String codigo){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this.context);
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        TextView title = new TextView(this.context);
        title.setText("Código QR");
        title.setBackgroundColor(getResources().getColor( R.color.radio_button_color_checked));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        View v = inflater.inflate(R.layout.dialog_qr, null);
        ImageView imageViewQr = (ImageView)v.findViewById(R.id.qr);

        ImageButton button = (ImageButton)v.findViewById(R.id.qr_share);

        try {
            Bitmap bitmap = encodeAsBitmap(codigo);
            imageViewQr.setImageBitmap(bitmap);

            File mFile = savebitmap(bitmap);

            final Uri u = Uri.fromFile(mFile);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("image/*");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Código QR");
                    // + "\n\r" + "\n\r" +
                    // feed.get(Selectedposition).DETAIL_OBJECT.IMG_URL
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Tú texto aquí");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, u);
                    startActivity(Intent.createChooser(emailIntent, "Enviando correo..."));
                }
            });


        } catch (WriterException e) {
            e.printStackTrace();
        }
        builder.setView(v);


        return builder.create();
    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, "qr" + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "qr" + ".png");
        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    private AlertDialog crearDialogGuardar(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this.context);
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        TextView title = new TextView(this.context);
        title.setText("Crear Plantilla");
        title.setBackgroundColor(getResources().getColor( R.color.radio_button_color_checked));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        builder.setCustomTitle(title);
        View v = inflater.inflate(R.layout.dialog_plantilla, null);

        Button buttonCancelar = (Button)v.findViewById(R.id.dialog_dato_btn1_id);
        Button buttonGuardar = (Button)v.findViewById(R.id.dialog_dato_btn2_id);
        final EditText editText = (EditText)v.findViewById(R.id.dialog_dato_texto_id);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        editText.setText(df.format(c.getTime()) + " Plantilla");

        builder.setView(v);
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss();
                guardarPlantilla(editText.getText().toString(), adapterCalculadora.getDatoSerializable().getDatoList());
            }
        });
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissGuardar();
            }
        });

        return builder.create();
    }

    private void dismissAlerta(){
        this.dialogAlerta.dismiss();
    }
    private void dismissGuardar(){

        this.dialogGuardarPlantilla.dismiss();
    }

    private void guardarPlantilla(String nombre,List<Dato> datoList){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        EstimacionDAO estimacionDAO = new EstimacionDAO(this.context);
        EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(this.context);

        List<EstimacionDato> estimacionDatoList = new ArrayList<>();

        try {
            estimacionDAO.open();
            estimacionDatoDAO.open();
            Estimacion estimacion = new Estimacion(nombre,df.format(c.getTime()),"1");
            int id = estimacionDAO.insert(estimacion);
            estimacion.setId(String.valueOf(id));
            estimacionDAO.close();

            for (Dato dato: datoList){
                estimacionDatoList.add(new EstimacionDato(estimacion,dato));
            }
            estimacionDatoDAO.insertAll(estimacionDatoList);

            this.crearToast("Se ha creado tu plantilla.");
            this.dismissGuardar();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 400, 400, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

    private void generar_qr(){
        List<String> datos_string = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        for(Dato dato : this.adapterCalculadora.getDatoSerializable().getDatoList()){
            datos_string.add(dato.getId());
        }
        String gson_list =gson.toJson(datos_string);

        this.dialogQr = this.crearDialogQr(gson_list);
        this.dialogQr.show();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calculadora_datos, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int resultado = this.adapterCalculadora.getDatoSerializable().getDatoList().size();

        switch (item.getItemId()) {
            case R.id.menu_action_generar_qr:
                if(resultado >= 1){
                    generar_qr();
                }else{
                    this.dialogAlerta = this.crearDialogAlerta();
                    this.dialogAlerta.show();
                }

                break;
            case R.id.menu_action_siguiente_1:
                if(resultado >= 1){
                    loadFragment();
                }else{
                    this.dialogAlerta = this.crearDialogAlerta();
                    this.dialogAlerta.show();
                }

                break;
            case R.id.menu_action_guardar_plantilla:
                if(resultado >= 1){
                    this.dialogGuardarPlantilla = this.crearDialogGuardar();
                    this.dialogGuardarPlantilla.show();
                }else{
                    this.dialogAlerta = this.crearDialogAlerta();
                    this.dialogAlerta.show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void crearToast(String m){
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View layout = inflater.inflate(R.layout.toast, (ViewGroup) getActivity().findViewById(R.id.toast_layout));

        TextView text = (TextView) layout.findViewById(R.id.toast_text_id);
        text.setText(m);

        Toast toast = new Toast(this.context);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void loadFragment(){
        Fragment newFragment = Preguntas.newInstance(this.adapterCalculadora.getDatoSerializable());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
