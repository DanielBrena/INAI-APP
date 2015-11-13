package inai.brena.com.inaiapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.fragment.Calculadora;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.estimacion.Estimacion;
import inai.brena.com.inaiapp.utils.sql.estimacion_dato.EstimacionDatoDAO;

/**
 * Created by DanielBrena on 01/11/15.
 */
public class AdapterPlantilla extends ArrayAdapter<Estimacion>  {

    private LayoutInflater layoutInflater;
    private List<Estimacion> original;

    public AdapterPlantilla(Context context, List<Estimacion> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        this.original = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;


        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.item_plantilla, parent, false);
            holder.setTextViewTitulo((TextView) convertView
                    .findViewById(R.id.plantilla_nombre_id));
            holder.setTextViewContador((TextView) convertView
                    .findViewById(R.id.plantilla_contador_id));

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        final Estimacion row = getItem(position);
        holder.getTextViewTitulo().setText(row.getNombre());
        EstimacionDatoDAO estimacionDatoDAO = new EstimacionDatoDAO(getContext());
        try {
            estimacionDatoDAO.open();
            holder.getTextViewContador().setText(String.valueOf(estimacionDatoDAO.selectByIdEstimacion(row.getId()).size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }










        return convertView;
    }

    public List<Estimacion> getOriginal() {
        return original;
    }

    public void setOriginal(List<Estimacion> original) {
        this.original = original;
    }

    static class Holder {
        TextView textViewTitulo;
        TextView textViewContador;

        public TextView getTextViewTitulo() {
            return textViewTitulo;
        }

        public void setTextViewTitulo(TextView textViewTitulo) {
            this.textViewTitulo = textViewTitulo;
        }

        public TextView getTextViewContador() {
            return textViewContador;
        }

        public void setTextViewContador(TextView textViewContador) {
            this.textViewContador = textViewContador;
        }
    }
}
