package com.example.tstfuncionario_30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tstfuncionario_30.R;
import com.example.tstfuncionario_30.modelos.Epi;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Epi> {
    private Context context;
    private List<Epi> epi;

    public Adapter( Context context, ArrayList<Epi> epi){
        super(context,0,epi);
        this.context = context;
        this.epi = epi;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View epiitem = convertView;


        if (epiitem == null) {
            epiitem = LayoutInflater.from(context)
                    .inflate(R.layout.epi_list,parent,false);
        }

        Epi epipego = epi.get(position);

        

        return epiitem;
    }
}
