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
import rx.functions.Func6;


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
                                                                            startActivity(new Intent(RegistroActivity.this,DrawerActivity.class));
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

        Observable<Boolean> telefonoValidator =
                RxValidator.createFor(editTelefono)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .length( 10,getResources().getString(R.string.phone_length))
                        .onFocusChanged()
                        .onValueChanged()
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                .map(result -> {
                    textInputTelefono.setErrorEnabled(result.isProper() ? false : true);
                    textInputTelefono.setError(result.getMessage());
                 return result.isProper();
                });


        Observable<Boolean> contrasenaValidator =
                RxValidator.createFor(editContrasena)
                        .nonEmpty("No puede estar vacío")
                        .maxLength(15,getResources().getString(R.string.password_max_length))
                        .minLength(8, getResources().getString(R.string.password_min_length))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputContrasena.setErrorEnabled(result.isProper() ? false : true);
                            textInputContrasena.setError(result.getMessage());
                            return result.isProper();
                        });



        Observable<Boolean> contrasenaConfirmValidator =
        RxValidator.createFor(editContrasenaConfirmacion)
                .nonEmpty(getResources().getString(R.string.non_empty))
                .sameAs(editContrasena,getResources().getString(R.string.password_confirm_dont_match))
                .maxLength(15,getResources().getString(R.string.password_max_length))
                .minLength(8, getResources().getString(R.string.password_min_length))
                .onFocusChanged()
                .onValueChanged()
                .toObservable()
                .map(result -> {
                    textInputContrasenaConfirmacion.setErrorEnabled(result.isProper() ? false : true);
                    textInputContrasenaConfirmacion.setError(result.getMessage());
                    return result.isProper();
                });


        Observable<Boolean> emailValidator =
                RxValidator.createFor(editCorreoElectronico)
                .nonEmpty(getResources().getString(R.string.non_empty))
                .email(getResources().getString(R.string.email_incorrect))
                .onFocusChanged()
                .toObservable()
                .map(result -> {
                    textInputCorreoElectronico.setErrorEnabled(result.isProper() ? false : true);
                    textInputCorreoElectronico.setError(result.getMessage());
                    return result.isProper();
                });

        Observable<Boolean> lastnameValidator =
                RxValidator.createFor(editApellido)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputApellido.setErrorEnabled(result.isProper() ? false : true);
                            textInputApellido.setError(result.getMessage());
                            return result.isProper();
                        });
        Observable<Boolean> firstnameValidator =
                RxValidator.createFor(editNombre)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputNombre.setErrorEnabled(result.isProper() ? false : true);
                            textInputNombre.setError(result.getMessage());
                            return result.isProper();
                        });


        firstnameValidator.subscribe();
        lastnameValidator.subscribe();
        emailValidator.subscribe();
        contrasenaValidator.subscribe();
        contrasenaConfirmValidator.subscribe();
        telefonoValidator.subscribe();

        Observable.combineLatest(firstnameValidator, lastnameValidator, emailValidator, contrasenaValidator, contrasenaConfirmValidator, telefonoValidator,
                (nombre, apellido, email, contrasena, confirmaContrasena, telefono) -> nombre && apellido && email && contrasena &&  confirmaContrasena && telefono)
                .subscribe(todoValido -> {
                    Log.i(TAG,"Es Valido todo "+todoValido);
                    buttonContinuar.setEnabled(todoValido);
                });

    }
}