package com.iesoluciones.freeconuser.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.iesoluciones.freeconuser.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroActivity extends AppCompatActivity  {

    static String TAG = RegistroActivity.class.getSimpleName();
    @BindView(R.id.textInputNombre)
    TextInputLayout textInputNombre;
    @BindView(R.id.editNombre)
    TextInputEditText editNombre;
    @BindView(R.id.textInputApellido)
    TextInputLayout textInputApellido;
    @BindView(R.id.editApellido)
    TextInputEditText editApellido;
    @BindView(R.id.textInputCorreoElectronico)
    TextInputLayout textInputCorreoElectronico;
    @BindView(R.id.editCorreoElectronico)
    TextInputEditText editCorreoElectronico;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputContrasena;
    @BindView(R.id.editContrasena)
    TextInputEditText editContrasena;
    @BindView(R.id.textInputContrasenaConfirmacion)
    TextInputLayout textInputContrasenaConfirmacion;
    @BindView(R.id.editContrasenaConfirmacion)
    TextInputEditText editContrasenaConfirmacion;
    @BindView(R.id.textInputTelefono)
    TextInputLayout textInputTelefono;
    @BindView(R.id.editTelefono)
    TextInputEditText editTelefono;
    int index;
    @BindView(R.id.toolbarRegistro)
    Toolbar toolbar;
    //RegistroBody registro;
    boolean facebookUser;
    @BindView(R.id.buttonContinuar)
    AppCompatButton buttonContinuar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
       // registro=new RegistroBody();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        facebookUser=getIntent().getBooleanExtra("fb",false);
        if(facebookUser){
          //  Usuario temp=  App.getInstance().getDaoSession().getUsuarioDao().loadAll().get(0);
          //  editNombre.setText(temp.getNombre());
          //  editNombre.setEnabled(false);
          //  editApellido.setText(temp.getApellido());
          //  editApellido.setEnabled(false);
          //  editCorreoElectronico.setText(temp.getEmail());
          //  editCorreoElectronico.setEnabled(false);
          //  editContrasena.setEnabled(false);
          //  editContrasenaConfirmacion.setEnabled(false);
        }

        Log.i(TAG," facebook "+facebookUser);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return(super.onOptionsItemSelected(item));
    }


}
