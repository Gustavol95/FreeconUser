package com.iesoluciones.freeconuser.services;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by iedeveloper on 10/08/17.
 */

public class FirebaseInstanceService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
