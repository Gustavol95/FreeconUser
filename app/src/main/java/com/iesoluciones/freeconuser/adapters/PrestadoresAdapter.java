package com.iesoluciones.freeconuser.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.Categoria;
import com.iesoluciones.freeconuser.models.Prestador;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PrestadoresAdapter extends RecyclerView.Adapter<PrestadoresAdapter.ViewHolder> {


    List<Prestador> prestadorList;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Prestador categoria);
    }
    public PrestadoresAdapter(List<Prestador> prestadorList, OnItemClickListener listener) {
        this.prestadorList = prestadorList;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_prestadores, null);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(prestadorList.get(position));
    }

    @Override
    public int getItemCount() {
        return prestadorList.size();
    }

    public List<Prestador> getPrestadorList() {
        return prestadorList;
    }

    public void setPrestadorList(List<Prestador> prestadorList) {
        this.prestadorList = prestadorList;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCalificacion)
        TextView tvCalificacion;
        @BindView(R.id.ivEstrella)
        ImageView ivEstrella;
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;   //Lo uso para la profesion
        @BindView(R.id.tvDescripcion)
        TextView tvDescripcion;
        @BindView(R.id.tvFolio)
        TextView tvFolio;    //Lo uso para el nombre
        @BindView(R.id.tvFecha)
        TextView tvFecha;    // Lo uso para lugar



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Prestador p){
            tvCalificacion.setText("N/A");
            tvFolio.setText(p.getFirst_name()+" "+p.getLast_name());
            tvTitulo.setText(p.getProfesion());
            tvDescripcion.setText(p.getDescripcion());
            tvFecha.setText(p.getCiudad()+", "+p.getEstado());
            itemView.setOnClickListener(v -> {
                listener.onItemClick(p);
            });

        }

    }
}
