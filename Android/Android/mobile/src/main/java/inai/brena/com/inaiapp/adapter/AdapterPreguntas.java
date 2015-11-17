package inai.brena.com.inaiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.extendsSQL.ItemPreguntaPreguntas;
import inai.brena.com.inaiapp.serializable.PreguntaSerializable;

/**
 * Created by DanielBrena on 26/10/15.
 */
public class AdapterPreguntas extends ArrayAdapter<ItemPreguntaPreguntas> implements  View.OnClickListener  {

    private LayoutInflater layoutInflater;
    List<ItemPreguntaPreguntas> aux;
    private PreguntaSerializable preguntaSerializable;

    public AdapterPreguntas(Context context, List<ItemPreguntaPreguntas> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        this.aux = objects;
        this.preguntaSerializable = new PreguntaSerializable();


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.item_preguntas_pregunta2, parent, false);



            holder.setTextView((TextView) convertView
                    .findViewById(R.id.pregunta));

            holder.setaSwitch((SwitchCompat) convertView
                    .findViewById(R.id.respuesta));



            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final ItemPreguntaPreguntas row = getItem(position);



        holder.getTextView().setText(row.getPregunta());
        holder.getaSwitch().setTag(position);
        holder.getaSwitch().setChecked(row.isChecked());

        if(row.isChecked()){
            holder.getaSwitch().setText("Si");
            holder.getaSwitch().setTextColor(convertView.getResources().getColor(R.color.primaryColor));
        }else{
            holder.getaSwitch().setText("No");
            holder.getaSwitch().setTextColor(convertView.getResources().getColor(R.color.radio_button_color_checked));
        }


        holder.getaSwitch().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchCompat aSwitch2 = (SwitchCompat) view.findViewById(R.id.respuesta);
                int position = (Integer) view.findViewById(R.id.respuesta).getTag();
                aSwitch2.setChecked(aSwitch2.isChecked());
                getItem(position).setIsChecked(aSwitch2.isChecked());

                if(aSwitch2.isChecked()){
                    // preguntaSerializable.getItemPreguntaPreguntasList().add(row);
                    //Toast.makeText(getContext(), String.valueOf(preguntaSerializable.getItemPreguntaPreguntasList().size()), Toast.LENGTH_SHORT).show();
                    aSwitch2.setText("Si");
                    aSwitch2.setTextColor(view.getResources().getColor(R.color.primaryColor));
                }else{
                    //preguntaSerializable.getItemPreguntaPreguntasList().remove(row);
                    //Toast.makeText(getContext(), String.valueOf(preguntaSerializable.getItemPreguntaPreguntasList().size()), Toast.LENGTH_SHORT).show();

                    aSwitch2.setText("No");
                    aSwitch2.setTextColor(view.getResources().getColor(R.color.radio_button_color_checked));
                }

                String msg = getContext().getString(R.string.check_toast,
                        position, String.valueOf(aSwitch2.isChecked()));
            }
        });

        return convertView;
    }




    @Override
    public void onClick(View v) {

        SwitchCompat aSwitch2 = (SwitchCompat) v.findViewById(R.id.respuesta);
        int position = (Integer) v.getTag();
        getItem(position).setIsChecked(aSwitch2.isChecked());

        if(aSwitch2.isChecked()){
            aSwitch2.setText("Si");
            aSwitch2.setTextColor(v.getResources().getColor(R.color.primaryColor));
        }else{
            aSwitch2.setText("No");
            aSwitch2.setTextColor(v.getResources().getColor(R.color.radio_button_color_checked));
        }

        String msg = this.getContext().getString(R.string.check_toast,
                position, String.valueOf(aSwitch2.isChecked()));
    }


    public PreguntaSerializable getPreguntaSerializable() {

        for(ItemPreguntaPreguntas pregunta : this.aux){
            this.preguntaSerializable.getItemPreguntaPreguntasList().add(pregunta);
        }

        return preguntaSerializable;
    }

    public void setPreguntaSerializable(PreguntaSerializable preguntaSerializable) {
        this.preguntaSerializable = preguntaSerializable;
    }

    static class Holder {
        TextView textView;
        SwitchCompat aSwitch;

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public SwitchCompat getaSwitch() {
            return aSwitch;
        }

        public void setaSwitch(SwitchCompat aSwitch) {
            this.aSwitch = aSwitch;
        }
    }

}
