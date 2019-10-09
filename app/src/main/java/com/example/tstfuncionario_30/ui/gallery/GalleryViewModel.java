package com.example.tstfuncionario_30.ui.gallery;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tstfuncionario_30.adapter.Adapter;
import com.example.tstfuncionario_30.modelos.Epi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ListView listView;
    private Epi epi = new Epi();
    private List<Epi> epis = new ArrayList<Epi>();
    private ArrayAdapter<Epi> arrayAdapterEpi;
    private Epi epii = new Epi();


    public GalleryViewModel() {
        mText = new MutableLiveData<>();




    }


    public LiveData<String> getText() {
        return mText;
    }



}