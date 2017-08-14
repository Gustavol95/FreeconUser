package com.iesoluciones.freeconuser.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.Servicio;
import com.iesoluciones.freeconuser.models.ServicioDao;

import java.util.List;

import butterknife.BindView;

/**
 * Created by iedeveloper on 14/08/17.
 */

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ViewHolder> {

    List<Servicio> servicios;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Servicio categoria);
    }

    public ServiciosAdapter(int idCategoria, OnItemClickListener listener) {
        servicios= App.getInstance().getDaoSession().getServicioDao().queryBuilder().where(ServicioDao.Properties.CategoriaId.eq(idCategoria)).list();
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
        holder.bind(servicios.get(position));
    }

    @Override
    public int getItemCount() {
        return servicios.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        private void bind(Servicio s){
            tvTitulo.setText(s.getNombre());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(s);
                }
            });
        }
    }
}
