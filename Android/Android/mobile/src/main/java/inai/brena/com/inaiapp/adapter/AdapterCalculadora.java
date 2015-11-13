package inai.brena.com.inaiapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.extendsSQL.ItemDatoCalculadora;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;

/**
 * Created by DanielBrena on 25/10/15.
 */
public class AdapterCalculadora extends ArrayAdapter<ItemDatoCalculadora> implements
        View.OnClickListener {

    private LayoutInflater layoutInflater;
    private List<ItemDatoCalculadora> original;
    private List<ItemDatoCalculadora> filter;

    private DatoSerializable datoSerializable;


    public AdapterCalculadora(Context context, List<ItemDatoCalculadora> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        this.original = objects;
        this.filter = new ArrayList<>();
        this.filter.addAll(objects);
        this.datoSerializable = new DatoSerializable();
    }
    public AdapterCalculadora(Context context, List<ItemDatoCalculadora> objects, DatoSerializable datoSerializable){
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        this.original = objects;
        this.filter = new ArrayList<>();
        this.filter.addAll(objects);
        this.datoSerializable = new DatoSerializable();
        this.combinar(datoSerializable);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;


        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.item_calculadora_dato, parent, false);


            holder.setImageView((ImageView) convertView
                    .findViewById(R.id.image_view));

            holder.setTextViewTitle((TextView) convertView
                    .findViewById(R.id.textViewTitle));

            holder.setTextViewSubtitle((TextView) convertView
                    .findViewById(R.id.textViewSubtitle));

            holder.setCheckBox((CheckBox) convertView
                    .findViewById(R.id.checkBox));

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        final ItemDatoCalculadora row = getItem(position);


        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(2)
                .toUpperCase()
                .textColor(Color.parseColor(row.getCategoriaDatos().getColor()))
                .useFont(Typeface.DEFAULT)
                .fontSize(30)
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(row.getNombre().substring(0,1), Color.WHITE);

        holder.getTextViewTitle().setText(row.getNombre());
        holder.getImageView().setImageDrawable(drawable);
        holder.getTextViewSubtitle().setText(row.getDescripcion());


        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setFocusable(false);
        holder.getCheckBox().setFocusableInTouchMode(false);
        holder.getCheckBox().setClickable(false);
        holder.getCheckBox().setChecked(row.isChecked());

        //holder.getCheckBox().setOnClickListener(this);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                int position = (Integer) view.findViewById(R.id.checkBox).getTag();

                checkBox.setChecked(!checkBox.isChecked());
                getItem(position).setIsChecked(checkBox.isChecked());

                if(checkBox.isChecked()){
                    datoSerializable.getDatoList().add(row);
                   // Toast.makeText(getContext(),String.valueOf(datoSerializable.getDatoList().size()), Toast.LENGTH_SHORT).show();
                }else{
                    datoSerializable.getDatoList().remove(row);
                   // Toast.makeText(getContext(), String.valueOf(datoSerializable.getDatoList().size()), Toast.LENGTH_SHORT).show();
                }
                    String msg =getContext().getString(R.string.check_toast,
                        position, String.valueOf(checkBox.isChecked()));
             //   Toast.makeText(getContext(), row.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    private void combinar(DatoSerializable datoSerializable){
        for(int i = 0; i < datoSerializable.getDatoList().size(); i++){
            for(int j = 0; j < this.original.size(); j++){
                if(datoSerializable.getDatoList().get(i).getId().equals(this.original.get(j).getId())){
                    this.original.get(j).setIsChecked(true);
                    this.getDatoSerializable().getDatoList().add(this.original.get(j));
                }
            }
        }

    }



    @Override
    public void onClick(View v) {

        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        int position = (Integer) v.getTag();
        getItem(position).setIsChecked(checkBox.isChecked());

        String msg = this.getContext().getString(R.string.check_toast,
                position, String.valueOf(checkBox.isChecked()));
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        original.clear();
        if (charText.length() == 0) {
            original.addAll(filter);
        }
        else
        {
            for (ItemDatoCalculadora i : filter)
            {
                if(i.getNombre().toLowerCase(Locale.getDefault()).contains(charText)  || i.getCategoriaDatos().getNombre().toLowerCase(Locale.getDefault()).contains(charText) ){
                    original.add(i);
                }

            }

        }
        notifyDataSetChanged();
    }

    public DatoSerializable getDatoSerializable() {
        /*for(ItemDatoCalculadora dato :this.original){
            if(dato.isChecked()&& !this.datoSerializable.getDatoList().contains(dato)) {
                this.datoSerializable.getDatoList().add(dato);
            }else{
                this.datoSerializable.getDatoList().remove(dato);
            }
        }*/
        return datoSerializable;
    }

    public void setDatoSerializable(DatoSerializable datoSerializable) {
        this.datoSerializable = datoSerializable;
    }

    static class Holder {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;
        ImageView imageView;


        public TextView getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle() {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle) {
            this.textViewSubtitle = textViewSubtitle;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }

}
