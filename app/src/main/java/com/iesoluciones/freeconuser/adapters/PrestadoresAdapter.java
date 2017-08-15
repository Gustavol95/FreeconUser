package com.iesoluciones.freeconuser.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freeconuser.R;

import butterknife.BindView;


public class PrestadoresAdapter extends RecyclerView.Adapter<PrestadoresAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_prestadores, null);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCalificacion)
        TextView tvCalificacion;
        @BindView(R.id.ivEstrella)
        ImageView ivEstrella;
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;
        @BindView(R.id.tvDescripcion)
        TextView tvDescripcion;
        @BindView(R.id.fabVer)
        FloatingActionButton fabVer;
        @BindView(R.id.fabDescartar)
        FloatingActionButton fabDescartar;


        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
