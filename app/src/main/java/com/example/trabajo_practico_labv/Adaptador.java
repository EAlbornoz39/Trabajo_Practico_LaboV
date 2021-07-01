package com.example.trabajo_practico_labv;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class Adaptador extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{

    public TextView txtTitulo,txtFecha,txtContenido;
    private ItemClickListener itemClickListener;

    public Adaptador(View itemView) {
        super(itemView);

        txtTitulo = (TextView)itemView.findViewById(R.id.txtTitulo);
        txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        txtContenido = (TextView)itemView.findViewById(R.id.txtContenido);


        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getLayoutPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getLayoutPosition(),true);
        return true;
    }
}

class AdaptadorView extends RecyclerView.Adapter<Adaptador> {

    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public AdaptadorView(RssObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public Adaptador onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rss,parent,false);
        return new Adaptador(itemView);
    }

    @Override
    public void onBindViewHolder(Adaptador holder, int position) {
        holder.txtTitulo.setText(rssObject.getItems().get(position).getTitle());
        holder.txtFecha.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtContenido.setText(rssObject.getItems().get(position).getContent());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}
