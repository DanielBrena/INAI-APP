package inai.brena.com.inaiapp.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.util.ArrayList;

import inai.brena.com.inaiapp.R;
import inai.brena.com.inaiapp.utils.sql.GenerarCalculo;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Estadisticas extends Fragment {
    private Context context;
    private PieChart mChart;

    public Estadisticas() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = container.getContext();
        View rooView = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        mChart = (PieChart) rooView.findViewById(R.id.pieChart);
        return rooView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.generarPieChart();
    }

    private void generarPieChart(){
        GenerarCalculo generarCalculo = new GenerarCalculo(this.context);
        DatoDAO datoDAO = new DatoDAO(this.context);
        try {
            datoDAO.open();
            generarCalculo.setDatos(datoDAO.selectAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String[] nombres = { "Datos Estandares" ,"Datos Sensibles", "Datos Especiales"};
        float[] valores = {generarCalculo.calculo_datos_estandar(),generarCalculo.calculo_datos_sensible(),generarCalculo.calculo_datos_especial()};

        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        ArrayList<Entry> valoresY = new ArrayList<Entry>();
        ArrayList<String> valoresX = new ArrayList<String>();

        for(int i = 0; i < nombres.length; i++){
            valoresX.add(nombres[i]);
        }

        for(int i = 0; i < valores.length; i++){
            valoresY.add(new Entry(valores[i],i));
        }
        PieDataSet pieDataSet = new PieDataSet(valoresY,"");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(Color.parseColor("#4CAF50"));
        colors.add(Color.parseColor("#FFC107"));
        colors.add(Color.parseColor("#F44336"));

        pieDataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(valoresX, pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(getResources().getColor(R.color.color_1));

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();

    }
}
