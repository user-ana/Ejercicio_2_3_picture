package com.uth.ejercicio_2_3.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uth.e23.R;
import com.uth.ejercicio_2_3.models.cPhotograh;

import java.util.ArrayList;

public class adapterSignature extends RecyclerView.Adapter<adapterSignature.adapterSignatureViewHolder>
{
    private final ArrayList<cPhotograh> oSignatureList;

    public adapterSignature(ArrayList<cPhotograh> oSignatureList) {
        this.oSignatureList = oSignatureList;
    }

    @NonNull
    @Override
    public adapterSignatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_photograh,parent,false);
        return new adapterSignatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterSignatureViewHolder holder, int position)
    {
        holder.oTextView.setText(oSignatureList.get(position).getDescripcion());
        Log.e("FIRMA",oSignatureList.get(position).getFoto().toString());
        holder.oSignatureView.setImageBitmap(oSignatureList.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return oSignatureList.size();
    }

    public static class adapterSignatureViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView oSignatureView;
        private final TextView oTextView;
        public adapterSignatureViewHolder(@NonNull View itemView)
        {
            super(itemView);
            oSignatureView = itemView.findViewById(R.id.signature_view_list);
            oTextView = itemView.findViewById(R.id.txtDetalleSignatureList);
        }
    }
}
