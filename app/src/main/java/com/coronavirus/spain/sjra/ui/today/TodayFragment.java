package com.coronavirus.spain.sjra.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.model.DateUtil;
import com.coronavirus.spain.sjra.model.RetrofitData;
import com.coronavirus.spain.sjra.model.StateData;
import com.coronavirus.spain.sjra.rest.CovidDataService;
import com.coronavirus.spain.sjra.rest.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView lastUpdateTextView;
    private TextView quarantineTextView;
    private View emptyTextLayout;
    private ProgressBar progressBar;

    private ArrayList<StateData> stateDataList = new ArrayList<>();
    private DateUtil dataDate = new DateUtil();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_today, container, false);

        progressBar = root.findViewById(R.id.dataProgressBar);
        recyclerView = root.findViewById(R.id.cards_recycler_view);
        lastUpdateTextView = root.findViewById(R.id.update_date_label);
        quarantineTextView = root.findViewById(R.id.quarantine_label);
        emptyTextLayout = root.findViewById(R.id.todayEmptyTextLayout);

        showProgress(true);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        mAdapter = new CardsAdapter(stateDataList, dataDate, getContext());

        recyclerView.setAdapter(mAdapter);

        CovidDataService covidDataService = RetrofitClientInstance.getRetrofitInstance().create(CovidDataService.class);
        Call<RetrofitData> call = covidDataService.getActual();
        call.enqueue(new Callback<RetrofitData>() {
            @Override
            public void onResponse(Call<RetrofitData> call, Response<RetrofitData> response) {
                emptyTextLayout.setVisibility(View.GONE);
                loadData(response.body());

                showProgress(false);
            }

            @Override
            public void onFailure(Call<RetrofitData> call, Throwable t) {
                emptyTextLayout.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    private void loadData(RetrofitData retrievedData) {
        DateUtil dataDate = retrievedData.getDate();
        DateUtil quarantineDate = retrievedData.getQuarantineDate();

        stateDataList.addAll(retrievedData.getStatesList());

        lastUpdateTextView.setText(getContext().getString(R.string.label_data_date, dataDate.getDay(), dataDate.getMonth(), dataDate.getYear()));
        quarantineTextView.setText(getContext().getString(R.string.label_quarantine_end, quarantineDate.getDay(), quarantineDate.getMonth(), quarantineDate.getYear()));

        mAdapter = new CardsAdapter(retrievedData.getStatesList(), dataDate, getContext());
    }

    private void showProgress(final boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
