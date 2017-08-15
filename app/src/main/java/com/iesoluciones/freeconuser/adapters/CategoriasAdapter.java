package com.iesoluciones.freeconuser.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.Categoria;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 14/08/17.
 */

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {


    static final String TAG=CategoriasAdapter.class.getSimpleName();
    List<Categoria> categorias;
    OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Categoria categoria);
    }

    public CategoriasAdapter(OnItemClickListener listener) {
        categorias= App.getInstance().getDaoSession().getCategoriaDao().loadAll();
        Log.i(TAG,categorias.toString());
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_categorias, null);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(categorias.get(position));
    }

    @Override
    public int getItemCount() {
        return categorias.size()-1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.ivImagen)
        ImageView ivImagen;
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void bind(Categoria c){
            tvTitulo.setText(c.getNombre());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(c);
                }
            });
        }
    }
}
