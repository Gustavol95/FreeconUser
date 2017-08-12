package com.iesoluciones.freeconuser.network.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;


import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * Created by godoy on 15/03/17.
 */

public abstract class CustomResourceObserver<T> extends ResourceObserver<T> {

    private final static String TAG = CustomResourceObserver.class.getSimpleName();
    private Context context;

    public CustomResourceObserver(Context context) {
        this.context = context;
        this.context.setTheme(R.style.AppTheme);


    }

    private class ResponseError {
        List<String> errors;

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }

        @Override
        public String toString() {
            return "ResponseError{" +
                    "errors=" + errors +
                    '}';
        }
    }


    @Override
    public void onError(Throwable e) {

        Log.i(TAG,"ERROR DE PETICIONNNN "+e.toString());

        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;

            switch (exception.code()) {
                case 400:
                case 401:
                case 403:
                case 404:
                case 405:
                case 423:
                case 422:
                    ResponseError error = paserError(exception);



                    Log.i(TAG, error.toString());
                    new AlertDialog.Builder(context)
                            .setMessage(error.getErrors().get(0))
                            .setTitle("Hubo un problema")
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            }).show();





                    break; // Unprocessable Entity

                case 500:

                    new AlertDialog.Builder(context)
                            .setMessage(App.getInstance().getResources().getString(R.string.unexpected_error))
                            .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null).show();


                    break;

            }


        } else if (e instanceof IOException) {

            if (e instanceof SocketException) {

                new AlertDialog.Builder(context)
                        .setMessage(App.getInstance().getResources().getString(R.string.unreachable_network))
                        .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                        .setCancelable(false)
                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null)
                        .setNeutralButton(App.getInstance().getResources().getString(R.string.network_settings), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                                context.startActivity(intent);

                            }
                        })
                        .show();
            } else if (e instanceof SocketTimeoutException) {


                new AlertDialog.Builder(context)
                        .setMessage(App.getInstance().getResources().getString(R.string.timeout))
                        .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                        .setCancelable(false)
                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null)
                        .show();

            } else if (e instanceof UnknownHostException) {

                Toast.makeText(context, context.getString(R.string.unknown_host_exception), Toast.LENGTH_LONG).show();

            } else {


                Toast.makeText(context, context.getString(R.string.unexpected_error), Toast.LENGTH_LONG).show();

            }


        }

        this.onComplete();

    }

    @Override
    public void onComplete() {

    }

    private ResponseError paserError(HttpException e) {

        ResponseError responseError = null;
        String mensaje = null;
        String body = null;
        try {

            body = e.response().errorBody().source().readUtf8().toString();
            Gson gson = new Gson();
            responseError = gson.fromJson(body, ResponseError.class);

        } catch (IOException ex) {

            mensaje = body;

        }

        return responseError;

    }


}
