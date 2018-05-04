package com.example.lukeboyde.projectofficial;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class RecommendedActivity extends AppCompatActivity {

    DatabaseHelper db;

    TextView tvRec;

    String rcInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        rcInt = bundle.getString("interest");

        tvRec = (TextView)findViewById(R.id.TVRecommended);

        generate();

    }

    // for each interest the user has
    //loop through each interest
    // find all users who have share interests
    // show those who match up and rank from highest to lowest

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void generate(){
        ArrayList<Interest> interests = db.generateRecommendations(rcInt);

        //get userInterests
        //loop through the arraylist of interest
        //each interest get back name and put in tv
        for (int i = 0; i < interests.size(); i++){
            Interest interest = interests.get(i);
            //Interest interest = interests.get(i);
            tvRec.append(interest.toString());
        }



    }

}
