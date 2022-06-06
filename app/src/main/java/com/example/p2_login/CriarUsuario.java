package com.example.p2_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CriarUsuario extends AppCompatActivity {

    EditText cadastrarEmail, cadastrarSenha;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

        cadastrarEmail  = findViewById(R.id.loginEmail);
    }
}