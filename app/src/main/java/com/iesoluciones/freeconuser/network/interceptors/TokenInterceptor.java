package com.iesoluciones.freeconuser.network.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by godoy on 14/03/17.
 */


public class TokenInterceptor implements Interceptor {

    private static final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        Request.Builder builder = original.newBuilder()
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept","application/json");
//
//
//        if(PreferencesHelper.getInstance().getToken()!=null ){
//
//            refreshToken();
//
//            HttpUrl url = original.url()
//                .newBuilder()
//                .addQueryParameter("tokenData",PreferencesHelper.getInstance().getToken())
//                .build();
//            builder.url(url);
//            builder.method(original.method(), original.body());
//        }
//
//        Request request = builder.build();
                                            //request
        Response response = chain.proceed(chain.request());

        return response;
    }

/*
    private void refreshToken() throws IOException{

        if( PreferencesHelper.getInstance().expirationDate() - (10 * 60000) <=
                System.currentTimeMillis() ){

            OkHttpClient client = new OkHttpClient();

            HttpUrl url = HttpUrl.parse(HttpClientScaAuthorize.BASE_URL)
                    .newBuilder()
                    .addPathSegment("Authorize")
                    .addQueryParameter("userName",PreferencesHelper.getInstance().getUser())
                    .addQueryParameter("userPassword",PreferencesHelper.getInstance().getPassword())
                    .build();

            Request request = new Request.Builder()
                    .header("Content-type","application/json")
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();

            if(response.code()== 200){

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();

                String json  = response.body().source().readUtf8().toString();

                TokenResponse tokenResponse = gson.fromJson(json, TokenResponse.class);

                PreferencesHelper.getInstance()
                        .setToken(tokenResponse.getTokenData());
                PreferencesHelper.getInstance()
                        .setExpirationDate(tokenResponse.getExpirationDateToken());

            }
        }
    }*/
}
