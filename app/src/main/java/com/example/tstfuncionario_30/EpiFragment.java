package com.example.tstfuncionario_30;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tstfuncionario_30.ui.home.HomeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EpiFragment extends Fragment {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textViewNome, textViewEmail,textViewProfissao;
    private HomeViewModel homeViewModel;
    private String nome,email,profissao;
    private Button botao;



    public EpiFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        conectaBanco();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_epi,
                container, false);
        textViewNome = root.findViewById(R.id.text_view_nome_main);
        textViewEmail = root.findViewById(R.id.text_view_email_main);
        textViewProfissao= root.findViewById(R.id.text_view_profissao_funcionario);

        evento();
        FloatingActionButton fab = root.findViewById(R.id.fab_sair);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(getActivity(),EditarActivity.class);
              startActivity(intent);
            }
        });

        return root;


    }

    public void conectaBanco(){
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public void evento() {
        databaseReference.child("projetotst").child("funcionario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        nome = (dataSnapshot.child("nome").getValue().toString());
                        textViewNome.setText(nome);
                        email = (dataSnapshot.child("email").getValue().toString());
                        textViewEmail.setText(email);
                        profissao= (dataSnapshot.child("profissao").getValue().toString());
                        textViewProfissao.setText(profissao);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}
