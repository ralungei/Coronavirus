package com.coronavirus.spain.sjra.ui.today;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.model.DateUtil;
import com.coronavirus.spain.sjra.model.StateData;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {
    private List<StateData> stateDataList;
    private DateUtil retrievedDate;
    private Context context;

    public CardsAdapter(List<StateData> myStateDataList, DateUtil mRetrievedDate, Context mContext) {
        stateDataList = myStateDataList;
        retrievedDate = mRetrievedDate;
        context = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView headerText;
        TextView newCases;
        TextView totalCases;
        TextView diffCases;
        TextView percentageRate;
        View bottomBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            headerText = itemView.findViewById(R.id.header_text);
            newCases = itemView.findViewById(R.id.new_cases_number);
            totalCases = itemView.findViewById(R.id.total_cases_number);
            diffCases = itemView.findViewById(R.id.diff_cases_number);
            bottomBar = itemView.findViewById(R.id.bottom_bar);
            percentageRate = itemView.findViewById(R.id.percentage_rate_text_view);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StateData covData = stateDataList.get(position);

        String id = covData.getId();
        int newCases = covData.getNewCases();
        int diffCases = covData.getDiffCases();
        int totalCases = covData.getTotalCases();

        DecimalFormat df = new DecimalFormat("#.00");
        double percentage = Math.abs((double) diffCases / (newCases - diffCases) * 100);

        char sign = Character.MIN_VALUE;
        int textColor = R.color.black;
        int headerTitle = 0;
        int color = R.color.black;

        if ((diffCases > 0 && !id.equals(context.getResources().getString(R.string.key_healed))) || (id.equals(context.getResources().getString(R.string.key_healed)) && diffCases < 0)) {
            textColor = R.color.darker_red;
        } else {
            textColor = R.color.darker_green;
        }

        if (diffCases > 0) {
            sign = '+';
        } else {
            sign = '-';
        }

        if (id.equals(context.getResources().getString(R.string.key_infected))) {
            headerTitle = R.string.header_infected;
            color = R.color.colorAccent;
        } else if (id.equals(context.getResources().getString(R.string.key_dead))) {
            headerTitle = R.string.header_dead;
            color = R.color.black;

        } else if (id.equals(context.getResources().getString(R.string.key_healed))) {
            headerTitle = R.string.header_healed;
            color = R.color.green;
        }

        NumberFormat numFormat = NumberFormat.getInstance(Locale.GERMAN);

        holder.percentageRate.setText(context.getString(R.string.percentage_value, sign, df.format(percentage).toString()));
        holder.percentageRate.setTextColor(ContextCompat.getColor(context, textColor));

        holder.headerText.setText(headerTitle);
//        holder.headerText.setTextColor(ContextCompat.getColor(context, color));
        holder.bottomBar.setBackgroundColor(ContextCompat.getColor(context, color));

        holder.newCases.setText(numFormat.format(newCases));
        holder.diffCases.setText(context.getString(R.string.label_than_yesterday, sign, Math.abs(diffCases)));
        holder.totalCases.setText(numFormat.format(totalCases));

    }

    @Override
    public int getItemCount() {
        if (stateDataList != null)
            return stateDataList.size();
        return 0;
    }

}
