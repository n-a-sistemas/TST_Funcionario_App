package com.example.tstfuncionario_30;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tstfuncionario_30.modelos.Funcionario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Funcionario funcionario = new Funcionario();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textView, textViewNome;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                String resultado = sharedPreferences.getString("LOGIN", "");

                if (!Boolean.parseBoolean(resultado)) {
                    criarLogin();
                    conectaBanco();

                }
                else {main();}
            }
        },2000);
    }

    public void main(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void criarLogin(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                123
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123){

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK){



                if (response.isNewUser()){
                    this.funcionario.setUuid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    this.funcionario.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    this.funcionario.setNome(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    this.funcionario.setValido("false");
                    this.funcionario.setPontos("");
                    this.funcionario.setImgScr("");
                    databaseReference
                            .child("projetotst")
                            .child("funcionario")
                            .child(funcionario.getUuid())
                            .setValue(funcionario);
                }

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LOGIN", "true");
                editor.apply();
                evento(FirebaseAuth.getInstance().getCurrentUser().getUid());
                evento(FirebaseAuth.getInstance().getCurrentUser().getUid());


            }
            else {

                if (response == null){
                    finish();
                }
            }
        }
    }


    public void conectaBanco(){
        FirebaseApp.initializeApp(SplashScreen.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void evento(String uid) {
        databaseReference.child("projetotst").child("funcionario")
                .child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String Email = (dataSnapshot.child("email").getValue().toString());
                        textView = findViewById(R.id.text_view_email);
                        textView.setText(Email);

                        imageView= findViewById(R.id.img_foto);

                        String img = (dataSnapshot.child("imgScr")
                                .getValue().toString());

                        Picasso.get().load(img)
                                .resize(120, 100)
                                .centerCrop().into(imageView);

                        String nome = (dataSnapshot.child("nome").getValue().toString());

                        textViewNome = findViewById(R.id.text_view_nome);
                        textViewNome.setText(nome);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
