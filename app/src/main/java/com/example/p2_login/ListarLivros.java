package com.example.p2_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.internal.constants.ListAppsActivityContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarLivros extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView;
    Button btIrCadastro;

    ArrayList<Livro> livroList = new ArrayList<>();
    ArrayList<String> livroListNomes = new ArrayList<>();
    ArrayAdapter<String> livroArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        listView = findViewById(R.id.listView);
        btIrCadastro = findViewById(R.id.btIrCadastro);

        if(getIntent().getExtras() != null){
            Livro livro = getIntent().getExtras().getParcelable("livro");
            livroList.add(livro);
            livroListNomes.add(livro.getNome());
        }

        livroArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, livroListNomes);

        listView.setAdapter(livroArrayAdapter);

        btIrCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarLivros.this, CadastroLivro.class));
            }
        });
    }
//    protected void onListItemClick(ListView l, View v, int position, long id) { //trata o click
//        super.onListItemClick(l, v, position, id);
//        Livro l1 = livroList.get(position);
//
//        //se não limpar o array, não renderiza a lista corretamente
//        //ouvinte addValueEventListener repopula ele com add.
//        livroList.clear();
//
//        databaseReference.child("Livro").child(l1.getNome()).removeValue();
//    }

}