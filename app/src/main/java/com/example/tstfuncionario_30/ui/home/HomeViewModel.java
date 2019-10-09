package com.example.tstfuncionario_30.ui.home;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> texto;
    private String textto;




    public HomeViewModel() {

        mText = new MutableLiveData<>();

        texto= new MutableLiveData<>();
        texto.setValue("zika");


    }



    public LiveData<String> getText() {
        return mText;

    }

    public MutableLiveData<String> getmText() {
        return mText;
    }

    public void setmText(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public MutableLiveData<String> getTexto() {
        return texto;
    }

    public void setTexto(MutableLiveData<String> texto) {
        this.texto = texto;
    }

    public String getTextto() {
        return textto;
    }

    public void setTextto(String textto) {
        this.textto = textto;
    }
}