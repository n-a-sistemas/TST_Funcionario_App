package com.example.tstfuncionario_30.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.tstfuncionario_30.R;
import com.example.tstfuncionario_30.adapter.Adapter;
import com.example.tstfuncionario_30.modelos.Epi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private DatabaseReference databaseReference;
    private GalleryViewModel galleryViewModel;
    private String profissao;
    TextView textViewluva;
    private Epi epi = new Epi();
    private List<Epi> epis = new ArrayList<Epi>();
    private ArrayAdapter<Epi> arrayAdapterEpi;
    private Epi epii = new Epi();
    private ListView lista;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        databaseReference = FirebaseDatabase.getInstance().getReference();
        eventosFuncionario();
        pesquisaEpi();






        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        lista = root.findViewById(R.id.list_view_epi);


        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);



            }

        });



        return root;
    }
    public void eventosFuncionario(){
        databaseReference.child("projetotst").child("funcionario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        profissao = (dataSnapshot.child("profissao").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void pesquisaEpi(){


        databaseReference.child("projetotst").child("funcionario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Epi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                epis.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    epii = snapshot.getValue(Epi.class);

                    epis.add(epii);
                }


                arrayAdapterEpi = new Adapter(getActivity(),
                        (ArrayList<Epi>) epis);
                lista.setAdapter(arrayAdapterEpi);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }












}