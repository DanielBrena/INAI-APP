package inai.brena.com.inaiapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.utils.sql.configuracion.ConfiguracionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracionF extends Fragment {

    private EditText unidad_monetaria;
    private Button eliminar_plantillas;
    private Context context;
    private Switch mostrar_tip;

    public ConfiguracionF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context = container.getContext();
        View view =  inflater.inflate(R.layout.fragment_configuracion, container, false);

        this.unidad_monetaria = (EditText)view.findViewById(R.id.conf_unidad_monetaria_id);
        this.eliminar_plantillas = (Button) view.findViewById(R.id.conf_btn_eliminar_plantillas);
        this.mostrar_tip = (Switch) view.findViewById(R.id.conf_switch_tips);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(context);
        try {
            configuracionDAO.open();
            Configuracion configuracion = configuracionDAO.selectById("1");
            Configuracion configuracion2 = configuracionDAO.selectById("2");
            this.unidad_monetaria.setText(configuracion.getValor());
            this.mostrar_tip.setChecked(Boolean.parseBoolean(configuracion2.getValor()));
            configuracionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.eliminar_plantillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarPlantillas();
                crearToast("Se eliminarón todas las plantillas");
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_configuracion, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_action_guardar_configuracion:
                this.guardarTodo();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarTodo(){
        this.guardarUnidadMonetaria();
        this.guardarTips();
        this.crearToast("Se guardó la configuración");
    }

    private void guardarUnidadMonetaria(){
        ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this.context);
        try {
            configuracionDAO.open();
            configuracionDAO.update(new Configuracion("1", "unidad_monetearia", unidad_monetaria.getText().toString()));
            configuracionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarTips(){
        ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this.context);
        try {
            configuracionDAO.open();
            configuracionDAO.update(new Configuracion("2", "tips_seguridad", String.valueOf(mostrar_tip.isChecked())));
            configuracionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminarPlantillas(){
        EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(this.context);
        EstimacionDAO estimacionDAO = new EstimacionDAO(this.context);
        try {
            estimacionDAO.open();
            estimacionDatoDAO.open();
            List<Estimacion> estimacionList = estimacionDAO.selectAllByPlantilla("1");
            for(Estimacion e : estimacionList){
                estimacionDatoDAO.deleteAllEstimacion(e.getId());
            }

            estimacionDAO.deleteAllPlantilla("1");



        } catch (SQLException e) {
            e.printStackTrace();
        }
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



}
