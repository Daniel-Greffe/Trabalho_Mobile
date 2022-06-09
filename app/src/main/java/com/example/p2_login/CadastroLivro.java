package com.example.p2_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CadastroLivro extends AppCompatActivity{

    EditText livroNome, livroAutor, livroGenero;
    Button btCadastrarLivro;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        livroNome = findViewById(R.id.livroNome);
        livroAutor = findViewById(R.id.livroAutor);
        livroGenero = findViewById(R.id.livroGenero);
        btCadastrarLivro = findViewById(R.id.btCadastrarLivro);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarFirebase();
                String livroNomeValue = livroNome.getText().toString();
                String livroAutorValue = livroAutor.getText().toString();
                String livroGeneroValue = livroGenero.getText().toString();

                cadastrarLivro(livroNomeValue, livroAutorValue, livroGeneroValue);
            }
        });
    }

    private void cadastrarLivro(String nome, String autor, String genero){
        Livro livro = new Livro();

        if(nome.equals("")){
            livroNome.setError("Preencha corretamente");
            livroNome.requestFocus();
            return;
        }
        if(autor.equals("")){
            livroAutor.setError("Preencha corretamente");
            livroAutor.requestFocus();
            return;
        }
        if(genero.equals("")){
            livroGenero.setError("Preencha corretamente");
            livroGenero.requestFocus();
            return;
        }

        livro.setNome(nome);
        livro.setAutor(autor);
        livro.setGênero(genero);

        databaseReference.child("Livro").
                child(livro.getNome()).
                setValue(livro);

        Intent intent = new Intent(CadastroLivro.this, ListarLivros.class);
        intent.putExtra("livro", livro);
        startActivity(intent);
    }

    private void iniciarFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}