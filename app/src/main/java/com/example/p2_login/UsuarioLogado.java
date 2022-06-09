package com.example.p2_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UsuarioLogado extends AppCompatActivity {

    Button btlogout, btNovoLivro, btListarLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logado);

        btlogout = findViewById(R.id.buttonLogout);
        btNovoLivro = findViewById(R.id.btNovoLivro);
        btListarLivros = findViewById(R.id.btListarLivros);

        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(UsuarioLogado.this, "Logout", Toast.LENGTH_LONG).show();

                startActivity(new Intent(UsuarioLogado.this, MainActivity.class));
            }
        });

        btNovoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsuarioLogado.this, CadastroLivro.class));
            }
        });

        btListarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsuarioLogado.this, ListarLivros.class));
            }
        });
    }
}