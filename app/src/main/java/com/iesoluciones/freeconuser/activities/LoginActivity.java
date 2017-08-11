package com.iesoluciones.freeconuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.inputLayoutUsuario)
    TextInputLayout inputLayoutUsuario;
    @BindView(R.id.editUsuario)
    TextInputEditText editUsuario;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputConstrasena;
    @BindView(R.id.editContrasena)
    TextInputEditText editContrasena;
    @BindView(R.id.tvOlvidarContrasena)
    TextView tvOlvidarContrasena;
    @BindView(R.id.buttonIniciarSesion)
    AppCompatButton buttonIniciarSesion;
    @BindView(R.id.linearRegistrate)
    LinearLayout linearRegistrate;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        //Log.i(TAG,"FIREBASE TOKEENNN -----------> "+ FirebaseInstanceId.getInstance().getToken());

       // AccessToken accessToken = AccessToken.getCurrentAccessToken();


/*        if (accessToken != null) {
            if (accessToken.isExpired()) {
                Toast.makeText(this, "Expiró, pidelo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "JALEESE COMPA", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "PIDELOO", Toast.LENGTH_SHORT).show();
        }*/


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
           public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i(TAG, loginResult.getAccessToken().getToken() + " TOKEN FB");

            }

            @Override
            public void onCancel() {
                // App code
                Log.i(TAG, " CANCELÓOOO");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i(TAG, exception.getMessage() + " VALIO V FB");
            }
        });
    }


    @OnClick(R.id.linearRegistrate)
    public void registrarse() {
        startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
    }

    @OnClick(R.id.buttonIniciarSesion)
    public void iniciarSesion() {

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
