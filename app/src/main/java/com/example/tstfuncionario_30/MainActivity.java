package com.example.tstfuncionario_30;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tstfuncionario_30.modelos.Funcionario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private SharedPreferences sharedPreferences;
    private Funcionario funcionario = new Funcionario();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textView, textViewNome;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectaBanco();


        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN", "");

        //if (!Boolean.parseBoolean(resultado)){
            criarLogin();

        //}


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send,R.id.nav_epi)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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

                    this.funcionario.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    this.funcionario.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    this.funcionario.setNome(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    this.funcionario.setValido(false);
                    this.funcionario.setPontos("");
                    this.funcionario.setImagem("");
                    databaseReference
                            .child("projetotst")
                            .child("funcionario")
                            .child(funcionario.getUid())
                            .setValue(funcionario);
                }

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LOGIN", "true");
                editor.apply();
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
        FirebaseApp.initializeApp(MainActivity.this);
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
