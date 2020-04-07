package com.coronavirus.spain.sjra.ui.help;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.model.HelpData;

import java.util.ArrayList;

class HelpListAdapter extends RecyclerView.Adapter<HelpListAdapter.MyViewHolder> {
    private ArrayList<HelpData> dataList;
    private Context context;

    public HelpListAdapter(ArrayList<HelpData> myDataList, Context mContext) {
        dataList = myDataList;
        context = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        View bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            image = itemView.findViewById(R.id.imageView);
            bar = itemView.findViewById(R.id.left_bar);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String title = dataList.get(position).getTitle();

        holder.title.setText(title);

        if (title.equals("Teléfonos de información")) {
//            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            holder.image.setBackgroundResource(R.drawable.ic_phone_black_24dp);
            holder.bar.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else if (title.equals("Información General")) {
            holder.image.setBackgroundResource(R.drawable.ic_assistant_photo_black_24dp);
            holder.bar.setBackgroundColor(context.getResources().getColor(R.color.blue));
        } else if (title.equals("Mapa del mundo, avance del coronavirus")) {
//            holder.image.setBackgroundResource(R.drawable.ic_public_black_24dp);
//            holder.itemView.setVisibility(View.GONE);
        } else if (title.equals("Notas de prensa")) {
            holder.image.setBackgroundResource(R.drawable.ic_rss_feed_black_24dp);
            holder.bar.setBackgroundColor(context.getResources().getColor(R.color.green));
        } else if (title.equals("Documentos técnicos para profesionales")) {
            holder.image.setBackgroundResource(R.drawable.ic_assignment_black_24dp);
            holder.bar.setBackgroundColor(context.getResources().getColor(R.color.gray));
        } else if (title.equals("Sanidad en datos")) {
            holder.image.setBackgroundResource(R.drawable.ic_local_hospital_black_24dp);
            holder.bar.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "https://" + dataList.get(position).getLink();

                String url = dataList.get(position).getLink();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
