package com.firstapp.securednotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Qoute> list;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
       this.listener = listener;
    }
    public RecyclerViewAdapter(Context context, List<Qoute> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.items_layoute,parent,false);
        ViewHolder viewHolder= new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.qoute.setText(list.get(position).getQoute());
        holder.qouteWriter.setText(list.get(position).getQoutewriter());
        holder.Qoutetyp.setText(list.get(position).getQouteType());
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    TextView qoute,qouteWriter,Qoutetyp;
    ImageView ivdelete,ivedit;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            qoute=itemView.findViewById(R.id.tvQouteid);
            qouteWriter=itemView.findViewById(R.id.tvQouteWriter);
            Qoutetyp=itemView.findViewById(R.id.tvTypeWriter);
            ivdelete=itemView.findViewById(R.id.imageViewdeleteid);
            ivedit=itemView.findViewById(R.id.imageVieweditid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            ivedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });
            ivdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
