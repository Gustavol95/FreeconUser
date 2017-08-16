package com.iesoluciones.freeconuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.github.phajduk.rxvalidator.RxValidator;
import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.models.RegistroBody;
import com.iesoluciones.freeconuser.models.Usuario;
import com.iesoluciones.freeconuser.network.helpers.CustomResourceObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;


/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroActivity extends AppCompatActivity {

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
    RegistroBody registro;
    boolean facebookUser;
    @BindView(R.id.buttonContinuar)
    AppCompatButton buttonContinuar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
         registro=new RegistroBody();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        facebookUser = getIntent().getBooleanExtra("fb", false);
        if (facebookUser) {
             Usuario temp=  App.getInstance().getDaoSession().getUsuarioDao().loadAll().get(0);
              editNombre.setText(temp.getNombre());
              editNombre.setEnabled(false);
              editApellido.setText(temp.getApellido());
              editApellido.setEnabled(false);
              editCorreoElectronico.setText(temp.getEmail());
              editCorreoElectronico.setEnabled(false);
              editContrasena.setEnabled(false);
              editContrasenaConfirmacion.setEnabled(false);
        }
        setUpValidators();
        Log.i(TAG, " facebook " + facebookUser);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (super.onOptionsItemSelected(item));
    }

    @OnClick(R.id.buttonContinuar)
    public void registrar() {
        RegistroBody registroBody = new RegistroBody();
        registroBody.setNombre(editNombre.getText().toString());
        registroBody.setApellido(editApellido.getText().toString());
        registroBody.setContrasena(editContrasena.getText().toString());
        registroBody.setEmail(editCorreoElectronico.getText().toString());
        registroBody.setCelular(editTelefono.getText().toString());
        ObservableHelper.registrar(registroBody)
                .subscribe(new CustomResourceObserver<LoginFbResponse>(this) {
                    @Override
                    public void onNext(LoginFbResponse value) {
                        //  new AlertDialog.Builder(context)
                        final AppCompatEditText input = new AppCompatEditText(RegistroActivity.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        input.setLayoutParams(lp);

                        new AlertDialog.Builder(RegistroActivity.this)
                                .setTitle("Registro exitoso")
                                .setMessage("Se envió un código de activación al correo electrónico: " + value.getUsuario().getEmail())
                                .setCancelable(false)
                                .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), (dialog, id) -> {
                                    //Enviar a drawer activity
                                    new AlertDialog.Builder(RegistroActivity.this)
                                            .setMessage("Ingresa el código que se envió a tu correo.")
                                            .setTitle("Ingresar código de activación")
                                            .setCancelable(false)
                                            .setView(input)
                                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog2, id2) -> {
                                                //MANDAR A COMPA SERVICIO DE LUISITO
                                                ObservableHelper.activarCuenta(input.getText().toString())
                                                        .subscribe(new CustomResourceObserver<LoginFbResponse>(RegistroActivity.this) {
                                                            @Override
                                                            public void onNext(LoginFbResponse value) {
                                                                new AlertDialog.Builder(RegistroActivity.this)
                                                                        .setMessage("Tu cuenta ha sido activada.")
                                                                        .setTitle("Registro exitoso")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog, id) -> {
                                                                            //Enviar a drawer activity
                                                                            //getContext().startActivity(new Intent(getContext(),DrawerActivity.class));
                                                                            finish();
                                                                        })
                                                                        .setNegativeButton(getString(R.string.button_cancel), (dialog1, which) -> {
                                                                            finish();
                                                                        })
                                                                        .show();
                                                            }
                                                        });
                                            })
                                            .setNegativeButton(getResources().getString(R.string.button_cancel), (dialog3, which) -> {
                                                dialog.dismiss();
                                            }).show();


                                }).show();

                    }

                });
    }

    public void setUpValidators(){

        Observable<Boolean> observable =
                RxValidator.createFor(editTelefono)
                        .nonEmpty("No puede estar vacío")
                        .length( 10,"El telefono debe de ser de 10 dígitos")
                        .onFocusChanged()
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                .map(result -> {
                   // result.getItem().setError(result.isProper() ? null : result.getMessage());
                    textInputTelefono.setErrorEnabled(result.isProper() ? false : true);
                    textInputTelefono.setError(result.getMessage());

                 return result.isProper();
                });


        Observable<Boolean> observable1 =
        RxValidator.createFor(editContrasena)
                .nonEmpty()
                .sameAs(editContrasenaConfirmacion,"Las contraseñas deben coincidir")
                .maxLength(15,"Máximo 15 caracteres")
                .minLength(8, "Mínimo 8 caracteres")
                .toObservable()
                .map(result -> {
                    // result.getItem().setError(result.isProper() ? null : result.getMessage());
                    textInputContrasena.setErrorEnabled(result.isProper() ? false : true);
                    textInputContrasena.setError(result.getMessage());

                    return result.isProper();
                });

        Observable.combineLatest(observable1, observable, new Func2<Boolean, Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean esValido, Boolean esValido2) {
                Log.i(TAG,"AMONOSSSS RIKYYY");
                return null;
            }
        });





    }
}