package com.example.freelance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailEdit, password1Edit, password2Edit;
    private Button signUpButton;
    private TextView signInView;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
//        emailEdit = findViewById(R.id.email);
//        password1Edit = findViewById(R.id.password1);
//        password2Edit = findViewById(R.id.password2);
        progressDialog = new ProgressDialog(this);
//        signIn = findViewById(R.id.signIn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signInView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }
    private void Register() {
        String email = emailEdit.getText().toString();
        String password1 = password1Edit.getText().toString();
        String password2 = password2Edit.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailEdit.setError("Enter your email");
        }
        else if (TextUtils.isEmpty(password1)){
            password1Edit.setError("Enter your password");
        }
        else if (!password1.equals(password2)) {
            password2Edit.setError("Passwords do not match");
        }
        else if (password1.length() < 4 ){
            password1Edit.setError("Passwords length needs to be more than 4");
        }
        else if (!isValidemail(email)){
            emailEdit.setError("Invalid email");
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth.createUserWithEmailAndPassword(email,password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this,
                            "Successfully Registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this,
                            "Signup fail", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private Boolean isValidemail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}
