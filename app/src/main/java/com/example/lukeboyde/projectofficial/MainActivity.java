package com.example.lukeboyde.projectofficial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {

    private final static int FINE_LOCATION = 100;
    private final static int PLACE_PICKER_REQUEST = 1;

    TextView placeName;
    TextView placeAddress;
    TextView placeNumber;
    Button pickPoiButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

        placeName = (TextView) findViewById(R.id.placeName);
        placeAddress = (TextView) findViewById(R.id.placeAddress);
        placeNumber = (TextView) findViewById(R.id.placeNumber);
        pickPoiButton = (Button) findViewById(R.id.pickPOI);
        pickPoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try{
                    Intent intent = builder.build(MainActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.interests);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.interests:
                Intent intent = new Intent(this, SaveInterests.class);
                startActivity(intent);
                return true;
            case R.id.findPlace:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            case R.id.userProfile:
                intent = new Intent(this, PersonalProfile.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void requestPermission() {

        //check whether app has fine location permission, and request if necessary
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION);
            }
        }
    }

    // Handle result of permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);;

        switch (requestCode){
            case FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to detect your location!",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    //Retrieve results from place dilog
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //if resultcode is ok
        if (resultCode == RESULT_OK){
            //retrieve place object
            Place place = PlacePicker.getPlace(this, data);
            //retrieve name of place and display in textview
            placeName.setText(place.getName());
            //retrieve address of place and display in textview
            placeAddress.setText(place.getAddress());
            //retrieve phone number
            placeNumber.setText(place.getPhoneNumber());

            //if user exits dialog without selecting place
        } else if(resultCode == RESULT_CANCELED){
            //display following toast
            Toast.makeText(getApplicationContext(), "No place selected", Toast.LENGTH_LONG).show();
        }
    }
}
