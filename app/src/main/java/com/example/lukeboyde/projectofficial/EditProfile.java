package com.example.lukeboyde.projectofficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button btn1, btn2, btn3, btn4, btn5, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        et1 = (EditText) findViewById(R.id.changeName);
        et2 = (EditText) findViewById(R.id.changeBio);
        et3 = (EditText) findViewById(R.id.changeEmail);
        et4 = (EditText) findViewById(R.id.changeNumber);
        et5 = (EditText) findViewById(R.id.changeInterests);

        btn1 = (Button) findViewById(R.id.update1);
        btn2 = (Button) findViewById(R.id.update2);
        btn3 = (Button) findViewById(R.id.update3);
        btn4 = (Button) findViewById(R.id.update4);
        btn5 = (Button) findViewById(R.id.update5);

        btnSave = (Button) findViewById(R.id.buttonSave);

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intname = new Intent(EditProfile.this, PersonalProfile.class);
//                String name = et1.getText().toString();
//                intname.putExtra("name", name);
//                startActivity(intname);
//
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intbio = new Intent(EditProfile.this, PersonalProfile.class);
//                String bio = et2.getText().toString();
//                intbio.putExtra("bio", bio);
//                startActivity(intbio);
//
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intemail = new Intent(EditProfile.this, PersonalProfile.class);
//                String email = et3.getText().toString();
//                intemail.putExtra("email", email);
//                startActivity(intemail);
//
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intnum = new Intent(EditProfile.this, PersonalProfile.class);
//                String num = et4.getText().toString();
//                intnum.putExtra("num", num);
//                startActivity(intnum);
//
//            }
//        });
//
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intinterests = new Intent(EditProfile.this, PersonalProfile.class);
//                String interests = et5.getText().toString();
//                intinterests.putExtra("interests", interests);
//                startActivity(intinterests);
//
//            }
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, PersonalProfile.class);
                Bundle extras = new Bundle();
                String name = et1.getText().toString();
                String bio = et2.getText().toString();
                String email = et3.getText().toString();
                String num = et4.getText().toString();
                String interests = et5.getText().toString();

                extras.putString("name", name);
                extras.putString("bio", bio);
                extras.putString("email", email);
                extras.putString("num", num);
                extras.putString("interests", interests);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });




    }
}
