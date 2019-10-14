package com.example.tstfuncionario_30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tstfuncionario_30.modelos.Funcionario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditarActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    EditText editTextProfissao,editTextTelefone,editTextCidade,editTextEndereco;
    Funcionario funcionario = new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        conectaBanco();

        editTextProfissao = findViewById(R.id.edit_text_profissao);
        editTextEndereco = findViewById(R.id.edit_text_endereco);
        editTextCidade = findViewById(R.id.edit_text_cidade);
        editTextTelefone = findViewById(R.id.edit_text_telefone);
        funcionarioPuxar();



        FloatingActionButton fab = findViewById(R.id.fab_sair);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
                Intent intent = new Intent(EditarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void conectaBanco(){
        FirebaseApp.initializeApp(EditarActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void funcionarioPuxar(){
        databaseReference.child("projetotst").child("funcionario")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        editTextProfissao.setText((dataSnapshot.child("profissao").getValue().toString()));
                        editTextCidade.setText((dataSnapshot.child("cidade").getValue().toString()));
                        editTextEndereco.setText((dataSnapshot.child("endereco").getValue().toString()));
                        editTextTelefone.setText((dataSnapshot.child("telefone").getValue().toString()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void salvar(){

        funcionario.setProfissao(editTextProfissao.getText().toString());
        funcionario.setCidade(editTextCidade.getText().toString());
        funcionario.setTelefone(editTextTelefone.getText().toString());
        funcionario.setEndereco(editTextEndereco.getText().toString());

            databaseReference.child("projetotst").child("funcionario")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("telefone")
                    .setValue(funcionario.getTelefone());
            databaseReference.child("projetotst").child("funcionario")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profissao")
                    .setValue(funcionario.getProfissao());
            databaseReference.child("projetotst").child("funcionario")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("cidade")
                    .setValue(funcionario.getCidade());
            databaseReference.child("projetotst").child("funcionario")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("endereco")
                    .setValue(funcionario.getEndereco());



    }






}
