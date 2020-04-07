package com.coronavirus.spain.sjra.ui.help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.model.PhoneData;

import java.util.ArrayList;

class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.MyViewHolder> {
    private ArrayList<PhoneData> dataList;
    private Context context;

    public PhoneListAdapter(ArrayList<PhoneData> myDataList, Context mContext) {
        dataList = myDataList;
        context = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView region;
        Button phoneButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.regionTextView);
            phoneButton = itemView.findViewById(R.id.phoneButton);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.region.setText(dataList.get(position).getRegion());
        holder.phoneButton.setText(dataList.get(position).getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "https://" + dataList.get(position).getLink();
//
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
