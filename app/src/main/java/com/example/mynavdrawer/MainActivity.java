//Asociamos el paquete con la clase
package com.example.mynavdrawer;

//Importamos las librerias necesarias

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

//Declaramos la clase y heredamos
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Para el diseño, variables para la interfaz
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Constantes para mostrar los fragment
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_PARAMS = 2;

    //Fragmentos para mostrar
    private Fragment fragmentNews;
    private Fragment fragmentProfile;
    private Fragment fragmentParams;

    //Metodo que se ejecuta en el ciclo de vida de las actividades
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Asociamos el layout con la activity
        setContentView(R.layout.activity_main);


        //Configuramos la toolbar
        this.configureToolBar();

        //Configuramps el drawerlayout
        this.configureDrawerLayout();

        //Configuramos el navigationview
        this.configureNavigationView();

        //Metodo para cargar un fragment por defecto se debe colocar despues de haber configurado el navigation
        this.showFirstFragment();

    }

    //Metodo para cerrar el navigation drawer si pulsamos el boton atras
    @Override
    public void onBackPressed() {

        //Comprueba si el drawer esta abierto
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {

            //Cierra el drawer con una animacion
            this.drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            //Detecta si el usuario ha pulsado el boton atras
            super.onBackPressed();

        }

    }

    //Metodo que al pulsar ciertos elementos del navegador hara una cosa u otra
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Metemos en un ID el item pulsado
        int id = item.getItemId();

        //En funcion del item pulsamos hacemos una cosa u otra
        switch (id) {

            case R.id.activity_main_drawer_news:
                this.showFragment(FRAGMENT_NEWS);
                break;

            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_PROFILE);
                break;

            case R.id.activity_main_drawer_settings:
                this.showFragment(FRAGMENT_PARAMS);
                break;

            default:
                break;

        }

        //Cerramos el drawer
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    //Metodo para enseñar el fragmento segun su identificador
    private void showFragment(int fragmentIdentifier) {

        //Creamos un switch para determinar que fragmento enseñar
        switch (fragmentIdentifier) {

            case FRAGMENT_NEWS:

                this.showNewsFragment();
                break;

            case FRAGMENT_PROFILE:

                this.showProfileFragment();
                break;

            case FRAGMENT_PARAMS:

                this.showParamsFragment();
                break;

            default:
                break;
        }

    }

    //Metodo para crear el fragment de news y mostrarlo
    private void showNewsFragment() {

        if (this.fragmentNews == null) {

            this.fragmentNews = NewsFragment.newInstance();

        }

        this.startTransactionFragment(this.fragmentNews);

    }

    //Metodo para crear el fragment de params y mostrarlo
    private void showParamsFragment() {


        if (this.fragmentParams == null) {

            this.fragmentParams = ParamsFragment.newInstance();

        }

        this.startTransactionFragment(this.fragmentParams);

    }

    //Metodo para crear el fragment de profile y mostrarlo
    private void showProfileFragment() {

        if (this.fragmentProfile == null) {

            this.fragmentProfile = ProfileFragment.newInstance();

        }

        this.startTransactionFragment(this.fragmentProfile);

    }

    //Metodo que cambiara el anterior fragment por uno nuevo
    private void startTransactionFragment(Fragment fragment) {

        if(!fragment.isVisible()){

            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();

        }

    }

    //Metodo para cargar un fragment por defecto
    private void showFirstFragment(){

        //Serializamos el fragment
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        //Si no existe, lo insertamos
        if (visibleFragment == null){

            //Mostramos el fragmento
            this.showFragment(FRAGMENT_NEWS);

            //Indicamos que elemento esta seleccionado
            this.navigationView.getMenu().getItem(0).setChecked(true);

        }

    }

    //Metodo para configurar la toolbar
    private void configureToolBar(){

        //Serializamos la toolbar
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);

        //Asociamos la toolbar a la activity
        setSupportActionBar(toolbar);

    }

    //Metodo para configurar el drawerlayout
    private void configureDrawerLayout(){

        //Serializamos el drawerlayout
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);

        //Sirve para asociar el drawerlayout y el marco de actionbar(boton hamburguesa)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //Añadimos un listener que detectara la interaccion con el drawer
        drawerLayout.addDrawerListener(toggle);

        //Sincroniza con el drawerlayout
        toggle.syncState();

    }

    //Metodo para configurar el navigationview
    private void configureNavigationView(){

        //Serializamos el navigationview
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);

        //Configuramos el listener para cuando se seleccione el elemento
        navigationView.setNavigationItemSelectedListener(this);

    }

}