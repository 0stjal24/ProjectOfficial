package com.example.lukeboyde.projectofficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SaveInterests extends AppCompatActivity {

    DatabaseHelper db;

    String firstname, lastname;

    EditText etInt;
    TextView tvDis, tvInterests, tvVR;
    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_interests);
        db = new DatabaseHelper(this);
        etInt = (EditText)findViewById(R.id.etInterests);
        tvDis = (TextView)findViewById(R.id.displayInterests);
        tvDis.setVisibility(View.GONE);
        tvInterests = (TextView)findViewById(R.id.tvInterests);
        tvVR = (TextView)findViewById(R.id.viewRecommendations);
        tvVR.setVisibility(View.GONE);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String intr = etInt.getText().toString();
                                        Boolean insertInterest = db.insertInterest(intr);
                                        if (insertInterest) {
                                            Toast.makeText(getApplicationContext(), "Interests saved", Toast.LENGTH_SHORT).show();
                                        }
                                    }

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String saveInt = etInt.getText().toString();
//                String file_name = "interest_file";
//                try {
//                    FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
//                    fileOutputStream.write(saveInt.getBytes());
//                    fileOutputStream.close();
//                    Toast.makeText(getApplicationContext(),"Interests Saved",Toast.LENGTH_LONG).show();
//                    etInt.setText("");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    String saveInt;
//                    FileInputStream fileInputStream = openFileInput("interest_file");
//                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    StringBuffer stringBuffer = new StringBuffer();
//                    while((saveInt = bufferedReader.readLine()) != null)
//                    {
//                        stringBuffer.append(saveInt +"\n");
//                    }
//                    tvDis.setText(stringBuffer.toString());
//                    tvDis.setVisibility(View.VISIBLE);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
                                    });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                ArrayList<NewProfile> profile = db.generateProfile();

                //get userInterests
                //loop through the arraylist of interest
                //each interest get back name and put in tv
                for (int i = 0; i < profile.size(); i++){
                    NewProfile np = profile.get(i);
                    Log.d("project", "generateProfile(1)");

                    //Interest interest = interests.get(i);
                    tvInterests.setText(np.toString());
                    Log.d("project", "generateRecommendations(2)");

                }
            }

        });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent recommendIntent = new Intent(SaveInterests.this, PersonalProfile.class);
                        String intr = etInt.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("text", intr);
                        recommendIntent.putExtras(bundle);
                        startActivity(recommendIntent);
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent connectInt = new Intent(SaveInterests.this, UserProfile.class);
                        startActivity(connectInt);
                    }
                });
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent recommendIntent = new Intent(SaveInterests.this, RecommendedActivity.class);
                        String intr = etInt.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("interest", intr);
                        recommendIntent.putExtras(bundle);
                        startActivity(recommendIntent);

                    }
                });


            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                MenuItem item = menu.findItem(R.id.interests);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.interests:
                        Intent intent = new Intent(this, SaveInterests.class);
                        startActivity(intent);
                        return true;
                    case R.id.findPlace:
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    default:
                        return super.onOptionsItemSelected(item);
                }
            }
        }

