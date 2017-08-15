package com.iesoluciones.freeconuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.network.helpers.CustomResourceObserver;


public class SplashActivity extends AppCompatActivity {

    static final  String TAG=SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            if (accessToken.isExpired()) {
                Toast.makeText(this, "Expiró, pidelo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this,DrawerActivity.class));
                finish();
            } else {
                Toast.makeText(this, "JALEESE COMPA", Toast.LENGTH_SHORT).show();
                ObservableHelper.loginFb(accessToken.getToken())
                        .subscribe(new CustomResourceObserver<LoginFbResponse>(SplashActivity.this) {
                            @Override
                            public void onNext(LoginFbResponse value) {
                                if (value.getUsuario().getActivado() == 1) {
                                    //Pasa directito al dashboard
                                    startActivity(new Intent(SplashActivity.this,DrawerActivity.class));
                                    finish();
                                    Log.i(TAG, "ACTIVADO TRUE");
                                } else {
                                    startActivity(new Intent(SplashActivity.this,DrawerActivity.class));
                                    finish();
                                }
                                Log.i(TAG, "ACTIVADO aasas");
                            }

                        });
            }
        } else {
            Toast.makeText(this, "PIDELOO", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SplashActivity.this,DrawerActivity.class));
            finish();
        }



    }

}
