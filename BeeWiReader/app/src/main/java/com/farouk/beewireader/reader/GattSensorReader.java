package com.farouk.beewireader.reader;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class GattSensorReader {
    private String macAddress = "F0:C7:7F:85:35:0C";
    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private String[] data = new String[10];
    private final Object syncObj = new Object();
    public GattSensorReader(BluetoothAdapter bluetoothAdapter, Context context) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.context = context;
    }
    public String[] readRawData() {
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
        {
            bluetoothDevice.connectGatt(context, false, new BeeWiSmartBtCallback(context.getApplicationContext(), data, syncObj));
            synchronized (syncObj) {
                try {
                    // attente max 15 secondes du callback de lecture du capteur
                    syncObj.wait(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(data[0] == null) {
                throw new RuntimeException("Failed reading data.");
            }
        }
        return data;
    }
}
