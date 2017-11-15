package santiago.appreuniones.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import santiago.appreuniones.R;
import santiago.appreuniones.dto.Reunion;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolderDatos> {

    List<Reunion> reuniones;

    public ListAdapter(List<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.txtItem.setText((reuniones.get(position).getNombre()));
        holder.txtItem2.setText((reuniones.get(position).getDescripcion()));

    }

    @Override
    public int getItemCount() {
        return reuniones.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtItem, txtItem2;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txt_nom);
            txtItem2 = (TextView) itemView.findViewById(R.id.txt_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "click en: "+ getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}