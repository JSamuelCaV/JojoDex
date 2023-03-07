package com.example.jojodex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_abierto, R.string.navigation_drawer_cerrado);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);

        menuItem.setChecked(true);

        drawerLayout.addDrawerListener(this);
    }
    //Se pasan los valores tanto de la toolbar como de los valores del Menu creados para hacer que funcione el NavigationDrawer
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
// Sirve para desplegar o cerrar el NavigationDrawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        int title;
        switch (item.getItemId()) {

            case R.id.personajes:
                title = R.string.personajes;
                fragment=new FragmentPersonajesJojo();
                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle(title);
                callFragmnent(fragment);
                break;
            case R.id.stands:
                title = R.string.stands;
                fragment=new FragmentStandsJojo();
                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle(title);
                callFragmnent(fragment);
                break;
            default:
                throw new IllegalArgumentException("menu option not implement");
        }
        return true;
    }
//Se seleccionan los datos que hacen falta para que se salga por pantalla los datos del fragment seleccionado

    public void callFragmnent(Fragment fragment){

        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.linear_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    //Este metodo llama al Fragment correspondiente seleccionado en el anterior paso
    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        Toast.makeText(this, getString(R.string.navigation_drawer_abierto),
                Toast.LENGTH_SHORT).show();
    }
// Crea un toast cuando se abre el NavigationDrawer
    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        Toast.makeText(this, getString(R.string.navigation_drawer_cerrado),
                Toast.LENGTH_SHORT).show();
    }
// Crea un toast cuando se cierra el NavigationDrawer

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}