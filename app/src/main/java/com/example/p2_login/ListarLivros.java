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

    ArrayAdapter<Livro> livroArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        iniciarFirebase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        listView = findViewById(R.id.listView);
        btIrCadastro = findViewById(R.id.btIrCadastro);

        String palavra = "";
        pesquisarPalavra(palavra);

        btIrCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListarLivros.this, CadastroLivro.class));
            }
        });
    }

    private void pesquisarPalavra(String palavra) {
        Query query;

        if (palavra.equals("")) {
            query = databaseReference.child("Livro").orderByChild("nome");
        }
        else{
            query = databaseReference.child("Livro").orderByChild("nome").
                    startAt(palavra).endAt(palavra + "\uf8ff");
        }
        livroList.clear();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objDataSnapshot1: dataSnapshot.getChildren()) {
                    Livro l = objDataSnapshot1.getValue(Livro.class);
                    livroList.add(l);
                }

                livroArrayAdapter = new ArrayAdapter<>(ListarLivros.this,
                        android.R.layout.simple_list_item_1, livroList);

                //não esquecer do toString na model pois o arraylist é de objetos.
                //não esquecer de definir altura para a lista no xml.
                listView.setAdapter(livroArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Livro p1 = livroList.get(i);

                //se não limpar o array, não renderiza a lista corretamente
                //ouvinte addValueEventListener repopula ele com add.
                livroList.clear();

                databaseReference.child("Livro").child(p1.getNome()).removeValue();

            }
        });
    }
    private void iniciarFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}