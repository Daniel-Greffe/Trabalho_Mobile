package com.example.p2_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputEmail, inputSenha;
    TextView recSenha, criarUsuario;
    Button btLogar;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail  = findViewById(R.id.loginEmail);
        inputSenha  = findViewById(R.id.loginSenha);
        criarUsuario  = findViewById(R.id.textViewCriaUsuario);
        recSenha  = findViewById(R.id.textViewEsqueciSenha);
        btLogar  = findViewById(R.id.btCadastrar);
        //progressBar  = findViewById(R.id.progressBar);

        criarUsuario.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCadastrar:
                logar();
                break;
            case R.id.textViewEsqueciSenha:
                //intent = new Intent(MainActivity.this, RecuperaSenha.class);
                startActivity(intent);
                break;
            case R.id.textViewCriaUsuario:
                intent = new Intent(MainActivity.this, CriarUsuario.class);
                startActivity(intent);
                break;
        }
    }

    private void logar() {
        String email = inputEmail.getText().toString();
        String senha = inputSenha.getText().toString();

        if(email.equals("") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("Preencha corretamente");
            inputSenha.requestFocus();
            return;
        }

        if(senha.equals("")){
            inputSenha.setError("Preencha corretamente");
            inputSenha.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        intent = new Intent(MainActivity.this, UsuarioLogado.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Confirme seu login por email", Toast.LENGTH_LONG).show();
                        user.sendEmailVerification();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}