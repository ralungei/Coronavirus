package com.coronavirus.spain.sjra.ui.help;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HelpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HelpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("¡Muy pronto disponible!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}