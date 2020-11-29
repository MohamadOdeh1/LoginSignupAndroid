package com.example.yourpackagename;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText etMail;
    private EditText etPass;
    private TextView txtLog;
    private Button btnLog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMail = (EditText) findViewById(R.id.emailField);
        etPass = (EditText) findViewById(R.id.passField);
        btnLog = (Button) findViewById(R.id.logIn);
        txtLog = (TextView) findViewById(R.id.signUpTxt);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(etMail.getText().toString(), etPass.getText().toString());
            }
        });
        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
    private void validate(String userName1, String passWord1){
        firebaseAuth.signInWithEmailAndPassword(userName1,passWord1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "LogIn Success!", Toast.LENGTH_SHORT).show();
                    
                }
                else{
                    Toast.makeText(MainActivity.this, "LogIn Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}