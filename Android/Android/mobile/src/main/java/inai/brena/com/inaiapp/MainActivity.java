package inai.brena.com.inaiapp;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import inai.brena.com.inaiapp.fragment.Calculadora;
import inai.brena.com.inaiapp.fragment.Estadisticas;
import inai.brena.com.inaiapp.fragment.Plantilla;
import inai.brena.com.inaiapp.fragment.Principal;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentIntegrator;
import inai.brena.com.inaiapp.google.zxing.integration.android.IntentResult;
import inai.brena.com.inaiapp.serializable.DatoSerializable;
import inai.brena.com.inaiapp.utils.sql.dato.Dato;
import inai.brena.com.inaiapp.utils.sql.dato.DatoDAO;

public class MainActivity extends AppCompatActivity {

    //Instancia de Drawer
    private DrawerLayout drawerLayout;
    private TextView textView_title;

    //Fragments
    private Calculadora calculadora;
    private Principal principal;
    private Plantilla plantilla;
    private Estadisticas estadisticas;

    private FragmentManager fragmentManager;
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

        textView_title.setText(getResources().getString(R.string.app_name).toString());
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
