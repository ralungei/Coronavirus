package com.coronavirus.spain.sjra.ui.help;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.model.HelpData;
import com.coronavirus.spain.sjra.model.PhoneData;
import com.coronavirus.spain.sjra.rest.CovidDataService;
import com.coronavirus.spain.sjra.rest.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpFragment extends Fragment {

    private ArrayList<HelpData> helpDataArrayList = new ArrayList<>();
    private ArrayList<PhoneData> phonesArrayList = new ArrayList<>();

    private RecyclerView helpRecyclerView;
    private RecyclerView.Adapter helpAdapter;
    private RecyclerView.LayoutManager helpLayoutManager;

    private View emptyTextLayout;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_help, container, false);

        progressBar = root.findViewById(R.id.helpProgressBar);
        emptyTextLayout = root.findViewById(R.id.infoEmptyTextLayout);

        helpRecyclerView = root.findViewById(R.id.helpRecyclerView);
        helpLayoutManager = new LinearLayoutManager(getContext());
        helpRecyclerView.setLayoutManager(helpLayoutManager);
        helpRecyclerView.setHasFixedSize(true);
        helpAdapter = new HelpListAdapter(helpDataArrayList, getContext());
        helpRecyclerView.setAdapter(helpAdapter);

        CovidDataService covidDataService = RetrofitClientInstance.getRetrofitInstance().create(CovidDataService.class);
        Call<ArrayList<HelpData>> call = covidDataService.getHelp();
        call.enqueue(new Callback<ArrayList<HelpData>>() {
            @Override
            public void onResponse(Call<ArrayList<HelpData>> call, Response<ArrayList<HelpData>> response) {
                if (response.isSuccessful()) {
                    loadData(response.body());
                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HelpData>> call, Throwable t) {
                showProgress(false);
                emptyTextLayout.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    private void loadData(ArrayList<HelpData> retrievedData) {

        retrievedData.remove(2);
        System.out.println(retrievedData.toString());
        helpDataArrayList.addAll(retrievedData);

        helpAdapter = new HelpListAdapter(helpDataArrayList, getContext());
    }

    private void showProgress(final boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
