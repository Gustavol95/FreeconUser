package com.iesoluciones.freeconuser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.adapters.ServiciosAdapter;
import com.iesoluciones.freeconuser.models.Servicio;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 14/08/17.
 */

public class ServiciosFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    ServiciosAdapter adapter;
    long idCategoria; //id para el adapter

    public static ServiciosFragment newInstance(long idCategoria){
        ServiciosFragment fragment=new ServiciosFragment();
        fragment.setIdCategoria(idCategoria);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicios,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        adapter=new ServiciosAdapter(idCategoria, servicio -> {
            //Mandar a fragment y meter a backstack, y meter el servicio WEEEEBBBBB
            Toast.makeText(getContext(), "METELE AL "+servicio.getNombre(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public ServiciosAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ServiciosAdapter adapter) {
        this.adapter = adapter;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }
}