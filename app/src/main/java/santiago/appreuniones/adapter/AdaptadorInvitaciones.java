package santiago.appreuniones.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import santiago.appreuniones.R;
import santiago.appreuniones.dto.Estados_reunion_usuario;

public class AdaptadorInvitaciones extends RecyclerView.Adapter<AdaptadorInvitaciones.ViewHolderInvitaciones>{

    List<Estados_reunion_usuario> listaInvitaciones;

    public AdaptadorInvitaciones(List<Estados_reunion_usuario> listaInvitaciones) {
        this.listaInvitaciones=listaInvitaciones;
    }

    @Override
    public ViewHolderInvitaciones onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.invitaciones_card, null, false);
        return new ViewHolderInvitaciones(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderInvitaciones holder, int position) {
        holder.nombreReu.setText(listaInvitaciones.get(position).getReunion().getNombre());
        holder.descripcionReu.setText(listaInvitaciones.get(position).getReunion().getDescripcion());

    }

    @Override
    public int getItemCount() {
        return listaInvitaciones.size();
    }

    public class ViewHolderInvitaciones extends RecyclerView.ViewHolder {

    TextView nombreReu, descripcionReu;

        public ViewHolderInvitaciones(View itemView) {
            super(itemView);

            nombreReu=itemView.findViewById(R.id.nombre_reu);
            descripcionReu=itemView.findViewById(R.id.desc_reu);

        }
    }
}
