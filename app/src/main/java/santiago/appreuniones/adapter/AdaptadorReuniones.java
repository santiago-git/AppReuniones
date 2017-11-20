package santiago.appreuniones.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import santiago.appreuniones.R;
import santiago.appreuniones.dto.Reunion;

public class AdaptadorReuniones extends RecyclerView.Adapter<AdaptadorReuniones.ReunionesViewHolder> implements View.OnClickListener{

    private final List<Reunion> reuniones;
    private View.OnClickListener listener;

    public AdaptadorReuniones(List<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    @Override
    public ReunionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
        view.setOnClickListener(this);
        return new ReunionesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ReunionesViewHolder holder, int position) {
        holder.txtItem.setText((reuniones.get(position).getNombre()));
        holder.txtItem2.setText((reuniones.get(position).getDescripcion()));

    }

    @Override
    public int getItemCount() {
        return reuniones.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ReunionesViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItem, txtItem2;

        public ReunionesViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txt_nom);
            txtItem2 = (TextView) itemView.findViewById(R.id.txt_desc);
        }


    }
}