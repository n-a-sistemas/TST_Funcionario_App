package com.example.tstfuncionario_30;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tstfuncionario_30.modelos.Epi;

import androidx.annotation.NonNull;
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

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;



    private Epi epi = new Epi();


    private List<Epi> epis = new ArrayList<Epi>();
    private ArrayAdapter<Epi> arrayAdapterEpi;
    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textViewEmail, textViewNome;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectaBanco();





        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                    R.id.nav_sair)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");
        View hView =  navigationView.getHeaderView(0);
        final TextView nav_nome= (TextView)hView.findViewById(R.id.text_view_nome_main);
        final TextView nav_email = (TextView)hView.findViewById(R.id.text_view_email_main);
        final ImageView nav_img = (ImageView)hView.findViewById(R.id.img_foto);

        databaseReference.child("projetotst").child("funcionario")
                .child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String Email = (dataSnapshot.child("email").getValue().toString());
                        nav_email.setText(Email);

                        String img = (dataSnapshot.child("imgScr")
                               .getValue().toString());

                        Picasso.get().load(img)
                               .resize(80, 80)
                               .centerCrop().into(nav_img);

                        String nome = (dataSnapshot.child("nome").getValue().toString());
                        nav_nome.setText(nome);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_settings){
            sharedPreferences = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LOGIN","false");
            editor.apply();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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

                        textViewEmail.setText(Email);

                        String img = (dataSnapshot.child("imgScr")
                                .getValue().toString());

                        Picasso.get().load(img)
                                .resize(120, 100)
                                .centerCrop().into(imageView);

                        String nome = (dataSnapshot.child("nome").getValue().toString());




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}
