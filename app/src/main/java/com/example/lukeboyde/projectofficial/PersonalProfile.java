package com.example.lukeboyde.projectofficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalProfile extends AppCompatActivity {

    TextView tvInterests, profileName, profileBio, profileNum, profileEmail;

    Button btnEP, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        tvInterests = (TextView)findViewById(R.id.tvInterests);
        profileName = (TextView)findViewById(R.id.profileName);
        profileBio = (TextView)findViewById(R.id.profileBio);
        profileNum = (TextView)findViewById(R.id.profileNum);
        profileEmail = (TextView)findViewById(R.id.profileEmail);

        btnHome = (Button)findViewById(R.id.home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inthome = new Intent(PersonalProfile.this, SaveInterests.class);
                startActivity(inthome);
            }
        });
        btnEP = (Button)findViewById(R.id.editProfile);
        btnEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalProfile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String name=extras.getString("name");
        String bio=extras.getString("bio");
        String email=extras.getString("email");
        String num=extras.getString("num");
        String interest=extras.getString("interests");

        profileName.setText(name);
        profileBio.setText(bio);
        profileEmail.setText(email);
        profileNum.setText(num);
        tvInterests.setText(interest);


    }
}
