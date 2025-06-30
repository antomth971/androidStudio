package com.farouk.beewireader;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.farouk.beewireader.reader.GattSensorReader;
import com.farouk.beewireader.reader.SensorData;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter btAdapter;
    private CircularProgressView cpv;
    private TextView errorTextView;
    private LinearLayout dataLinearLayout;
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView batteryTextView;
    private Button readButton;
    private SensorData sensorData;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cpv = findViewById(R.id.cpv); //affichage progression

        this.errorTextView = findViewById(R.id.errorTextView);
        this.dataLinearLayout = findViewById(R.id.dataLinearLayout);
        this.temperatureTextView = findViewById(R.id.temperatureTextView);
        this.humidityTextView = findViewById(R.id.humidityTextView);
        this.batteryTextView = findViewById(R.id.batteryTextView);
        this.readButton = findViewById(R.id.readButton);

        //bouton pour relancer l'activation
        this.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBluetoothAndProceed();
            }
        });

        //instanciation d'un objet de contrôle du BT
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            showErrorMessage("BluetoothAdapter not available.");
            return;
        }

        //Enregistrement d'un lanceur pour l'activité Bluetooth
        activityResultLauncher =
                registerForActivityResult(    //décrit un lanceur d'activité avec retour de résultat
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == RESULT_OK)
                                    ReadSmartClim();
                                else {
                                    showErrorMessage("Bluetooth doit être activé.");
                                }
                            }
                        });

        requestBluetoothAndProceed(); //activation et lecture des données
    }

    private void requestBluetoothAndProceed() {
        //lance l'activité système ctrlant le BT
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (enableIntent.resolveActivity(getPackageManager()) != null) {
                activityResultLauncher.launch(enableIntent);
                                    //et attend le résultat via un callBack
            }
    }
    private void showErrorMessage(String msg) {
        cpv.setVisibility(View.GONE);
        errorTextView.setText(msg);
        errorTextView.setVisibility(View.VISIBLE);
    }
    private void ReadSmartClim() {
        new Thread(new Runnable() {
            public void run(){
                try {
                    //lecture des données fait en tâche de fond
                    GattSensorReader sensorReader = new GattSensorReader(btAdapter, getApplicationContext());
                    String[] rawData = sensorReader.readRawData();
                    sensorData = SensorData.parseSensorData(rawData);

                    //lancement d'un thread UI pour opérer l'affichage
                    ((Activity)MainActivity.this).runOnUiThread(new Runnable(){
                        public void run()
                        {
                            if(sensorData == null) {
                                return;
                            }
                            cpv.setVisibility(View.GONE);
                            temperatureTextView.setText("temperature: " + sensorData.getTemperature() +"°C");
                            humidityTextView.setText("humidity: " + sensorData.getHumidity() + "%");
                            batteryTextView.setText("battery: " + sensorData.getBattery() + "%");
                            dataLinearLayout.setVisibility(View.VISIBLE);
                        }
                    });
                }
                catch (RuntimeException e) {
                }
            }
        }).start();
    }

/*
    @Override
    protected void onStart() {
        super.onStart();
        requestBluetoothAndProceed();
    }
*/

}