package com.sera.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sera.R;
import com.sera.model.CovidData;

import java.util.ArrayList;

public class TodayFragment extends Fragment {

    private TodayViewModel todayViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<CovidData> covidDataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        covidDataList.add(new CovidData("Infectados", 328, 13716, 238));
        covidDataList.add(new CovidData("Defunciones", 1, 2, 3));
        covidDataList.add(new CovidData("Curados", 1, 2, 3));


        todayViewModel =
                ViewModelProviders.of(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        todayViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        recyclerView = (RecyclerView) root.findViewById(R.id.cards_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CardsAdapter(covidDataList, getContext());
        recyclerView.setAdapter(mAdapter);


        return root;
    }
}
