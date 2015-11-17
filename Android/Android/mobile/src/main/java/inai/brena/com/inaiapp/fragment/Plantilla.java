package inai.brena.com.inaiapp.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.adapter.AdapterPlantilla;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion.EstimacionDAO;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDato;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Plantilla extends Fragment {
    private Context context;
    private ListView listView;
    private AdapterPlantilla adapterPlantilla;
    private ProgressDialog dialogCarga;
    public Plantilla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = container.getContext();

        View rootView =  inflater.inflate(R.layout.fragment_plantilla, container, false);
        this.listView = (ListView) rootView.findViewById(R.id.listView_plantilla);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialogCarga = new ProgressDialog(this.context);
        dialogCarga.setTitle("Cargando...");
        dialogCarga.setMessage("Cargando las plantillas, espere un momento.");
        dialogCarga.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cargarEstimaciones();
                if (dialogCarga.isShowing()) {
                    dialogCarga.dismiss();
                }
            }





        }, 600);




    }

    private  void cargarEstimaciones(){
        final List<Estimacion> estimacionList = new ArrayList<>();


        final EstimacionDAO estimacionDAO = new EstimacionDAO(context);
        try {
            estimacionDAO.open();
            if(estimacionDAO.selectAllByPlantilla("1").size() > 0){
                for(Estimacion estimacion : estimacionDAO.selectAllByPlantilla("1")){
                    estimacionList.add(estimacion);
                }
                adapterPlantilla =new AdapterPlantilla(this.context,estimacionList);
                listView.setAdapter(this.adapterPlantilla);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String id = adapterPlantilla.getOriginal().get(i).getId();
                        EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(context);
                        try {
                            estimacionDatoDAO.open();
                            List<EstimacionDato> estimacionDatoList = estimacionDatoDAO.selectByIdEstimacion(id);
                            List<Dato> datoList = new ArrayList<Dato>();
                            for(EstimacionDato estimacionDato: estimacionDatoList){
                                datoList.add(estimacionDato.getDato());
                            }
                            cargarCalculadora(new DatoSerializable(datoList));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void cargarCalculadora(DatoSerializable datoSerializable){
        Fragment newFragment = Calculadora.newInstance(datoSerializable);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.main_content, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
