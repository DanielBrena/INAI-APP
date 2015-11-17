package inai.brena.com.inaiapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import inai.brena.com.inaiapp.fragment.Calculadora;
import inai.brena.com.inaiapp.fragment.ConfiguracionF;
import inai.brena.com.inaiapp.fragment.Estadisticas;
import inai.brena.com.inaiapp.fragment.Plantilla;
import inai.brena.com.inaiapp.fragment.Principal;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentIntegrator;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentResult;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.configuracion.Configuracion;
import inai.brena.com.inaiapp.utils.sql.configuracion.ConfiguracionDAO;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;
import inai.brena.com.inaiapp.utils.sql.tip.Tip;
import inai.brena.com.inaiapp.utils.sql.tip.TipDAO;

public class MainActivity extends AppCompatActivity {

    //Instancia de Drawer
    private DrawerLayout drawerLayout;
    private TextView textView_title;

    //Fragments
    private Calculadora calculadora;
    private Principal principal;
    private Plantilla plantilla;
    private Estadisticas estadisticas;
    private ConfiguracionF configuracion;

    private FragmentManager fragmentManager;

    private AlertDialog alertDialogTip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        this.initFragment();
        this.fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            // Añadir carácteristicas
            seleccionarItem(navigationView.getMenu().getItem(0));
            prepararDrawer(navigationView);

        }
        cargarDialog();
        textView_title.setText(getResources().getString(R.string.app_name).toString());
    }


    private void cargarDialog(){
        ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(this);
        try {
            configuracionDAO.open();
            Configuracion configuracion = configuracionDAO.selectById("2");

            if( Boolean.parseBoolean(configuracion.getValor())){
                TipDAO tipDAO = new TipDAO(this);
                tipDAO.open();
                List<Tip> tipList = tipDAO.selectAll();
                int n = randInt(0,tipList.size());
                this.alertDialogTip = crearDialogAlerta(tipList.get(n).getMensaje());
                this.alertDialogTip.show();


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) ) + min;

        return randomNum;
    }




    /**
     * Metodo primario para agregar el Toolbar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        //Ocultar titulo
        getSupportActionBar().setTitle(null);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        final  MenuItem menuItem1 = menuItem;
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                seleccionarItem(menuItem1);
                            }


                        }, 200);

                        return true;
                    }
                });

    }


    /**
     *
     */
    private void initFragment(){
        this.calculadora = new Calculadora();
        this.principal = new Principal();
        this.plantilla = new Plantilla();
        this.estadisticas = new Estadisticas();
        this.configuracion = new ConfiguracionF();
    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;

        switch (itemDrawer.getItemId()) {
            case R.id.nav_principal:
                fragmentoGenerico = principal;
                break;
            case R.id.nav_calculadora:
                fragmentoGenerico = calculadora;
                break;
            case R.id.nav_plantilla:
                fragmentoGenerico = plantilla;
                break;
            case R.id.nav_estadisticas:
                fragmentoGenerico = estadisticas;
                break;
            case R.id.nav_configuracion:
                fragmentoGenerico = configuracion;
                break;

        }
        if (fragmentoGenerico != null) {
            this.fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragmentoGenerico)
                    .commit();
        }

        textView_title.setText(itemDrawer.getTitle());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        try{
            if (scanningResult != null) {
                //get content from Intent Result
                String scanContent = scanningResult.getContents();
                //get format name of data scanned
                String scanFormat = scanningResult.getFormatName();
                //output to UI
                Log.d("s", scanFormat);
                // Toast.makeText(this, scanFormat, Toast.LENGTH_SHORT).show();
                // Toast.makeText(this, scanContent, Toast.LENGTH_SHORT).show();
                Log.d("qr", scanContent);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<String>>() {}.getType();

                List<String> datoList = ( List<String>)gson.fromJson(scanContent, collectionType);
                final List<Dato> list = new ArrayList<>();
                DatoDAO datoDAO = new DatoDAO(this);
                datoDAO.open();
                for(String id : datoList){
                    list.add(datoDAO.selectById(id));
                }
                datoDAO.close();

                Log.d("qr", String.valueOf(list.size()));
                Log.d("qr", String.valueOf(list.get(0).getNombre()));
                //Toast.makeText(this, String.valueOf(datoList.size()), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cargarCalculadora(new DatoSerializable(list));
                    }


                }, 600);


            } else {
                //invalid scan data or scan canceled
                Toast toast = Toast.makeText(this,
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(this,
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void cargarCalculadora(DatoSerializable datoSerializable){
        Fragment newFragment = Calculadora.newInstance(datoSerializable);
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();

        transaction.replace(R.id.main_content, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    private AlertDialog crearDialogAlerta(String mensaje){
        final String mensaje_final = mensaje;
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        TextView title = new TextView(this);
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
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
