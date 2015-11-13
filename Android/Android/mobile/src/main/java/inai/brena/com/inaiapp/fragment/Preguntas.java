package inai.brena.com.inaiapp.fragment;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.adapter.AdapterPreguntas;
import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.pregunta.Pregunta;
import inai.brena.com.inaiapp.utils.sql.pregunta.PreguntaDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Preguntas extends Fragment {
    private Context context;
    private ListView listView;
    private AdapterPreguntas adapterPreguntas;
    private DatoSerializable datoSerializable;

    String mensaje = "";

    public static Preguntas newInstance(DatoSerializable arguments){
        Preguntas f = new Preguntas();
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos",arguments);
        if(arguments != null){
            f.setArguments(bundle);
        }
        return f;
    }

    public Preguntas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = container.getContext();
        View rootView = inflater.inflate(R.layout.fragment_preguntas, container, false);

        this.listView = (ListView) rootView.findViewById(R.id.listView_preguntas);

        datoSerializable = (DatoSerializable)getArguments().getSerializable("datos");

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.hideKeyboard();
        this.loadDatos();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void loadDatos(){
        final List<ItemPreguntaPreguntas> itemPreguntaPreguntaList = new ArrayList<>();


        PreguntaDAO preguntaDAO = new PreguntaDAO(context);
        try {
            preguntaDAO.open();
            if(preguntaDAO.selectAll().size() > 0){

                for(Pregunta p : preguntaDAO.selectAll()){
                    ItemPreguntaPreguntas itemPreguntaPregunta = new ItemPreguntaPreguntas(p.getId(),p.getPregunta(),p.getCategoriaPrincipios());
                    itemPreguntaPreguntaList.add(itemPreguntaPregunta);
                }
                adapterPreguntas =new AdapterPreguntas(this.context,itemPreguntaPreguntaList);
                listView.setAdapter(this.adapterPreguntas);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_preguntas_preguntas, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_action_siguiente_2:
                    this.loadFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(){
        Fragment newFragment = Resultado.newInstance(this.datoSerializable,this.adapterPreguntas.getPreguntaSerializable());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.main_content, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
