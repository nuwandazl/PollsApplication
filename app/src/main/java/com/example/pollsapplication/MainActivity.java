package com.example.pollsapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private Button enter_button;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter_button=findViewById(R.id.enter_btn);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this, gso);
        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = gsc.getSignInIntent();
                GoogleSignInLauncher.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> GoogleSignInLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(
                            result.getData()
                    );
                    try {
                        GoogleSignInAccount account =  task.getResult(ApiException.class);
                        MyAccount.email = account.getEmail();
                        MyAccount.name = account.getDisplayName();
                        Toast.makeText(MainActivity.this, "Успешная авторизация" + account.getEmail(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, PollsActivity.class);
                        startActivity(intent);
                    }catch (ApiException e) {
                        Toast.makeText(MainActivity.this, "Авторизация не удалась", Toast.LENGTH_LONG).show();
                    }
                }
            });
}