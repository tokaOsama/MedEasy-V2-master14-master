package com.example.nihal.medeasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText loginPhone, loginPassWord;
    Button login;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPhone = findViewById(R.id.loginPhone);
        loginPassWord = findViewById(R.id.loginPassWord);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 final ProgressDialog loding = new ProgressDialog(LoginActivity.this);

                loding.setMessage("Loding please w8 ..."); // dh m4 btt7t fe el stringFile
                loding.setCancelable(false);

                String UserPhoneInFireB = loginPhone.getText().toString();
                String PassWordInFireB = loginPassWord.getText().toString();

                if (UserPhoneInFireB.isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.Toast_needPhone, Toast.LENGTH_SHORT).show();
                } else if (PassWordInFireB.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.Toast_needPassword, Toast.LENGTH_SHORT).show();
                } else {
                    loding.show();
                    UserPhoneInFireB = UserPhoneInFireB + "@gmail.com";
                    auth.signInWithEmailAndPassword(UserPhoneInFireB, PassWordInFireB).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                FirebaseUser user = task.getResult().getUser();
                                loding.dismiss();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            } else {
                                loding.show();
                                loding.dismiss();
                                Toast.makeText(LoginActivity.this, R.string.Toast_wrongPhonOrPassword, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    /**
     * to going to signUpForm
     **/

    public void signUpForm(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
