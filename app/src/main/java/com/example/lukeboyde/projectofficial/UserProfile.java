package com.example.lukeboyde.projectofficial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lukeboyde on 25/04/2018.
 */

public class UserProfile extends AppCompatActivity {

    Button conBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        conBtn = (Button)findViewById(R.id.connectButton);

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You want to Connect with Jamie. A request has been sent!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
