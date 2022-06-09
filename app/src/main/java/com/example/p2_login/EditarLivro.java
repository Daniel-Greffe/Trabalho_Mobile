package com.example.p2_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditarLivro extends AppCompatActivity {

    EditText livroNomeEditar, livroAutorEditar, livroGeneroEditar;
    Button btEditarLivro;
    String livroNomeValue, livroAutorValue, livroGeneroValue;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);

        livroNomeEditar = findViewById(R.id.livroNomeEditar);
        livroAutorEditar = findViewById(R.id.livroAutorEditar);
        livroGeneroEditar = findViewById(R.id.livroGeneroEditar);
        btEditarLivro = findViewById(R.id.btEditarLivro);

        Livro livro = (Livro) getIntent().getSerializableExtra("l1");

        livroNomeValue = livro.getNome();
        livroAutorValue = livro.getAutor();
        livroGeneroValue = livro.getGenero();

        livroNomeEditar.setText(livroNomeValue);
        livroAutorEditar.setText(livroAutorValue);
        livroGeneroEditar.setText(livroGeneroValue);

        btEditarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarFirebase();
                livroNomeValue = livroNomeEditar.getText().toString();
                livroAutorValue = livroAutorEditar.getText().toString();
                livroGeneroValue = livroGeneroEditar.getText().toString();
                editarLivro(livroNomeValue, livroAutorValue, livroGeneroValue);
            }
        });
    }

    private void editarLivro(String nome, String autor, String genero){
        Livro livro = new Livro();
        livro.setNome(nome);
        livro.setAutor(autor);
        livro.setGênero(genero);

        databaseReference.child("Livro").
                child(livro.getNome()).
                setValue(livro);

        Intent intent = new Intent(EditarLivro.this, ListarLivros.class);
        startActivity(intent);
    }

    private void iniciarFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}