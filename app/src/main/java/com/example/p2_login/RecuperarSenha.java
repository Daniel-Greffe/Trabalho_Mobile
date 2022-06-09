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
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenha extends AppCompatActivity {

    EditText emailRec;
    Button botRec;
    FirebaseAuth mAuthRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        emailRec = findViewById(R.id.emailRec);
        botRec = findViewById(R.id.botRec);

        mAuthRec = FirebaseAuth.getInstance();

        botRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailRec.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.equals("")){
                    emailRec.setError("Preencha corretamente");
                    return;
                }

                mAuthRec.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RecuperarSenha.this, "E-mail enviado.", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RecuperarSenha.this, MainActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(RecuperarSenha.this, "Erro.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}