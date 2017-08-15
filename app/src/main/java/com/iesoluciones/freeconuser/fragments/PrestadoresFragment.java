package com.iesoluciones.freeconuser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.adapters.PrestadoresAdapter;
import com.iesoluciones.freeconuser.models.Prestador;
import com.iesoluciones.freeconuser.network.helpers.CustomResourceObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 14/08/17.
 */

public class PrestadoresFragment extends Fragment {

    static final String TAG=PrestadoresFragment.class.getSimpleName();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    PrestadoresAdapter adapter;


    public static PrestadoresFragment newInstance(){
        return new PrestadoresFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prestadores,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        adapter=new PrestadoresAdapter(new ArrayList<Prestador>(), prestador -> {
            //Mandamos al fragment detalle
            getFragmentManager().beginTransaction().replace(R.id.frameDrawer,PrestadorDetalleFragment.newInstance(prestador))
                    .addToBackStack("TAG")
                    .commit();

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        swipeRefresh.setRefreshing(true);
        swipeRefresh.setOnRefreshListener(() -> {
            //Actualizar
        });

        ObservableHelper.getPrestadoresPorServicio("12")
                .subscribe(new CustomResourceObserver<List<Prestador>>(getContext()) {
                    @Override
                    public void onNext(List<Prestador> value) {
                        Log.i(TAG,value.toString());
                        adapter.getPrestadorList().addAll(value);
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.i(TAG,"troniqui");
                        swipeRefresh.setRefreshing(false);
                    }
                });

    }
}
