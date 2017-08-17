package com.iesoluciones.freeconuser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freeconuser.App;
import com.iesoluciones.freeconuser.ObservableHelper;
import com.iesoluciones.freeconuser.R;
import com.iesoluciones.freeconuser.models.Prestador;
import com.iesoluciones.freeconuser.models.Servicio;
import com.iesoluciones.freeconuser.network.helpers.CustomResourceObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 15/08/17.
 */

public class PrestadorDetalleFragment  extends Fragment {

    static final String TAG=PrestadorDetalleFragment.class.getSimpleName();

    @BindView(R.id.ivImagen)
    ImageView ivImagen;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    @BindView(R.id.tvSubtitulo)
    TextView tvSubtitulo;
    @BindView(R.id.tvDescripcion)
    TextView tvDescripcion;
    @BindView(R.id.editMotivo)
    AppCompatEditText editMotivo;
    @BindView(R.id.buttonSolicitar)
    AppCompatButton buttonSolicitar;
    Prestador prestador;
    Servicio servicio;

    public static PrestadorDetalleFragment newInstance(Prestador prestador, Servicio servicio){
        PrestadorDetalleFragment fragment=new PrestadorDetalleFragment();
        fragment.setServicio(servicio);
        fragment.setPrestador(prestador);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prestadores_detalle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        tvTitulo.setText(prestador.getFirst_name()+" "+prestador.getLast_name());
        tvSubtitulo.setText(prestador.getProfesion());
        tvDescripcion.setText(prestador.getDescripcion());
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }


    @OnClick(R.id.buttonSolicitar)
    public void onClickButtonSolicitar(){
        Log.i(TAG,"Vamonos ricky");
        ObservableHelper.solicitar(servicio.getCategoriaId()+"",servicio.getId()+"",prestador.getUsuario_id()+"",editMotivo.getText().toString())
                .subscribe(new CustomResourceObserver<ResponseBody>(getContext()) {
                    @Override
                    public void onNext(ResponseBody value) {
                        Log.i(TAG,"JALOOOOOOO");

                        //Meter a la BD y mandar a Historial de Solicitudes.
                    }

                    @Override
                    public void onError(Throwable e) {
                        //super.onError(e);
                        Log.i(TAG,"Tronó el solicitar :( "+e.toString());
                    }
                });
    }


    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }


}