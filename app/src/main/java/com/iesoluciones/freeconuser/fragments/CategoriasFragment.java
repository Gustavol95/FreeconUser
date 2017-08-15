package com.iesoluciones.freeconuser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.adapters.CategoriasAdapter;
import com.iesoluciones.freeconuser.models.Categoria;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by iedeveloper on 07/08/17.
 */

public class CategoriasFragment extends Fragment {

    static final String TAG=CategoriasFragment.class.getSimpleName();
    @BindView(R.id.recycler)
    RecyclerView recyclerView;


    public static CategoriasFragment newInstance(){
        return new CategoriasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        CategoriasAdapter adapter = new CategoriasAdapter(categoria -> {
            //Mandar a otro fragment de recycler de servicios
            Log.i(TAG, categoria.getId()+"");
            getFragmentManager().beginTransaction().replace(R.id.frameDrawer,ServiciosFragment.newInstance(categoria.getId()))
                    .addToBackStack("TAG")
                    .commit();
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }
}
