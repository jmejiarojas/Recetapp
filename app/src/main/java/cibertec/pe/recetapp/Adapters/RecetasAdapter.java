package cibertec.pe.recetapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cibertec.pe.recetapp.Entities.Receta;
import cibertec.pe.recetapp.R;

/**
 * Created by USUARIO on 13/07/2017.
 */

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.ViewHolder>{

    Context context;
    List<Receta> recetaList;

    public RecetasAdapter(Context context, List<Receta> recetaList) {
        this.context = context;
        this.recetaList = recetaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receta_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewNombre.setText(recetaList.get(position).getNombre());
        holder.textViewDescripcion.setText(recetaList.get(position).getDescripcion());
        holder.textViewNumPersonas.setText("Personas" + recetaList.get(position).getNumPersonas());
        holder.textViewPreparacion.setText(recetaList.get(position).getPreparacion());

    }

    @Override
    public int getItemCount() {
        return recetaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewNumPersonas;
        TextView textViewDescripcion;
        TextView textViewPreparacion;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardViewRecetas);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombreReceta);
            textViewNumPersonas = (TextView) itemView.findViewById(R.id.textViewNumPersonas);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);
            textViewPreparacion = (TextView) itemView.findViewById(R.id.textViewPreparacion);
        }
    }
}
