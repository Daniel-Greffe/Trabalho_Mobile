package com.example.p2_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, senha;
    TextView recSenha, criarUsuario;
    Button btLogar;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email  = findViewById(R.id.loginEmail);
        senha  = findViewById(R.id.loginSenha);
        criarUsuario  = findViewById(R.id.textViewCriaUsuario);
        recSenha  = findViewById(R.id.textViewEsqueciSenha);
        btLogar  = findViewById(R.id.btCadastrar);
//        progressBar  = findViewById(R.id.progressBar);

        criarUsuario.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCadastrar:
                //logar();
                break;
            case R.id.textViewEsqueciSenha:
                //i = new Intent(MainActivity.this, RecuperaSenha.class);
                startActivity(i);
                break;
            case R.id.textViewCriaUsuario:
                i = new Intent(MainActivity.this, CriarUsuario.class);
                startActivity(i);
                break;
        }
    }
}