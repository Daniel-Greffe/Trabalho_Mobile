package com.example.p2_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CriarUsuario extends AppCompatActivity {

    EditText cadastrarEmail, cadastrarSenha;
    Button btCadastrar;
    FirebaseAuth mAuthCria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

        cadastrarEmail  = findViewById(R.id.cadastrarEmail);
        cadastrarSenha = findViewById(R.id.cadastrarSenha);
        btCadastrar = findViewById(R.id.btCadastrar);

        mAuthCria = FirebaseAuth.getInstance();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarUSuario(cadastrarEmail.getText().toString(), cadastrarSenha.getText().toString());
            }
        });
    }

    private void criarUSuario(String email, String senha) {
        if(email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            cadastrarEmail.setError("Preencha corretamente");
            cadastrarEmail.requestFocus();
            return;
        }

        if(senha.equals("")){
            cadastrarSenha.setError("Preencha corretamente");
            cadastrarSenha.requestFocus();
            return;
        }

        mAuthCria.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário criado com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CriarUsuario.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Erro ao criar usuário", Toast.LENGTH_LONG).show();
            }
        });
    }
}