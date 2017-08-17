package com.iesoluciones.freeconuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.fragments.CategoriasFragment;
import com.iesoluciones.freeconuser.fragments.HistorialFragment;
import com.iesoluciones.freeconuser.network.helpers.CustomResourceObserver;

import java.io.IOException;

import okhttp3.ResponseBody;


public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static String TAG = DrawerActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ObservableHelper.solicitar(3+"",1+"",3+"","Amonooos")
                .subscribe(new CustomResourceObserver<ResponseBody>(this) {
                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            Log.i(TAG,value.source().readUtf8().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //super.onError(e);
                        Log.i(TAG,"TRONIQIO "+e.getMessage());
                    }
                });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);
        Fragment newFragment = CategoriasFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameDrawer, newFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cerrarSesion) {
            App.getInstance().getDaoSession().getUsuarioDao().deleteAll();
            LoginManager.getInstance().logOut();
            ObservableHelper.logout(FirebaseInstanceId.getInstance().getToken())
                    .subscribe(new CustomResourceObserver<ResponseBody>(DrawerActivity.this) {
                        @Override
                        public void onNext(ResponseBody value) {
                            Log.i(TAG,"AIII "+value.toString());
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.i(TAG,"FALLO "+e.toString());
                        }
                    });
            startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment newFragment;
        FragmentTransaction ft;
        switch (id) {
            case R.id.historial:
                newFragment = HistorialFragment.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameDrawer, newFragment).commit();
                break;
            case R.id.solicitudes:
                newFragment = CategoriasFragment.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameDrawer, newFragment).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
