package com.example.tstfuncionario_30.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.tstfuncionario_30.R;
import com.example.tstfuncionario_30.modelos.Funcionario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String ponto;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    private String data;
    private String dataacidente;
    private String datachurasco;
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        eventos();
        eventoData();
        ranking();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView1 = root.findViewById(R.id.text_view_pontuacao);
        textView2 = root.findViewById(R.id.text_view_churasco);
        textView3 = root.findViewById(R.id.text_view_dias_sem_acidente);
        textView4 = root.findViewById(R.id.text_view_primeiro);
        textView5 = root.findViewById(R.id.text_view_segundo);
        textView6 = root.findViewById(R.id.text_view_terceiro);
        textView7 = root.findViewById(R.id.text_view_nome_primeiro);
        textView8 = root.findViewById(R.id.text_view_nome_segundo);
        textView9 = root.findViewById(R.id.text_view_nome_terceiro);





        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);


            }
        });


        return root;
    }


    public void eventos(){
        databaseReference.child("projetotst").child("funcionario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ponto = (dataSnapshot.child("pontos").getValue().toString());

                textView1.setText(ponto);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void eventoData() {

        databaseReference.child("projetotst")
                .addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataacidente = (dataSnapshot.child("data_de_acidente")
                                .getValue().toString());
                        datachurasco = (dataSnapshot.child("data_do_churasco")
                                .getValue().toString());


                        Date hoje = Calendar.getInstance().getTime();

                        Date cal = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                                Locale.ENGLISH);
                        try {
                           cal =  sdf.parse(dataacidente);
                           Long a =  ((hoje.getTime() - cal.getTime()) / (1000 * 60 * 60 * 24));

                           textView3.setText(a.toString());

                        } catch (ParseException e) {
                            e.printStackTrace();
                       }

                        Date churasco = new Date();
                        SimpleDateFormat mdf = new SimpleDateFormat("dd/MM/yyyy",
                                Locale.ENGLISH);
                        try {
                            churasco = mdf.parse(datachurasco);

                            Long churas = ((churasco.getTime() - hoje.getTime()) / (1000 * 60 * 60 * 24));
                            textView2.setText(churas.toString());


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }


    public void ranking(){
        databaseReference.child("projetotst").child("funcionario").orderByChild("nome")
                .addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                funcionarios.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Funcionario funcionario = snapshot.getValue(Funcionario.class);
                    funcionarios.add(funcionario);
                }


                Collections.sort(funcionarios);

                funcionarios.get(0);
                textView7.setText(funcionarios.get(0).getNome());
                textView4.setText(funcionarios.get(0).getPontos());

                funcionarios.get(1);
                textView8.setText(funcionarios.get(1).getNome());
                textView5.setText(funcionarios.get(1).getPontos());

                funcionarios.get(2);
                textView9.setText(funcionarios.get(2).getNome());
                textView6.setText(funcionarios.get(2).getPontos());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }







}














