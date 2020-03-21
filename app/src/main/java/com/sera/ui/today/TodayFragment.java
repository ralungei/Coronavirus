package com.sera.ui.today;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sera.R;
import com.sera.model.DateUtil;
import com.sera.model.RetrofitData;
import com.sera.model.StateData;
import com.sera.rest.CovidDataService;
import com.sera.rest.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayFragment extends Fragment {

    private TodayViewModel todayViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView lastUpdateTextView;
    private ProgressBar progressBar;

    private ArrayList<StateData> stateDataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        todayViewModel =
                ViewModelProviders.of(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);


        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) root.findViewById(R.id.cards_recycler_view);
        lastUpdateTextView = (TextView) root.findViewById(R.id.last_update_label);

        showProgress(true);

        CovidDataService covidDataService = RetrofitClientInstance.getRetrofitInstance().create(CovidDataService.class);
        Call<RetrofitData> call = covidDataService.getActual();
        call.enqueue(new Callback<RetrofitData>() {
            @Override
            public void onResponse(Call<RetrofitData> call, Response<RetrofitData> response) {
                loadData(response.body());

                showProgress(false);
            }

            @Override
            public void onFailure(Call<RetrofitData> call, Throwable t) {
                Toast.makeText(getActivity(), "Lo sentimos, parece que algo ha fallado... ¡Intentalo de nuevo más tarde!", Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }

    private void loadData(RetrofitData retrievedData) {
        recyclerView.setHasFixedSize(true);


        DateUtil dateUtil = retrievedData.getDate();

        lastUpdateTextView.setText(getContext().getString(R.string.label_last_update, dateUtil.getDay(), dateUtil.getMonth(), dateUtil.getYear(), dateUtil.getHour()));

        layoutManager = new LinearLayoutManager(getContext());

        mAdapter = new CardsAdapter(retrievedData.getStatesList(), getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
//            recyclerView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }
}
