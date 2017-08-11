package com.iesoluciones.freeconuser.network.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by godoy on 08/05/17.
 */

public class ConnectivityHelper {

    public static boolean isOnline(Context context){
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {

            return true;

        }

        return  false;
    }


}
