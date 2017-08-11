package com.iesoluciones.freeconuser.network.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by godoy on 13/03/17.
 */

public class LogInterceptor implements Interceptor {

    private static final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request request = chain.request();



        printRequest(request);


        long t1 = System.nanoTime();
        Response response = chain.proceed(request);
       // Log.i(TAG,response.body().source().readUtf8().toString());
        long t2 = System.nanoTime();
        return response;
    }


    private void printRequest(Request request) {
        try {
            String requestJson = "";
            final RequestBody copy = request.body();
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            requestJson = buffer.readUtf8();
            Log.d("REQUEST", "URL: " + request.url().toString() + " - Method: " + request.method().toString() + " - JSON: " + requestJson.toString() + " - HEADERS :" + request.headers().toString());
        } catch (final IOException e) {
        }
    }


}
